import { Injectable } from '@angular/core';
import { Employee } from '../models/employee';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Training } from '../models/training';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  public searchText!: string;

  private baseUrl ="http://localhost:8008/HrMangement/Employee";
  private afficher = "/afficherAllEmployee";
  private ajouter = "/ajouterEnseignant";
  private getById = "/afficherEmployee";
  private udpate = "/updateEmployee"; 
  private delete = "/deleteEmployee";
 
  employees!:Employee[];

  constructor(private httpClient:HttpClient) { 
    // this.httpClient.get('http://localhost:8008/Enseignant/afficheAllEnseignants')
    // .subscribe((data : any)=>{      
    //   this.employees=data
    //   console.log(this.employees[1].email)
    // });
   }
  
   getEmployees(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(`${this.baseUrl+this.afficher}`);
  }

  AddEmployee(E:Employee):Observable<Object>
  {
    return this.httpClient.post(`${this.baseUrl+this.ajouter}`,E);                        
  }

  getEnseignantById(id:number):Observable<Employee>
  {
    return this.httpClient.get<Employee>(`${this.baseUrl+this.getById}/${id}`);
  }

  ModifierEnseignant(id:number,emp:Employee):Observable<Object>
  {
    emp.idEmployee = id;
    return this.httpClient.put<Employee>(`${this.baseUrl+this.udpate}`,emp);
  }

  deleteEmployee(id:number):Observable<Object>
  {
    return this.httpClient.delete(`${this.baseUrl+this.delete}/${id}`);
  }

  getEmployeeTrainingClasses(id:number):Observable<Object>
  {
    return this.httpClient.get<Training[]>(`${this.baseUrl}/${id}/training-classes`);
  }


}
