import { Employee } from "./employee";
import { Motif } from "./motif";

export class Leaves {
    id!: number; 
    dateDebutConge!: string; // Date in Java maps to ISO 8601 string or c
    dateFinConge!: string; // Same as above
    valide!: boolean; 
    motif!: Motif; 
    employee!: Employee;
}
