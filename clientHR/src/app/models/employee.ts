import { Allocation } from "./allocation";
import { Attendance } from "./attendance";
import { Contract } from "./contract";
import { Document } from "./document";
import { Fonction } from "./fonction";
import { Leaves } from "./leaves";
import { PaymentHistories } from "./payment-histories";
import { Project } from "./project";
import { Salary } from "./salary";
import { Training } from "./training";

export class Employee {
  
    idEmployee!: number;
    prenom!: string;
    nom!: string;
    mobile!: string;
    email!: string;
    image!: string;
    addresse!: string;
    soldecongé!: number; 
    fonction!: Fonction;  
    
    allocations!:Set<Allocation>;  
    leaves!: Set<Leaves>;
    projet!: Project;
    attendances!: Set<Attendance>;
    paymentHistories!: Set<PaymentHistories>;
    salary!: Salary;
    managedProject!: Project;
    documents!: Set<Document>;
    contract !:Set<Contract>;
    trainingClasses!: Set<Training>;
}
