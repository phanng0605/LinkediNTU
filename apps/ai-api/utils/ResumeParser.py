import PyPDF2 
import json 
import torch
from transformers import AutoTokenizer, AutoModelForSequenceClassification

class ResumeParser:
    def __init__(self, file_path, model_dir):

        self.file_path = file_path
        self.text = ""
        self.json_data = {}

        # Load the tokenizer and model
        self.tokenizer = AutoTokenizer.from_pretrained(model_dir)
        self.model = AutoModelForSequenceClassification.from_pretrained(model_dir)
        self.id2label = self.model.config.id2label

        self.section_list = list(self.id2label.values())
        self.section_list.append("Others")

    def extract_text(self):
        """
        Extract text from the resume
        """
        try: 
            with open(self.file_path, 'rb') as file:
                reader = PyPDF2.PdfReader(file)
                text = ""
                for page in reader.pages:
                    page_text = page.extract_text()
                    if page_text:
                        text += page_text + "\n"
                self.text = text
                return text
        except Exception as e:
            print("Error while extracting text:",e)
            return None

    def parse_sections(self):
        """
        Use the trained huggingface model to predict the section of the resume
        """
        segments = self.text.split("\n")

        sections = {sec: [] for sec in self.section_list}

        for segment in segments:
            segment = segment.strip()
            if not segment: 
                continue

            inputs = self.tokenizer(segment, return_tensors="pt", truncation=True, max_length=512)
            
            with torch.no_grad():
                outputs = self.model(**inputs)

            predicted_label = self.id2label[torch.argmax(outputs.logits, dim=1).item()]

            if predicted_label not in sections:
                predicted_label = "Others"

            sections[predicted_label].append(segment)

        self.json_data = sections

    def to_json(self):
        return json.dumps(self.json_data, indent=4)

    def parse(self):
        self.extract_text()
        self.parse_sections()
        return self.json_data

# Usage
if __name__ == "__main__":
    file_path = "sample_resume.pdf"
    model_dir = "../training/section_classifier/fine_tuned_model"

    parser = ResumeParser(file_path, model_dir)
    result = parser.parse()

    with open("parsed_resume.json", "w", encoding="utf-8") as file:
        file.write(parser.to_json())

    print("Resume parsed successfully!")






            
