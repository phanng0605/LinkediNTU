#!/bin/bash
# Usage: ./generate_data.sh "<role>" <number_of_entries> <output_filename>
if [ "$#" -ne 3 ]; then
    echo "Usage: $0 \"<role>\" <number_of_entries> <output_filename>"
    exit 1
fi

ROLE="$1"
NUM="$2"
OUTPUT_FILE="$3"

# Run the Python script with the specified role, count, and output filename
python3 DataAugmentor.py --role "$ROLE" --num "$NUM" --output "$OUTPUT_FILE"

