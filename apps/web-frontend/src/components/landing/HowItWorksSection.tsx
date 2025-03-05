// components/landing/HowItWorksSection.tsx

import { FileText, Zap, TrendingUp } from "lucide-react"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export function HowItWorksSection() {
  return (
    <section className="py-20 bg-muted">
      <div className="container px-4 md:px-6 mx-auto">
        <div className="text-center mb-12">
          <h2 className="text-3xl md:text-4xl font-bold tracking-tight mb-4">How LinkediNTU Works</h2>
          <p className="text-xl text-muted-foreground max-w-[800px] mx-auto">
            Our platform uses advanced AI and personalized recommendations to connect you with the right
            opportunities.
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8">
          <HowItWorksCard
            icon={<FileText className="h-6 w-6 text-primary" />}
            title="1. Create Your Profile"
            description="Upload your resume and set your preferences. Our AI will analyze your skills and experience to create a personalized profile."
          />
          <HowItWorksCard
            icon={<Zap className="h-6 w-6 text-primary" />}
            title="2. Get Personalized Recommendations"
            description="Receive tailored job recommendations, skill development suggestions, and networking opportunities based on your profile."
          />
          <HowItWorksCard
            icon={<TrendingUp className="h-6 w-6 text-primary" />}
            title="3. Grow Your Career"
            description="Apply to jobs, improve your skills, connect with peers and mentors, and track your progress all in one platform."
          />
        </div>
      </div>
    </section>
  )
}

function HowItWorksCard({
  icon,
  title,
  description,
}: {
  icon: React.ReactNode
  title: string
  description: string
}) {
  return (
    <Card>
      <CardHeader>
        <div className="w-12 h-12 rounded-full bg-primary/10 flex items-center justify-center mb-4">
          {icon}
        </div>
        <CardTitle>{title}</CardTitle>
      </CardHeader>
      <CardContent>
        <p>{description}</p>
      </CardContent>
    </Card>
  )
}
