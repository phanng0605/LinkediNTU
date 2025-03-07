import Head from "next/head";
import { HeroSection } from "@/components/about/HeroSection";
import { MissionVisionSection } from "@/components/about/MissionVisionSection";
import { OurStorySection } from "@/components/about/OurStorySection";
import { CoreValuesSection } from "@/components/about/CoreValueSections";
import { TeamSection } from "@/components/about/TeamSection";
import { PartnersSection } from "@/components/about/PartnersSection";
import { FAQSection } from "@/components/about/FAQSection";
import { ContactSection } from "@/components/about/ContactSection";
import { FooterSection } from "@/components/common/FooterSection";

export default function AboutUs() {
  return (
    <div className="flex flex-col min-h-screen">
      <Head>
        <title>About Us - LinkediNTU</title>
        <meta
          name="description"
          content="Learn about LinkediNTU, our mission, team, and how we're helping NTU students advance their careers."
        />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <HeroSection />
      <MissionVisionSection />
      <OurStorySection />
      <CoreValuesSection />
      <TeamSection />
      <PartnersSection />
      <FAQSection />
      <ContactSection />
      <FooterSection />
    </div>
  );
}
