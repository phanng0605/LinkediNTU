## Usage guidance
>> This folder contains the code to generate the training data and finetune our models

```bash
# To generate the training data
./generate_data.sh "<role>" <number_of_entries> <output_filename>


```

The script `generate_data.sh` generates the training data for the model. The script takes three arguments:
- `<role>`: The role of the user. It can be either `admin` or `user`
- `<number_of_entries>`: The number of entries to generate
- `<output_filename>`: The name of the file to save the generated data


