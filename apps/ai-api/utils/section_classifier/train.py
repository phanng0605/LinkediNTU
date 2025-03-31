import argparse
import pandas as pd
import numpy as np
from datasets import Dataset
from transformers import (AutoTokenizer, AutoModelForSequenceClassification,
                          Trainer, TrainingArguments, DataCollatorWithPadding)
from sklearn.metrics import accuracy_score, precision_recall_fscore_support

def compute_metrics(eval_pred):
    logits, labels = eval_pred
    predictions = np.argmax(logits, axis=-1)
    precision, recall, f1, _ = precision_recall_fscore_support(labels, predictions, average='weighted')
    acc = accuracy_score(labels, predictions)
    return {"accuracy": acc, "f1": f1, "precision": precision, "recall": recall}

def main():
    parser = argparse.ArgumentParser(
        description="Fine-tune a transformer model for resume section classification."
    )
    parser.add_argument("--data", type=str, required=True,
                        help="Path to the CSV file containing labeled resume segments.")
    parser.add_argument("--model_name", type=str, default="distilbert-base-uncased",
                        help="Pretrained transformer model name (default: distilbert-base-uncased).")
    parser.add_argument("--output_dir", type=str, default="./model",
                        help="Directory to save the fine-tuned model.")
    parser.add_argument("--num_train_epochs", type=int, default=3,
                        help="Number of training epochs (default: 3).")
    parser.add_argument("--per_device_train_batch_size", type=int, default=16,
                        help="Batch size per device during training (default: 16).")
    parser.add_argument("--per_device_eval_batch_size", type=int, default=16,
                        help="Batch size per device during evaluation (default: 16).")
    args = parser.parse_args()

    # Load CSV data into a DataFrame
    df = pd.read_csv(args.data)
    
    # Create a mapping from label names to numeric IDs
    unique_labels = sorted(df['label'].unique())
    label2id = {label: idx for idx, label in enumerate(unique_labels)}
    id2label = {idx: label for label, idx in label2id.items()}
    
    # Map labels to numeric IDs
    df['label_id'] = df['label'].map(label2id)
    
    # Split into train and test (80/20 split)
    train_df = df.sample(frac=0.8, random_state=42)
    test_df = df.drop(train_df.index)
    
    # Convert pandas DataFrames to Hugging Face Datasets
    train_dataset = Dataset.from_pandas(train_df)
    test_dataset = Dataset.from_pandas(test_df)
    train_dataset = train_dataset.rename_column("label_id", "labels")
    test_dataset = test_dataset.rename_column("label_id", "labels")

    
    # Load the tokenizer and model
    tokenizer = AutoTokenizer.from_pretrained(args.model_name)
    model = AutoModelForSequenceClassification.from_pretrained(
        args.model_name,
        num_labels=len(unique_labels),
        id2label=id2label,
        label2id=label2id
    )
    
    # Preprocessing function for tokenization
    def preprocess_function(examples):
        return tokenizer(examples["text"], truncation=True, padding=True)
    
    # Tokenize the datasets
    train_dataset = train_dataset.map(preprocess_function, batched=True)
    test_dataset = test_dataset.map(preprocess_function, batched=True)
    train_dataset = train_dataset.remove_columns(["text", "label", "__index_level_0__"])
    test_dataset = test_dataset.remove_columns(["text", "label", "__index_level_0__"])
    train_dataset.set_format("torch")
    test_dataset.set_format("torch")
    
    # Data collator to handle dynamic padding
    data_collator = DataCollatorWithPadding(tokenizer=tokenizer)
    
    # Define training arguments
    training_args = TrainingArguments(
        output_dir=args.output_dir,
        evaluation_strategy="epoch",
        save_strategy="epoch", 
        num_train_epochs=args.num_train_epochs,
        per_device_train_batch_size=args.per_device_train_batch_size,
        per_device_eval_batch_size=args.per_device_eval_batch_size,
        weight_decay=0.01,
        save_total_limit=2,
        load_best_model_at_end=True,
        metric_for_best_model="f1"
    )
       
    # Initialize the Trainer
    trainer = Trainer(
        model=model,
        args=training_args,
        train_dataset=train_dataset,
        eval_dataset=test_dataset,
        tokenizer=tokenizer,
        data_collator=data_collator,
        compute_metrics=compute_metrics
    )
    
    # Train the model
    trainer.train()
    trainer.evaluate()
    
    # Save the fine-tuned model and tokenizer
    model.save_pretrained(args.output_dir)
    tokenizer.save_pretrained(args.output_dir)
    print(f"Model and tokenizer saved to {args.output_dir}")

if __name__ == "__main__":
    main()

