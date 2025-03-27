export default interface Response <T> {
    status: "SUCCESS" | "ERROR",
    message: string,
    data: T
}