import sys
import os

sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), "..")))


import argparse
import csv
from GroqLLM import GroqLLMClient  

class ResumeDataAugmentor:
    def __init__(self, groq_client: GroqLLMClient):
        self.groq_client = groq_client

    def generate_segment(self, role: str, label: str) -> str:
        """
        Generate a realistic resume segment for a given section label and job role.
        Args:
            role (str): The technical role (e.g., "Software Engineer")
            label (str): The resume section label (e.g., "Experience")
        Returns:
            str: A generated resume segment text.
        """
        prompt = (
            f"Generate a realistic {label} segment for a job applicant applying for a technical role as {role}. "
            "Provide only the segment text."
        )
        segment = self.groq_client.chat_completion(prompt)
        return segment.strip()

def main():
    parser = argparse.ArgumentParser(
        description="Augment a training CSV file for resume segments using GroqLLM."
    )
    parser.add_argument("--role", required=True, help="The technical job role (e.g., 'Software Engineer').")
    parser.add_argument(
        "--num", type=int, default=5,
        help="Number of samples to generate per resume section (default: 5)."
    )
    parser.add_argument(
        "--output", required=True,
        help="The output CSV filename (e.g., augmented_resume_segments.csv)."
    )
    args = parser.parse_args()

    # Define the resume section labels
    section_list = [
        "Contact Information", "Summary", "Education", "Experience",
        "Skills", "Projects", "Certifications", "Languages", "Interests"
    ]

    # Initialize GroqLLMClient with your API key
    groq_key = "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"
    groq_client = GroqLLMClient(api_key=groq_key)
    augmentor = ResumeDataAugmentor(groq_client)

    # Ensure the output folder exists
    output_folder = os.path.dirname(args.output)
    if output_folder and not os.path.exists(output_folder):
        os.makedirs(output_folder, exist_ok=True)

    # Open the CSV file for writing augmented data
    with open(args.output, "w", newline="", encoding="utf-8") as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(["text", "label"])  # CSV header

        for label in section_list:
            for _ in range(args.num):
                segment = augmentor.generate_segment(args.role, label)
                writer.writerow([segment, label])
                print(f"Generated {label} segment: {segment[:60]}...")

    print(f"Augmented training data successfully written to {args.output}")

if __name__ == "__main__":
    main()

