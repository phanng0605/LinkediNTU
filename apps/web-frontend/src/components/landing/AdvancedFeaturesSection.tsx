// components/landing/AdvancedFeaturesSection.tsx

import {
    BookOpen,
    BriefcaseIcon,
    MessageSquare,
    Award,
    Calendar,
    Linkedin,
  } from "lucide-react"
  import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
  
  export function AdvancedFeaturesSection() {
    return (
      <section className="py-20">
        <div className="container px-4 md:px-6 mx-auto">
          <div className="text-center mb-12">
            <h2 className="text-3xl md:text-4xl font-bold tracking-tight mb-4">Advanced Features</h2>
            <p className="text-xl text-muted-foreground max-w-[800px] mx-auto">
              Powered by cutting-edge AI and data analytics to give you the competitive edge.
            </p>
          </div>
  
          <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-6">
            <AdvancedFeatureCard
              icon={<BriefcaseIcon className="h-6 w-6 text-primary mb-2" />}
              title="Application Tracking"
              description="Monitor all your job applications in one place"
              content="Keep track of application statuses, interview schedules, and feedback to optimize your job search strategy."
            />
            <AdvancedFeatureCard
              icon={<BookOpen className="h-6 w-6 text-primary mb-2" />}
              title="Skill Development"
              description="Personalized learning recommendations"
              content="Get suggestions for courses, tutorials, and projects based on your career goals and identified skill gaps."
            />
            <AdvancedFeatureCard
              icon={<MessageSquare className="h-6 w-6 text-primary mb-2" />}
              title="Mock Interviews"
              description="AI-powered interview preparation"
              content="Practice with AI interviewers that provide feedback on your responses, communication skills, and body language."
            />
            <AdvancedFeatureCard
              icon={<Award className="h-6 w-6 text-primary mb-2" />}
              title="Mentorship Program"
              description="Connect with industry professionals"
              content="Get guidance from experienced professionals and alumni who can help you navigate your career path."
            />
            <AdvancedFeatureCard
              icon={<Calendar className="h-6 w-6 text-primary mb-2" />}
              title="Event Hub"
              description="Never miss important career events"
              content="Discover and register for career fairs, workshops, and networking events relevant to your interests."
            />
            <AdvancedFeatureCard
              icon={<Linkedin className="h-6 w-6 text-primary mb-2" />}
              title="LinkedIn Integration"
              description="Seamlessly connect your profiles"
              content="Import your LinkedIn profile and connections to enhance your LinkediNTU experience and expand your network."
            />
          </div>
        </div>
      </section>
    )
  }
  
  function AdvancedFeatureCard({
    icon,
    title,
    description,
    content,
  }: {
    icon: React.ReactNode
    title: string
    description: string
    content: string
  }) {
    return (
      <Card className="border-primary/20 hover:border-primary/50 transition-colors">
        <CardHeader>
          {icon}
          <CardTitle>{title}</CardTitle>
          <CardDescription>{description}</CardDescription>
        </CardHeader>
        <CardContent>
          <p>{content}</p>
        </CardContent>
      </Card>
    )
  }
  