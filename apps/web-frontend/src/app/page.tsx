// pages/index.tsx

import Head from "next/head"
import { HeroSection } from "@/components/landing/HeroSection"
import { StatsSection } from "@/components/landing/StatsSection"
import { KeyFeaturesSection } from "@/components/landing/KeyFeaturesSection"
import { HowItWorksSection } from "@/components/landing/HowItWorksSection"
import { AdvancedFeaturesSection } from "@/components/landing/AdvancedFeaturesSection"
import { TestimonialsSection } from "@/components/landing/TestimonialsSection"
import { CTASection } from "@/components/landing/CTASection"
import { FooterSection } from "@/components/common/FooterSection"

export default function Home() {
  return (
    <div className="flex flex-col min-h-screen">
      <Head>
        <title>LinkediNTU - Career Platform for NTU Students</title>
        <meta
          name="description"
          content="A platform for NTU students to find job opportunities and internships through personalized recommendations and AI-powered resume improvement."
        />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <HeroSection />
      <StatsSection />
      <KeyFeaturesSection />
      <HowItWorksSection />
      <AdvancedFeaturesSection />
      <TestimonialsSection />
      <CTASection />
      <FooterSection />
    </div>
  )
}
