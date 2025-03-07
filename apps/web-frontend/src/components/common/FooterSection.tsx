// components/landing/FooterSection.tsx

import Link from "next/link"

export function FooterSection() {
  return (
    <footer className="py-12 bg-muted/80 border-t">
      <div className="container px-4 md:px-6 mx-auto">
        <div className="grid grid-cols-2 md:grid-cols-4 gap-8">
          <FooterColumn
            title="LinkediNTU"
            links={["About Us", "Our Team", "Careers", "Contact"]}
          />
          <FooterColumn
            title="Features"
            links={["Resume Improvement", "Job Matching", "Networking", "Skill Development"]}
          />
          <FooterColumn
            title="Resources"
            links={["Blog", "Guides", "Events", "FAQ"]}
          />
          <FooterColumn
            title="Legal"
            links={["Privacy Policy", "Terms of Service", "Cookie Policy"]}
          />
        </div>
        <div className="mt-12 pt-8 border-t border-muted-foreground/20 text-center">
          <p className="text-muted-foreground">&copy; {new Date().getFullYear()} LinkediNTU. All rights reserved.</p>
        </div>
      </div>
    </footer>
  )
}

function FooterColumn({ title, links }: { title: string; links: string[] }) {
  return (
    <div className="space-y-4">
      <h3 className="text-lg font-semibold">{title}</h3>
      <ul className="space-y-2">
        {links.map((linkText) => (
          <li key={linkText}>
            <Link href="#" className="text-muted-foreground hover:text-foreground">
              {linkText}
            </Link>
          </li>
        ))}
      </ul>
    </div>
  )
}
