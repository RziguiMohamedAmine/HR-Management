import { Employee } from "./employee";
import { FrequencyPay } from "./frequency-pay";

export class Salary {
    id!: number;
    baseSalary!: number;
    bonuses!: number;
    paymentFrequency!: FrequencyPay; // Enum or string representing the payment frequency
    deductions!: number;
    paymentValidated!: boolean; // Whether the salary is validated for the month
    lastPaymentDate!: string; // ISO 8601 date string (e.g., "2024-12-11")
    employee!: Employee; // 
}
