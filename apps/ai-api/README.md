# ai-api (FastAPI)

This directory contains the FastAPI app for AI or ML endpoints.

To activate virtual environment installed:
source /Users/trongphan/Desktop/LinkediNTU/apps/ai-api/venv/bin/activate 

### Installation

We are currently using Poetry as the package manager for this project. To install the dependencies, run the following command:

```bash
poetry install
```

To activate the virtual environment, run the following command:

```bash
poetry shell
```

### Running the app

Haven't touched this part yet so of course it's not working. But the plan is to run the app using the following command:

```bash
uvicorn app.main:app --reload
```


Structure of folder:
| app -> run UI
| training
    | augmentor -> data augmentation
    | finetuning 
| utils
    | analyser
        | prompts.txt
        | resume_analyser.py -> to analyse resume using LLMs
    | data
        | feedback -> store feed back in json format
        | processed_resume -> store parsed resume in json format
        | resume_pdf
    | parser
        | ResumeParser.py -> Resume Parser class
    | section_classifier
        | no idea what is this
| venv -> virtual env
