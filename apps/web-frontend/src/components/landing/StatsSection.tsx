// components/landing/StatsSection.tsx

export function StatsSection() {
    return (
      <section className="py-12 bg-muted/50">
        <div className="container px-4 md:px-6 mx-auto">
          <div className="grid grid-cols-2 md:grid-cols-4 gap-8 text-center">
            <StatItem value="5000+" label="NTU Students" />
            <StatItem value="1200+" label="Job Opportunities" />
            <StatItem value="300+" label="Partner Companies" />
            <StatItem value="85%" label="Placement Rate" />
          </div>
        </div>
      </section>
    )
  }
  
  function StatItem({ value, label }: { value: string; label: string }) {
    return (
      <div className="space-y-2">
        <h3 className="text-3xl md:text-4xl font-bold text-primary">{value}</h3>
        <p className="text-muted-foreground">{label}</p>
      </div>
    )
  }
  