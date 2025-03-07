import Head from "next/head"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import AuthLayout from "@/components/auth/auth-layout"
import LoginForm from "@/components/auth/login-form"
import SignupForm from "@/components/auth/signup-form"

export default function AuthPage() {
  return (
    <>
      <Head>
        <title>Login or Sign Up - LinkediNTU</title>
        <meta
          name="description"
          content="Log in to your LinkediNTU account or sign up to access career resources, job matching, and networking opportunities."
        />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <AuthLayout>
        <Tabs defaultValue="login" className="w-full">
          <TabsList className="grid grid-cols-2 w-full mb-6">
            <TabsTrigger value="login">Login</TabsTrigger>
            <TabsTrigger value="signup">Sign Up</TabsTrigger>
          </TabsList>

          <TabsContent value="login">
            <LoginForm />
          </TabsContent>

          <TabsContent value="signup">
            <SignupForm />
          </TabsContent>
        </Tabs>
      </AuthLayout>
    </>
  )
}


