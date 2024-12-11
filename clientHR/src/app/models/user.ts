import { Role } from "./role";

export class User {
    id!: number;
    firstname!: string;
    lastname!: string;
    email!: string;
    password!: string;  // Typically you wouldn't send this to the frontend in a secure application
    Role!: Role;  
}
