"use client"

import Image from "next/image"
import { Button } from "@/components/ui/button"
import { Linkedin } from "lucide-react"

interface SocialButtonsProps {
  isLoading?: boolean
}

export default function SocialButtons({ isLoading = false }: SocialButtonsProps) {
  return (
    <div className="grid grid-cols-2 gap-4">
      <Button variant="outline" type="button" disabled={isLoading}>
        <Image src="/placeholder.svg?height=24&width=24" width={24} height={24} alt="Google" className="mr-2 h-4 w-4" />
        Google
      </Button>
      <Button variant="outline" type="button" disabled={isLoading}>
        <Linkedin className="mr-2 h-4 w-4" />
        LinkedIn
      </Button>
    </div>
  )
}


