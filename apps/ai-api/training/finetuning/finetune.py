from transformers import AutoTokenizer, AutoModelForCausalLM, TrainingArguments, Trainer
from datasets import load_dataset, Dataset
import torch

# Load the dataset
def load_custom_dataset(file_path):
    """
    Load a custom dataset from a JSON or CSV file.
    """
    dataset = load_dataset('json', data_files=file_path)
    return dataset

# Preprocess the dataset
def preprocess_dataset(dataset, tokenizer):
    """
    Tokenize the dataset for text generation.
    """
    def tokenize_function(examples):
        # Combine resume and job description into a single input
        inputs = [f"Resume: {resume}\nJob Description: {jd}\nTailored Resume:" for resume, jd in zip(examples['resume'], examples['job_description'])]
        targets = examples['tailored_resume']
        
        # Tokenize inputs and targets
        model_inputs = tokenizer(inputs, max_length=512, truncation=True, padding="max_length")
        labels = tokenizer(targets, max_length=512, truncation=True, padding="max_length")
        
        # Set labels for the model
        model_inputs["labels"] = labels["input_ids"]
        return model_inputs

    tokenized_dataset = dataset.map(tokenize_function, batched=True, remove_columns=["resume", "job_description", "tailored_resume"])
    return tokenized_dataset

# Load the model and tokenizer
model_name = "deepseek-ai/DeepSeek-R1-Distill-Qwen-1.5B"
tokenizer = AutoTokenizer.from_pretrained(model_name)
model = AutoModelForCausalLM.from_pretrained(model_name)

# Load and preprocess the dataset
file_path = "path/to/your/dataset.json"  # Replace with your dataset path
dataset = load_custom_dataset(file_path)
tokenized_dataset = preprocess_dataset(dataset['train'], tokenizer)

# Define training arguments
training_args = TrainingArguments(
    output_dir="./fine_tuned_model",  # Directory to save the fine-tuned model
    evaluation_strategy="epoch",
    learning_rate=5e-5,
    per_device_train_batch_size=4,
    per_device_eval_batch_size=4,
    num_train_epochs=3,
    weight_decay=0.01,
    save_strategy="epoch",
    save_total_limit=2,
    fp16=True,  # Enable mixed precision training if using a GPU
    logging_dir="./logs",
    logging_steps=10,
    push_to_hub=False,  # Set to True if you want to push the model to Hugging Face Hub
)

# Initialize the Trainer
trainer = Trainer(
    model=model,
    args=training_args,
    train_dataset=tokenized_dataset,
    eval_dataset=tokenized_dataset,  # Use the same dataset for evaluation (replace with a validation set if available)
    tokenizer=tokenizer,
)

# Fine-tune the model
trainer.train()

# Save the fine-tuned model
trainer.save_model("./fine_tuned_model")
tokenizer.save_pretrained("./fine_tuned_model")