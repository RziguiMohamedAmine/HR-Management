import { Project } from "./project";
import { Status } from "./status";

export class Task {
    idTask!: number; 
    description!: string; 
    status!: Status; 
    project!: Project; 
}
