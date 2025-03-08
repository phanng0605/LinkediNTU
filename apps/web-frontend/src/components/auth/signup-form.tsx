"use client"

import type React from "react"

import { useState } from "react"
import Link from "next/link"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Checkbox } from "@/components/ui/checkbox"
import { Separator } from "@/components/ui/separator"
import PasswordInput from "./password-input"
import SocialButtons from "./social-buttons"

export default function SignupForm() {
  const [isLoading, setIsLoading] = useState(false)

  const handleSignup = (e: React.FormEvent) => {
    e.preventDefault()
    setIsLoading(true)
    // Simulate API call
    setTimeout(() => {
      setIsLoading(false)
      // Handle signup logic here
    }, 1500)
  }

  return (
    <Card>
      <CardHeader>
        <CardTitle>Create an account</CardTitle>
        <CardDescription>Join LinkediNTU to access personalized career resources and opportunities</CardDescription>
      </CardHeader>
      <CardContent>
        <form onSubmit={handleSignup} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div className="space-y-2">
              <Label htmlFor="firstName">First name</Label>
              <Input id="firstName" placeholder="John" required />
            </div>
            <div className="space-y-2">
              <Label htmlFor="lastName">Last name</Label>
              <Input id="lastName" placeholder="Doe" required />
            </div>
          </div>
          <div className="space-y-2">
            <Label htmlFor="signupEmail">Email</Label>
            <Input id="signupEmail" type="email" placeholder="your.name@e.ntu.edu.sg" required />
            <p className="text-xs text-muted-foreground">Please use your NTU email address</p>
          </div>
          <div className="space-y-2">
            <Label htmlFor="signupPassword">Password</Label>
            <PasswordInput id="signupPassword" />
            <p className="text-xs text-muted-foreground">
              Must be at least 8 characters with a number and a special character
            </p>
          </div>
          <div className="space-y-2">
            <Label htmlFor="confirmPassword">Confirm password</Label>
            <PasswordInput id="confirmPassword" />
          </div>
          <div className="flex items-center space-x-2">
            <Checkbox id="terms" required />
            <Label htmlFor="terms" className="text-sm">
              I agree to the{" "}
              <Link href="/terms" className="text-primary hover:underline">
                Terms of Service
              </Link>{" "}
              and{" "}
              <Link href="/privacy" className="text-primary hover:underline">
                Privacy Policy
              </Link>
            </Label>
          </div>
          <Button type="submit" className="w-full" disabled={isLoading}>
            {isLoading ? "Creating account..." : "Create account"}
          </Button>
        </form>

        <div className="mt-6">
          <div className="relative">
            <div className="absolute inset-0 flex items-center">
              <Separator />
            </div>
            <div className="relative flex justify-center text-xs uppercase">
              <span className="bg-background px-2 text-muted-foreground">Or continue with</span>
            </div>
          </div>

          <div className="mt-6">
            <SocialButtons isLoading={isLoading} />
          </div>
        </div>
      </CardContent>
      <CardFooter className="flex justify-center">
        <p className="text-sm text-muted-foreground">
          Already have an account?{" "}
          <button
            type="button"
            className="text-primary hover:underline"
            onClick={() => document.querySelector('[data-value="login"]')?.click()}
          >
            Log in
          </button>
        </p>
      </CardFooter>
    </Card>
  )
}


