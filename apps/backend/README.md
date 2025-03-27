# NTU LinkediNTU

# Pre-requisites

1. Java 11
2. Maven (Install from https://maven.apache.org/download.cgi)

# How to run

1. Clone the repository
2. Run `mvn clean install`

# Testing

Run `mvn test`

Or to run with a specific test file, run `mvn test -Dtest=TestClassName`
E.g: `mvn test -Dtest=AccountTestV1`

# Running the application
`mvn spring-boot:run`

It will automatically runs the server on port 8000

# Frontend integration

1. Clone the frontend repository from
2. Run `pnpm install`
3. Config the proxy to point to the backend server
4. Run `pnpm dev`