// components/about/FAQSection.tsx

import { Accordion, AccordionContent, AccordionItem, AccordionTrigger } from "@/components/ui/accordion"

export function FAQSection() {
  return (
    <section className="py-16 md:py-24 bg-muted/30">
      <div className="container px-4 md:px-6 mx-auto">
        <div className="max-w-3xl mx-auto">
          <h2 className="text-3xl font-bold tracking-tight mb-12 text-center">Frequently Asked Questions</h2>
          <Accordion type="single" collapsible className="w-full">
            <AccordionItem value="item-1">
              <AccordionTrigger>Who can use LinkediNTU?</AccordionTrigger>
              <AccordionContent>
                LinkediNTU is designed specifically for Nanyang Technological University students and alumni. Current
                students from all faculties and programs can access the platform using their NTU credentials.
              </AccordionContent>
            </AccordionItem>
            <AccordionItem value="item-2">
              <AccordionTrigger>Is LinkediNTU free to use?</AccordionTrigger>
              <AccordionContent>
                Yes, LinkediNTU is completely free for all NTU students. We're funded through university grants,
                partnerships with employers, and optional premium features for alumni.
              </AccordionContent>
            </AccordionItem>
            <AccordionItem value="item-3">
              <AccordionTrigger>How does the AI resume feedback work?</AccordionTrigger>
              <AccordionContent>
                Our AI system analyzes your resume against industry standards and specific job descriptions. It
                provides detailed feedback on content, formatting, and keyword optimization, along with actionable
                suggestions for improvement.
              </AccordionContent>
            </AccordionItem>
            <AccordionItem value="item-4">
              <AccordionTrigger>Can I connect with alumni through LinkediNTU?</AccordionTrigger>
              <AccordionContent>
                Our mentorship program connects current students with NTU alumni working in their fields of interest.
                You can request mentorship, ask questions, and get personalized career advice.
              </AccordionContent>
            </AccordionItem>
            <AccordionItem value="item-5">
              <AccordionTrigger>How do I join or create an interest group?</AccordionTrigger>
              <AccordionContent>
                After logging in, navigate to the "Communities" section where you can browse existing groups or create
                your own. Groups can be organized around academic interests, career paths, or specific skills.
              </AccordionContent>
            </AccordionItem>
          </Accordion>
        </div>
      </div>
    </section>
  )
}

