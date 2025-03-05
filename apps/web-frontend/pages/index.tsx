import React from "react";
import Head from "next/head";

const Home: React.FC = () => {
  return (
    <>
      {/* Sets the page <title> and meta tags */}
      <Head>
        <title>Welcome to My Landing Page</title>
        <meta
          name="description"
          content="A quick landing page using Next.js + TypeScript"
        />
      </Head>

      {/* Basic hero section */}
      <main style={styles.container}>
        <div style={styles.hero}>
          <h1 style={styles.title}>Welcome to LinkediNTU</h1>
          <p style={styles.subtitle}>
            Your gateway to building a professional network and finding
            internships.
          </p>
          <button style={styles.ctaButton}>Get Started</button>
        </div>
      </main>
    </>
  );
};

export default Home;

// Example inline styles for quick prototyping (you can replace with CSS modules or styled-components).
const styles: { [key: string]: React.CSSProperties } = {
  container: {
    display: "flex",
    flexDirection: "column",
    minHeight: "100vh",
    fontFamily: "sans-serif",
    margin: 0,
    padding: 0,
  },
  hero: {
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    alignItems: "center",
    flexGrow: 1,
  },
  title: {
    fontSize: "2.5rem",
    marginBottom: "1rem",
    textAlign: "center",
  },
  subtitle: {
    fontSize: "1.2rem",
    maxWidth: "600px",
    textAlign: "center",
    marginBottom: "2rem",
  },
  ctaButton: {
    backgroundColor: "#0070f3",
    color: "#fff",
    padding: "0.75rem 1.5rem",
    border: "none",
    borderRadius: "4px",
    fontSize: "1rem",
    cursor: "pointer",
  },
};

