import { Employee } from "./employee";
import { Priority } from "./priority";
import { Status } from "./status";
import { Task } from "./task";

export class Project {
  idProjet!: number;
  projectName!: string; 
  description!: string; 
  dateDebutProjet!: string; // Start date of the project in ISO string format
  deadLine!: string; 
  status!: Status;
  priority!: Priority; 
  employees!: Set<Employee>; 
  attachments!: Set<string>; 
  tasks!: Set<Task>; 
  teamManager!: Employee; 
}
