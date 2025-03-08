import Link from "next/link"
import Image from "next/image"
import type { ReactNode } from "react"

interface AuthLayoutProps {
  children: ReactNode
}

export default function AuthLayout({ children }: AuthLayoutProps) {
  return (
    <div className="min-h-screen flex flex-col">
      <div className="flex-1 flex items-center justify-center p-4 md:p-8">
        <div className="w-full max-w-md">
          <div className="text-center mb-8">
            <Link href="/" className="inline-block">
              <div className="flex items-center justify-center gap-2">
                <Image src="/placeholder.svg?height=40&width=40" width={40} height={40} alt="LinkediNTU Logo" />
                <span className="text-2xl font-bold">LinkediNTU</span>
              </div>
            </Link>
          </div>

          {children}
        </div>
      </div>

      <footer className="py-6 border-t">
        <div className="container px-4 md:px-6 mx-auto">
          <div className="flex flex-col sm:flex-row items-center justify-between gap-4">
            <p className="text-sm text-muted-foreground">
              &copy; {new Date().getFullYear()} LinkediNTU. All rights reserved.
            </p>
            <div className="flex items-center gap-4">
              <Link href="/privacy" className="text-sm text-muted-foreground hover:text-foreground">
                Privacy
              </Link>
              <Link href="/terms" className="text-sm text-muted-foreground hover:text-foreground">
                Terms
              </Link>
              <Link href="/contact" className="text-sm text-muted-foreground hover:text-foreground">
                Contact
              </Link>
            </div>
          </div>
        </div>
      </footer>
    </div>
  )
}


