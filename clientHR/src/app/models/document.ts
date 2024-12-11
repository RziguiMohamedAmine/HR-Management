import { Employee } from "./employee";

export class Document {
    id!: number;
    employee!: Employee;
    fileName!: string; 
    fileType!: string;
    fileData!: string; // Base64-encoded string for file data (from byte array in Java)
    uploadedDate!: string; // ISO 8601 string or Date object
}
