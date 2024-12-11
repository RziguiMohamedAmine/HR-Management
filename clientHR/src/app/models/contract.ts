import { Employee } from "./employee";

export class Contract {
    id!: number; 
    employee!: Employee; 
    startDate!: string; 
    endDate!: string; 
    terms!: string; 
}
