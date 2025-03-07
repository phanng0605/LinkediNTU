import Image from "next/image";

export function MissionVisionSection() {
  return (
    <section className="py-16 md:py-24">
      <div className="container px-4 md:px-6 mx-auto">
        <div className="grid md:grid-cols-2 gap-12 items-center">
          <div>
            <Image
              src="/placeholder.svg?height=400&width=500"
              width={500}
              height={400}
              alt="LinkediNTU mission"
              className="rounded-lg shadow-xl"
            />
          </div>
          <div className="space-y-6">
            <div>
              <h2 className="text-3xl font-bold tracking-tight mb-4">Our Mission</h2>
              <p className="text-lg text-muted-foreground">
                To bridge the gap between academic learning and professional success by providing NTU students with
                cutting-edge tools, personalized guidance, and a supportive community that empowers them to thrive in
                their careers.
              </p>
            </div>
            <div>
              <h2 className="text-3xl font-bold tracking-tight mb-4">Our Vision</h2>
              <p className="text-lg text-muted-foreground">
                To become the definitive career platform for NTU students, where every student can discover their
                professional path, develop essential skills, and connect with opportunities that align with their
                aspirations and potential.
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}