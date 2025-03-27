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
import CryptoJS from "crypto-js"
import Response from "@/interfaces/response"
import LoginResponse from "@/interfaces/loginResponse"

export default function LoginForm() {
  const [isLoading, setIsLoading] = useState(false)

  const handleLogin = (e: React.FormEvent<HTMLFormElement>) => {
      e.preventDefault();
      
      setIsLoading(true);
      // Simulate API call
      const form = e.target as HTMLFormElement;
      const formData = new FormData(form);
      const email = formData.get("email") as string;
      const password = formData.get("password") as string;
      const rememberLogin = formData.get("remember") === "on";

      
      const hashedPassword = CryptoJS.SHA256(password).toString();
      console.log({ email, hashedPassword, rememberLogin });

      fetch(`/api/auth/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ 
          'email': email,
          'password': hashedPassword,
          'rememberMe': rememberLogin
         })
      }).then(response => {
        return response.json()
      }).then(data => {
        console.log(data as Response<LoginResponse>)
        if (data.status === "SUCCESS") {
          console.log(data.data);
          alert("Login successfully");
          (async () => {
            await new Promise(resolve => setTimeout(resolve, 1000));
            // window.location.href = "/";
          })();
        } else {
          alert("An error occurred: " + data.message);
        }
      }).catch((error) => {
        console.error('Error:', error);
        setIsLoading(false);
        alert("An error occurred: " + error)
      }).finally(() => {
        setIsLoading(false);
      });
      
    }

  return (
    <Card>
      <CardHeader>
        <CardTitle>Welcome back</CardTitle>
        <CardDescription>Log in to your account to access your personalized career resources</CardDescription>
      </CardHeader>
      <CardContent>
        <form onSubmit={handleLogin} className="space-y-4">
          <div className="space-y-2">
            <Label htmlFor="email">Email</Label>
            <Input id="email" type="email" name="email" placeholder="your.name@e.ntu.edu.sg" required />
          </div>
          <div className="space-y-2">
            <div className="flex items-center justify-between">
              <Label htmlFor="password">Password</Label>
              <Link href="/forgot-password" className="text-sm text-primary hover:underline">
                Forgot password?
              </Link>
            </div>
            <PasswordInput id="password" name="password" />
          </div>
          <div className="flex items-center space-x-2">
            <Checkbox id="remember" name="remember"/>
            <Label htmlFor="remember" className="text-sm">
              Remember me for 30 days
            </Label>
          </div>
          <Button type="submit" className="w-full" disabled={isLoading}>
            {isLoading ? "Logging in..." : "Log in"}
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
          Don't have an account?{" "}
          <button
            type="button"
            className="text-primary hover:underline"
            onClick={() => (document.querySelector('[data-value="signup"]') as HTMLElement)?.click()}
          >
            Sign up
          </button>
        </p>
      </CardFooter>
    </Card>
  )
}


