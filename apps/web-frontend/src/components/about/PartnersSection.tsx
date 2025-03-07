// components/about/PartnersSection.tsx

import Image from "next/image"
import { Button } from "@/components/ui/button"

export function PartnersSection() {
  const partners = [1, 2, 3, 4, 5, 6, 7, 8] // just placeholders

  return (
    <section className="py-16 md:py-24">
      <div className="container px-4 md:px-6 mx-auto">
        <h2 className="text-3xl font-bold tracking-tight mb-6 text-center">Our Partners</h2>
        <p className="text-lg text-muted-foreground mb-12 text-center max-w-3xl mx-auto">
          We collaborate with leading companies, organizations, and NTU departments to provide the best opportunities
          and resources for our users.
        </p>
        <div className="grid grid-cols-2 md:grid-cols-4 gap-8 items-center justify-items-center">
          {partners.map((i) => (
            <div
              key={i}
              className="bg-muted/30 p-6 rounded-lg w-full max-w-[200px] h-[100px] flex items-center justify-center"
            >
              <Image
                src="/placeholder.svg?height=60&width=120"
                width={120}
                height={60}
                alt={`Partner ${i}`}
                className="max-w-full max-h-full"
              />
            </div>
          ))}
        </div>
        <div className="mt-12 text-center">
          <Button variant="outline">Become a Partner</Button>
        </div>
      </div>
    </section>
  )
}

