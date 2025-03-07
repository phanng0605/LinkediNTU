#!/bin/bash
# This script runs the transformer-based resume section classification training.

# Set paths and parameters
DATA_FILE="augment.csv"
OUTPUT_DIR="fine_tuned_model"
MODEL_NAME="distilbert-base-uncased"
NUM_EPOCHS=3
TRAIN_BATCH_SIZE=16
EVAL_BATCH_SIZE=16

echo "Starting training..."
echo "Data file: $DATA_FILE"
echo "Model: $MODEL_NAME"
echo "Output directory: $OUTPUT_DIR"
echo "Number of epochs: $NUM_EPOCHS"
echo "Training batch size: $TRAIN_BATCH_SIZE"
echo "Evaluation batch size: $EVAL_BATCH_SIZE"

# Run the training script with the parameters
python train.py \
  --data "$DATA_FILE" \
  --model_name "$MODEL_NAME" \
  --output_dir "$OUTPUT_DIR" \
  --num_train_epochs "$NUM_EPOCHS" \
  --per_device_train_batch_size "$TRAIN_BATCH_SIZE" \
  --per_device_eval_batch_size "$EVAL_BATCH_SIZE"

echo "Training completed."

