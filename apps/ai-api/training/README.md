## Usage guidance
>> This folder contains the code to generate the training data and finetune our models

```bash
# To generate the training data
./generate_data.sh "<role>" <number_of_entries> <output_filename> <custom_input>


```

The script `generate_data.sh` generates the training data for the model. The script takes three arguments:
- `<role>`: The role of the user. It can be either `admin` or `user`
- `<number_of_entries>`: The number of entries to generate
- `<output_filename>`: The name of the file to save the generated data

08/03 changes:
* add new bash script (test.sh) to try generate directly from custom input.
* can have a look at input_resume.json & custom_generate.json to see the input & output (with good prompt)


