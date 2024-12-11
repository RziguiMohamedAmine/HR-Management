import { Employee } from "./employee";

export class Training {
    id!: number; 
    name!: string; 
    description!: string; 
    startDate!: string; // Start date in ISO string format
    endDate!: string; // End date in ISO string format
    attendees!: Set<Employee>; 
}
