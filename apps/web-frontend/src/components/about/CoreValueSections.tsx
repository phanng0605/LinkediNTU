import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Users, Target, BookOpen, Heart } from "lucide-react";

export function CoreValuesSection() {
  return (
    <section className="py-16 md:py-24">
      <div className="container px-4 md:px-6 mx-auto">
        <h2 className="text-3xl font-bold tracking-tight mb-12 text-center">Our Core Values</h2>
        <div className="grid sm:grid-cols-2 lg:grid-cols-4 gap-8">
          {[
            { icon: Users, title: "Community First", description: "We believe in the power of community..." },
            { icon: Target, title: "Personalization", description: "Every student's career journey is unique..." },
            { icon: BookOpen, title: "Continuous Learning", description: "We embrace a growth mindset..." },
            { icon: Heart, title: "Inclusivity", description: "We're dedicated to creating an inclusive platform..." },
          ].map((value, index) => (
            <Card key={index} className="bg-background border-primary/20 hover:border-primary/50 transition-colors">
              <CardHeader className="pb-2">
                <div className="w-12 h-12 rounded-full bg-primary/10 flex items-center justify-center mb-4">
                  <value.icon className="h-6 w-6 text-primary" />
                </div>
                <CardTitle>{value.title}</CardTitle>
              </CardHeader>
              <CardContent>
                <p>{value.description}</p>
              </CardContent>
            </Card>
          ))}
        </div>
      </div>
    </section>
  );
}