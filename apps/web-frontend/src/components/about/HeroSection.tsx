import { Badge } from "@/components/ui/badge";

export function HeroSection() {
  return (
    <section className="relative bg-gradient-to-r from-primary/90 to-primary py-16 md:py-24">
      <div className="container px-4 md:px-6 mx-auto">
        <div className="max-w-3xl mx-auto text-center">
          <h1 className="text-3xl md:text-5xl font-bold tracking-tighter text-white mb-4">About LinkediNTU</h1>
          <p className="text-xl text-white/90 mb-6">
            Empowering NTU students to build successful careers through technology, community, and innovation.
          </p>
          <Badge variant="secondary" className="bg-white/20 text-white hover:bg-white/30">
            Established 2023
          </Badge>
        </div>
      </div>
      <div className="absolute bottom-0 left-0 right-0 h-16 bg-gradient-to-t from-background to-transparent"></div>
    </section>
  );
}