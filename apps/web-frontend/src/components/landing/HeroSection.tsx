// components/landing/HeroSection.tsx

import Image from "next/image"
import { Button } from "@/components/ui/button"

export function HeroSection() {
  return (
    <section className="relative bg-gradient-to-r from-primary/90 to-primary py-20 md:py-32">
      <div className="container px-4 md:px-6 mx-auto flex flex-col md:flex-row items-center gap-8">
        <div className="flex-1 space-y-4 text-center md:text-left">
          <h1 className="text-4xl md:text-5xl lg:text-6xl font-bold tracking-tighter text-white">
            Elevate Your Career Journey at NTU
          </h1>
          <p className="text-xl text-white/90 max-w-[600px] mx-auto md:mx-0">
            Connect with opportunities, improve your resume with AI, and network with peers who share your
            professional interests.
          </p>
          <div className="flex flex-col sm:flex-row gap-4 justify-center md:justify-start">
            <Button size="lg" className="bg-white text-primary hover:bg-white/90">
              Get Started
            </Button>
            <Button size="lg" variant="outline" className="bg-transparent border-white text-white hover:bg-white/10">
              Learn More
            </Button>
          </div>
        </div>
        <div className="flex-1 relative">
          <Image
            src="/hero.jpg?height=400&width=500"
            width={500}
            height={400}
            alt="NTU students collaborating"
            className="rounded-lg shadow-xl"
          />
        </div>
      </div>
      <div className="absolute bottom-0 left-0 right-0 h-16 bg-gradient-to-t from-background to-transparent"></div>
    </section>
  )
}
