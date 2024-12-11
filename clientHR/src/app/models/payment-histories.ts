import { Employee } from "./employee";

export class PaymentHistories {
  id!: number;
  baseSalary!: number;
  bonuses!: number;
  deductions!: number;
  totalPaid!: number; // Final amount paid after bonuses and deductions
  paymentDate!: string; // ISO 8601 date string (e.g., "2024-12-11")
  employee!: Employee; 
}
