## This folder is for model experiements. 
- I tried Qwen 1.5B with very basic prompting, and seems like the model performs quite well! 
- Use gradio for better representation (as ggle colab can't host local website:<)

## Instruction to run these notebooks locally via poetry:
- Install poetry (follow the instruction on the official website)
- Run `poetry install` 
- Run `python -m ipykernel install --user --name=<project_name>` to install the kernel

>> Note: this is how I usually run my notebooks locally. Not sure if its the best way to do it ;( 
## Follow up steps: 
- Data augmentation using free LLMs (GPT, deepseek, Gemini)
- Finetune model with those augmented data and save model checkpoint, load to local and run. 

