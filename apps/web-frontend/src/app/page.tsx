import Head from "next/head"
import Image from "next/image"
import Link from "next/link"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import {
  BookOpen,
  BriefcaseIcon,
  FileText,
  TrendingUp,
  Award,
  MessageSquare,
  Calendar,
  Zap,
  CheckCircle,
  Linkedin,
} from "lucide-react"

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

      {/* Hero Section */}
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

      {/* Stats Section */}
      <section className="py-12 bg-muted/50">
        <div className="container px-4 md:px-6 mx-auto">
          <div className="grid grid-cols-2 md:grid-cols-4 gap-8 text-center">
            <div className="space-y-2">
              <h3 className="text-3xl md:text-4xl font-bold text-primary">5000+</h3>
              <p className="text-muted-foreground">NTU Students</p>
            </div>
            <div className="space-y-2">
              <h3 className="text-3xl md:text-4xl font-bold text-primary">1200+</h3>
              <p className="text-muted-foreground">Job Opportunities</p>
            </div>
            <div className="space-y-2">
              <h3 className="text-3xl md:text-4xl font-bold text-primary">300+</h3>
              <p className="text-muted-foreground">Partner Companies</p>
            </div>
            <div className="space-y-2">
              <h3 className="text-3xl md:text-4xl font-bold text-primary">85%</h3>
              <p className="text-muted-foreground">Placement Rate</p>
            </div>
          </div>
        </div>
      </section>

      {/* Key Features Section */}
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
                    <li className="flex items-start gap-2">
                      <CheckCircle className="h-5 w-5 text-primary mt-0.5 flex-shrink-0" />
                      <span>Get personalized feedback on your resume from advanced LLMs</span>
                    </li>
                    <li className="flex items-start gap-2">
                      <CheckCircle className="h-5 w-5 text-primary mt-0.5 flex-shrink-0" />
                      <span>Automatically tailor your resume to match specific job descriptions</span>
                    </li>
                    <li className="flex items-start gap-2">
                      <CheckCircle className="h-5 w-5 text-primary mt-0.5 flex-shrink-0" />
                      <span>Identify and bridge skill gaps with targeted recommendations</span>
                    </li>
                    <li className="flex items-start gap-2">
                      <CheckCircle className="h-5 w-5 text-primary mt-0.5 flex-shrink-0" />
                      <span>Maintain multiple resume versions for different career paths</span>
                    </li>
                  </ul>
                  <Button className="mt-2">Improve Your Resume</Button>
                </div>
              </div>
            </TabsContent>
            <TabsContent value="jobs" className="space-y-4">
              <div className="grid md:grid-cols-2 gap-8 items-center">
                <div className="space-y-4 order-2 md:order-1">
                  <h3 className="text-2xl font-bold">Smart Job Recommendations</h3>
                  <ul className="space-y-3">
                    <li className="flex items-start gap-2">
                      <CheckCircle className="h-5 w-5 text-primary mt-0.5 flex-shrink-0" />
                      <span>Personalized job feed based on your skills and preferences</span>
                    </li>
                    <li className="flex items-start gap-2">
                      <CheckCircle className="h-5 w-5 text-primary mt-0.5 flex-shrink-0" />
                      <span>Real-time notifications for matching opportunities</span>
                    </li>
                    <li className="flex items-start gap-2">
                      <CheckCircle className="h-5 w-5 text-primary mt-0.5 flex-shrink-0" />
                      <span>Detailed company insights and culture information</span>
                    </li>
                    <li className="flex items-start gap-2">
                      <CheckCircle className="h-5 w-5 text-primary mt-0.5 flex-shrink-0" />
                      <span>Strategic internship application timeline</span>
                    </li>
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
            </TabsContent>
            <TabsContent value="network" className="space-y-4">
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
                    <li className="flex items-start gap-2">
                      <CheckCircle className="h-5 w-5 text-primary mt-0.5 flex-shrink-0" />
                      <span>Join interest-based groups for study sessions and workshops</span>
                    </li>
                    <li className="flex items-start gap-2">
                      <CheckCircle className="h-5 w-5 text-primary mt-0.5 flex-shrink-0" />
                      <span>Connect with alumni and industry mentors</span>
                    </li>
                    <li className="flex items-start gap-2">
                      <CheckCircle className="h-5 w-5 text-primary mt-0.5 flex-shrink-0" />
                      <span>Exchange feedback through peer review system</span>
                    </li>
                    <li className="flex items-start gap-2">
                      <CheckCircle className="h-5 w-5 text-primary mt-0.5 flex-shrink-0" />
                      <span>Discover and register for career events</span>
                    </li>
                  </ul>
                  <Button className="mt-2">Start Networking</Button>
                </div>
              </div>
            </TabsContent>
          </Tabs>
        </div>
      </section>

      {/* How It Works Section */}
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
            <Card>
              <CardHeader>
                <div className="w-12 h-12 rounded-full bg-primary/10 flex items-center justify-center mb-4">
                  <FileText className="h-6 w-6 text-primary" />
                </div>
                <CardTitle>1. Create Your Profile</CardTitle>
              </CardHeader>
              <CardContent>
                <p>
                  Upload your resume and set your preferences. Our AI will analyze your skills and experience to create
                  a personalized profile.
                </p>
              </CardContent>
            </Card>

            <Card>
              <CardHeader>
                <div className="w-12 h-12 rounded-full bg-primary/10 flex items-center justify-center mb-4">
                  <Zap className="h-6 w-6 text-primary" />
                </div>
                <CardTitle>2. Get Personalized Recommendations</CardTitle>
              </CardHeader>
              <CardContent>
                <p>
                  Receive tailored job recommendations, skill development suggestions, and networking opportunities
                  based on your profile.
                </p>
              </CardContent>
            </Card>

            <Card>
              <CardHeader>
                <div className="w-12 h-12 rounded-full bg-primary/10 flex items-center justify-center mb-4">
                  <TrendingUp className="h-6 w-6 text-primary" />
                </div>
                <CardTitle>3. Grow Your Career</CardTitle>
              </CardHeader>
              <CardContent>
                <p>
                  Apply to jobs, improve your skills, connect with peers and mentors, and track your progress all in one
                  platform.
                </p>
              </CardContent>
            </Card>
          </div>
        </div>
      </section>

      {/* Advanced Features Section */}
      <section className="py-20">
        <div className="container px-4 md:px-6 mx-auto">
          <div className="text-center mb-12">
            <h2 className="text-3xl md:text-4xl font-bold tracking-tight mb-4">Advanced Features</h2>
            <p className="text-xl text-muted-foreground max-w-[800px] mx-auto">
              Powered by cutting-edge AI and data analytics to give you the competitive edge.
            </p>
          </div>

          <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-6">
            <Card className="border-primary/20 hover:border-primary/50 transition-colors">
              <CardHeader>
                <BriefcaseIcon className="h-6 w-6 text-primary mb-2" />
                <CardTitle>Application Tracking</CardTitle>
                <CardDescription>Monitor all your job applications in one place</CardDescription>
              </CardHeader>
              <CardContent>
                <p>
                  Keep track of application statuses, interview schedules, and feedback to optimize your job search
                  strategy.
                </p>
              </CardContent>
            </Card>

            <Card className="border-primary/20 hover:border-primary/50 transition-colors">
              <CardHeader>
                <BookOpen className="h-6 w-6 text-primary mb-2" />
                <CardTitle>Skill Development</CardTitle>
                <CardDescription>Personalized learning recommendations</CardDescription>
              </CardHeader>
              <CardContent>
                <p>
                  Get suggestions for courses, tutorials, and projects based on your career goals and identified skill
                  gaps.
                </p>
              </CardContent>
            </Card>

            <Card className="border-primary/20 hover:border-primary/50 transition-colors">
              <CardHeader>
                <MessageSquare className="h-6 w-6 text-primary mb-2" />
                <CardTitle>Mock Interviews</CardTitle>
                <CardDescription>AI-powered interview preparation</CardDescription>
              </CardHeader>
              <CardContent>
                <p>
                  Practice with AI interviewers that provide feedback on your responses, communication skills, and body
                  language.
                </p>
              </CardContent>
            </Card>

            <Card className="border-primary/20 hover:border-primary/50 transition-colors">
              <CardHeader>
                <Award className="h-6 w-6 text-primary mb-2" />
                <CardTitle>Mentorship Program</CardTitle>
                <CardDescription>Connect with industry professionals</CardDescription>
              </CardHeader>
              <CardContent>
                <p>
                  Get guidance from experienced professionals and alumni who can help you navigate your career path.
                </p>
              </CardContent>
            </Card>

            <Card className="border-primary/20 hover:border-primary/50 transition-colors">
              <CardHeader>
                <Calendar className="h-6 w-6 text-primary mb-2" />
                <CardTitle>Event Hub</CardTitle>
                <CardDescription>Never miss important career events</CardDescription>
              </CardHeader>
              <CardContent>
                <p>
                  Discover and register for career fairs, workshops, and networking events relevant to your interests.
                </p>
              </CardContent>
            </Card>

            <Card className="border-primary/20 hover:border-primary/50 transition-colors">
              <CardHeader>
                <Linkedin className="h-6 w-6 text-primary mb-2" />
                <CardTitle>LinkedIn Integration</CardTitle>
                <CardDescription>Seamlessly connect your profiles</CardDescription>
              </CardHeader>
              <CardContent>
                <p>
                  Import your LinkedIn profile and connections to enhance your LinkediNTU experience and expand your
                  network.
                </p>
              </CardContent>
            </Card>
          </div>
        </div>
      </section>

      {/* Testimonials Section */}
      <section className="py-20 bg-muted/50">
        <div className="container px-4 md:px-6 mx-auto">
          <div className="text-center mb-12">
            <h2 className="text-3xl md:text-4xl font-bold tracking-tight mb-4">What Students Say</h2>
            <p className="text-xl text-muted-foreground max-w-[800px] mx-auto">
              Hear from NTU students who have transformed their career journey with LinkediNTU.
            </p>
          </div>

          <div className="grid md:grid-cols-3 gap-8">
            <Card className="bg-background">
              <CardHeader>
                <div className="flex items-center gap-4">
                  <div className="w-12 h-12 rounded-full bg-primary/20 overflow-hidden">
                    <Image
                      src="/placeholder.svg?height=48&width=48"
                      width={48}
                      height={48}
                      alt="Student"
                      className="object-cover"
                    />
                  </div>
                  <div>
                    <CardTitle className="text-lg">Sarah Chen</CardTitle>
                    <CardDescription>Computer Science, Year 3</CardDescription>
                  </div>
                </div>
              </CardHeader>
              <CardContent>
                <p className="italic">
                  "The AI resume feedback helped me completely transform my CV. I secured an internship at a top tech
                  company within two weeks of using the platform!"
                </p>
              </CardContent>
            </Card>

            <Card className="bg-background">
              <CardHeader>
                <div className="flex items-center gap-4">
                  <div className="w-12 h-12 rounded-full bg-primary/20 overflow-hidden">
                    <Image
                      src="/placeholder.svg?height=48&width=48"
                      width={48}
                      height={48}
                      alt="Student"
                      className="object-cover"
                    />
                  </div>
                  <div>
                    <CardTitle className="text-lg">Raj Patel</CardTitle>
                    <CardDescription>Business Administration, Year 4</CardDescription>
                  </div>
                </div>
              </CardHeader>
              <CardContent>
                <p className="italic">
                  "The networking features connected me with alumni in my dream industry. Their mentorship was
                  invaluable in helping me prepare for interviews and understand the industry."
                </p>
              </CardContent>
            </Card>

            <Card className="bg-background">
              <CardHeader>
                <div className="flex items-center gap-4">
                  <div className="w-12 h-12 rounded-full bg-primary/20 overflow-hidden">
                    <Image
                      src="/placeholder.svg?height=48&width=48"
                      width={48}
                      height={48}
                      alt="Student"
                      className="object-cover"
                    />
                  </div>
                  <div>
                    <CardTitle className="text-lg">Li Wei</CardTitle>
                    <CardDescription>Mechanical Engineering, Year 2</CardDescription>
                  </div>
                </div>
              </CardHeader>
              <CardContent>
                <p className="italic">
                  "The skill gap analysis showed me exactly what I needed to learn to be competitive. The recommended
                  courses helped me develop the right skills for my target roles."
                </p>
              </CardContent>
            </Card>
          </div>
        </div>
      </section>

      {/* CTA Section */}
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

      {/* Footer */}
      <footer className="py-12 bg-muted/80 border-t">
        <div className="container px-4 md:px-6 mx-auto">
          <div className="grid grid-cols-2 md:grid-cols-4 gap-8">
            <div className="space-y-4">
              <h3 className="text-lg font-semibold">LinkediNTU</h3>
              <ul className="space-y-2">
                <li>
                  <Link href="#" className="text-muted-foreground hover:text-foreground">
                    About Us
                  </Link>
                </li>
                <li>
                  <Link href="#" className="text-muted-foreground hover:text-foreground">
                    Our Team
                  </Link>
                </li>
                <li>
                  <Link href="#" className="text-muted-foreground hover:text-foreground">
                    Careers
                  </Link>
                </li>
                <li>
                  <Link href="#" className="text-muted-foreground hover:text-foreground">
                    Contact
                  </Link>
                </li>
              </ul>
            </div>
            <div className="space-y-4">
              <h3 className="text-lg font-semibold">Features</h3>
              <ul className="space-y-2">
                <li>
                  <Link href="#" className="text-muted-foreground hover:text-foreground">
                    Resume Improvement
                  </Link>
                </li>
                <li>
                  <Link href="#" className="text-muted-foreground hover:text-foreground">
                    Job Matching
                  </Link>
                </li>
                <li>
                  <Link href="#" className="text-muted-foreground hover:text-foreground">
                    Networking
                  </Link>
                </li>
                <li>
                  <Link href="#" className="text-muted-foreground hover:text-foreground">
                    Skill Development
                  </Link>
                </li>
              </ul>
            </div>
            <div className="space-y-4">
              <h3 className="text-lg font-semibold">Resources</h3>
              <ul className="space-y-2">
                <li>
                  <Link href="#" className="text-muted-foreground hover:text-foreground">
                    Blog
                  </Link>
                </li>
                <li>
                  <Link href="#" className="text-muted-foreground hover:text-foreground">
                    Guides
                  </Link>
                </li>
                <li>
                  <Link href="#" className="text-muted-foreground hover:text-foreground">
                    Events
                  </Link>
                </li>
                <li>
                  <Link href="#" className="text-muted-foreground hover:text-foreground">
                    FAQ
                  </Link>
                </li>
              </ul>
            </div>
            <div className="space-y-4">
              <h3 className="text-lg font-semibold">Legal</h3>
              <ul className="space-y-2">
                <li>
                  <Link href="#" className="text-muted-foreground hover:text-foreground">
                    Privacy Policy
                  </Link>
                </li>
                <li>
                  <Link href="#" className="text-muted-foreground hover:text-foreground">
                    Terms of Service
                  </Link>
                </li>
                <li>
                  <Link href="#" className="text-muted-foreground hover:text-foreground">
                    Cookie Policy
                  </Link>
                </li>
              </ul>
            </div>
          </div>
          <div className="mt-12 pt-8 border-t border-muted-foreground/20 text-center">
            <p className="text-muted-foreground">&copy; {new Date().getFullYear()} LinkediNTU. All rights reserved.</p>
          </div>
        </div>
      </footer>
    </div>
  )
}


