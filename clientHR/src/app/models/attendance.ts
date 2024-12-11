import { Employee } from "./employee";

export class Attendance {
  id!: number;
  arrivalTime!: string; // ISO 8601 format string (e.g., "2024-12-11T09:00:00")
  departureTime!: string; 
  totalTimeWorked!: string; 
  day!: any; 
  employee!: Employee; 
  absent!: boolean; 
  justifiedAbsence!: boolean; 
  justification!: string; 
}
