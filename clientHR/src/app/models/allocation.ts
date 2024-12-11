import { Employee } from "./employee";
import { Material } from "./material";

export class Allocation {
    id!: number;
    material!: Material;
    employee!: Employee;
    quantityAllocated!: number;
    allocationDate!: string;
}
