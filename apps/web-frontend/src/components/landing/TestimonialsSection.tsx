// components/landing/TestimonialsSection.tsx

import Image from "next/image"
import { Card, CardContent, CardHeader, CardTitle, CardDescription } from "@/components/ui/card"

export function TestimonialsSection() {
  return (
    <section className="py-20 bg-muted/50">
      <div className="container px-4 md:px-6 mx-auto">
        <div className="text-center mb-12">
          <h2 className="text-3xl md:text-4xl font-bold tracking-tight mb-4">What Students Say</h2>
          <p className="text-xl text-muted-foreground max-w-[800px] mx-auto">
            Hear from NTU students who have transformed their career journey with LinkediNTU.
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8">
          <TestimonialCard
            name="Sarah Chen"
            year="Computer Science, Year 3"
            text="The AI resume feedback helped me completely transform my CV. I secured an internship at a top tech
            company within two weeks of using the platform!"
          />
          <TestimonialCard
            name="Raj Patel"
            year="Business Administration, Year 4"
            text="The networking features connected me with alumni in my dream industry. Their mentorship was
            invaluable in helping me prepare for interviews and understand the industry."
          />
          <TestimonialCard
            name="Li Wei"
            year="Mechanical Engineering, Year 2"
            text="The skill gap analysis showed me exactly what I needed to learn to be competitive. The recommended
            courses helped me develop the right skills for my target roles."
          />
        </div>
      </div>
    </section>
  )
}

function TestimonialCard({
  name,
  year,
  text,
}: {
  name: string
  year: string
  text: string
}) {
  return (
    <Card className="bg-background">
      <CardHeader>
        <div className="flex items-center gap-4">
          <div className="w-12 h-12 rounded-full bg-primary/20 overflow-hidden">
            <Image
              src="/placeholder.svg?height=48&width=48"
              width={48}
              height={48}
              alt={name}
              className="object-cover"
            />
          </div>
          <div>
            <CardTitle className="text-lg">{name}</CardTitle>
            <CardDescription>{year}</CardDescription>
          </div>
        </div>
      </CardHeader>
      <CardContent>
        <p className="italic">{`"${text}"`}</p>
      </CardContent>
    </Card>
  )
}
