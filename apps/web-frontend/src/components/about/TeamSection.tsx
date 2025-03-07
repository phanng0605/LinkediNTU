// components/about/TeamSection.tsx

import Image from "next/image"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"

export function TeamSection() {
  const teamMembers = [
    {
      name: "Dr. Wei Lin",
      role: "Founder & CEO",
      image: "/placeholder.svg?height=300&width=300",
      bio: "Former NTU Computer Science professor with expertise in AI and machine learning.",
    },
    {
      name: "Sarah Tan",
      role: "CTO",
      image: "/placeholder.svg?height=300&width=300",
      bio: "NTU Computer Science graduate with experience at leading tech companies.",
    },
    {
      name: "Raj Mehta",
      role: "Head of Partnerships",
      image: "/placeholder.svg?height=300&width=300",
      bio: "Business School graduate with extensive industry connections across Singapore.",
    },
    {
      name: "Li Mei",
      role: "Lead UX Designer",
      image: "/placeholder.svg?height=300&width=300",
      bio: "Design expert focused on creating intuitive and accessible user experiences.",
    },
  ]

  return (
    <section className="py-16 md:py-24 bg-muted">
      <div className="container px-4 md:px-6 mx-auto">
        <h2 className="text-3xl font-bold tracking-tight mb-12 text-center">Meet Our Team</h2>
        <div className="grid sm:grid-cols-2 lg:grid-cols-4 gap-8">
          {teamMembers.map((member, index) => (
            <Card key={index} className="overflow-hidden border-0 bg-background shadow-md">
              <div className="aspect-square relative">
                <Image src={member.image || "/placeholder.svg"} alt={member.name} fill className="object-cover" />
              </div>
              <CardHeader className="pb-2">
                <CardTitle>{member.name}</CardTitle>
                <CardDescription>{member.role}</CardDescription>
              </CardHeader>
              <CardContent>
                <p className="text-muted-foreground">{member.bio}</p>
              </CardContent>
            </Card>
          ))}
        </div>
        <div className="mt-12 text-center">
          <p className="text-lg text-muted-foreground mb-6">
            Our team also includes talented developers, career advisors, and student ambassadors who are passionate
            about helping NTU students succeed.
          </p>
          <Button variant="outline">Join Our Team</Button>
        </div>
      </div>
    </section>
  )
}

