// components/landing/KeyFeaturesSection.tsx

import Image from "next/image"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { Button } from "@/components/ui/button"
import { CheckCircle } from "lucide-react"

export function KeyFeaturesSection() {
  return (
    <section className="py-20">
      <div className="container px-4 md:px-6 mx-auto">
        <div className="text-center mb-12">
          <h2 className="text-3xl md:text-4xl font-bold tracking-tight mb-4">
            Powerful Features for Your Career Growth
          </h2>
          <p className="text-xl text-muted-foreground max-w-[800px] mx-auto">
            LinkediNTU provides a comprehensive suite of tools designed to help NTU students excel in their career
            journey.
          </p>
        </div>

        <Tabs defaultValue="resume" className="w-full max-w-4xl mx-auto">
          <TabsList className="grid grid-cols-3 mb-8">
            <TabsTrigger value="resume">Resume Improvement</TabsTrigger>
            <TabsTrigger value="jobs">Job Matching</TabsTrigger>
            <TabsTrigger value="network">Networking</TabsTrigger>
          </TabsList>

          <TabsContent value="resume" className="space-y-4">
            <ResumeFeature />
          </TabsContent>
          <TabsContent value="jobs" className="space-y-4">
            <JobsFeature />
          </TabsContent>
          <TabsContent value="network" className="space-y-4">
            <NetworkingFeature />
          </TabsContent>
        </Tabs>
      </div>
    </section>
  )
}

function ResumeFeature() {
  return (
    <div className="grid md:grid-cols-2 gap-8 items-center">
      <div>
        <Image
          src="/placeholder.svg?height=300&width=400"
          width={400}
          height={300}
          alt="AI Resume Analysis"
          className="rounded-lg shadow-md"
        />
      </div>
      <div className="space-y-4">
        <h3 className="text-2xl font-bold">AI-Powered Resume Enhancement</h3>
        <ul className="space-y-3">
          <FeatureListItem text="Get personalized feedback on your resume from advanced LLMs" />
          <FeatureListItem text="Automatically tailor your resume to match specific job descriptions" />
          <FeatureListItem text="Identify and bridge skill gaps with targeted recommendations" />
          <FeatureListItem text="Maintain multiple resume versions for different career paths" />
        </ul>
        <Button className="mt-2">Improve Your Resume</Button>
      </div>
    </div>
  )
}

function JobsFeature() {
  return (
    <div className="grid md:grid-cols-2 gap-8 items-center">
      <div className="space-y-4 order-2 md:order-1">
        <h3 className="text-2xl font-bold">Smart Job Recommendations</h3>
        <ul className="space-y-3">
          <FeatureListItem text="Personalized job feed based on your skills and preferences" />
          <FeatureListItem text="Real-time notifications for matching opportunities" />
          <FeatureListItem text="Detailed company insights and culture information" />
          <FeatureListItem text="Strategic internship application timeline" />
        </ul>
        <Button className="mt-2">Explore Jobs</Button>
      </div>
      <div className="order-1 md:order-2">
        <Image
          src="/placeholder.svg?height=300&width=400"
          width={400}
          height={300}
          alt="Job Matching"
          className="rounded-lg shadow-md"
        />
      </div>
    </div>
  )
}

function NetworkingFeature() {
  return (
    <div className="grid md:grid-cols-2 gap-8 items-center">
      <div>
        <Image
          src="/placeholder.svg?height=300&width=400"
          width={400}
          height={300}
          alt="Networking"
          className="rounded-lg shadow-md"
        />
      </div>
      <div className="space-y-4">
        <h3 className="text-2xl font-bold">Connect & Collaborate</h3>
        <ul className="space-y-3">
          <FeatureListItem text="Join interest-based groups for study sessions and workshops" />
          <FeatureListItem text="Connect with alumni and industry mentors" />
          <FeatureListItem text="Exchange feedback through peer review system" />
          <FeatureListItem text="Discover and register for career events" />
        </ul>
        <Button className="mt-2">Start Networking</Button>
      </div>
    </div>
  )
}

function FeatureListItem({ text }: { text: string }) {
  return (
    <li className="flex items-start gap-2">
      <CheckCircle className="h-5 w-5 text-primary mt-0.5 flex-shrink-0" />
      <span>{text}</span>
    </li>
  )
}
