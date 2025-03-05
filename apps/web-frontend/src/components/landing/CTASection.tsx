// components/landing/CTASection.tsx

import { Button } from "@/components/ui/button"

export function CTASection() {
  return (
    <section className="py-20 bg-primary text-primary-foreground">
      <div className="container px-4 md:px-6 mx-auto">
        <div className="flex flex-col md:flex-row items-center justify-between gap-8">
          <div className="space-y-4 text-center md:text-left">
            <h2 className="text-3xl md:text-4xl font-bold tracking-tight">Ready to Elevate Your Career?</h2>
            <p className="text-xl text-primary-foreground/90 max-w-[600px]">
              Join thousands of NTU students who are already using LinkediNTU to find their dream opportunities.
            </p>
          </div>
          <div className="flex flex-col sm:flex-row gap-4">
            <Button size="lg" className="bg-white text-primary hover:bg-white/90">
              Sign Up Now
            </Button>
            <Button size="lg" variant="outline" className="bg-transparent border-white text-white hover:bg-white/10">
              Request Demo
            </Button>
          </div>
        </div>
      </div>
    </section>
  )
}
