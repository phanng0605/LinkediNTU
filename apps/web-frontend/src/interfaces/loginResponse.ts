export default interface LoginResponse {
    token: string,
    role: "ADMIN" | "USER",
    username: string,
    email: string
}