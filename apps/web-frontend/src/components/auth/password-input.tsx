"use client"

import { useState } from "react"
import { Input } from "@/components/ui/input"
import { Eye, EyeOff } from "lucide-react"


export default function PasswordInput({ id, placeholder = "••••••••", required = true, ...props }: React.ComponentProps<"input">) {
  const [showPassword, setShowPassword] = useState(false)

  return (
    <div className="relative">
      <Input id={id} type={showPassword ? "text" : "password"} placeholder={placeholder} required={required} {...props}/>
      <button
        type="button"
        className="absolute right-3 top-1/2 -translate-y-1/2 text-muted-foreground"
        onClick={() => setShowPassword(!showPassword)}
      >
        {showPassword ? <EyeOff className="h-4 w-4" /> : <Eye className="h-4 w-4" />}
        <span className="sr-only">{showPassword ? "Hide password" : "Show password"}</span>
      </button>
    </div>
  )
}


