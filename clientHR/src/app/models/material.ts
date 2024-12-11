import { Allocation } from "./allocation";
import { MatStatus } from "./mat-status";

export class Material {
  idMat!: number; 
  name!: string; 
  image!: string; 
  quantity!: number;
  status!: MatStatus; 
  cost!: number; 
  allocations!: Set<Allocation>; 
}
