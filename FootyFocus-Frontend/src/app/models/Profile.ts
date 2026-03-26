export interface Profile {
    id?: number;
    firstname: string;
    lastname: string;
    email: string;
    password?: string; // Optional or use if updating the password
}