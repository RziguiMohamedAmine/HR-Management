import { Injectable } from '@angular/core';
import { Employee } from '../models/employee';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Training } from '../models/training';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  public searchText!: string;

  private baseUrl ="http://localhost:8008/HrMangement/Employee";
  private afficher = "/afficherAllEmployee";
  private ajouter = "/ajouterEmployee";
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

   getEmployees(headers: HttpHeaders): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(this.baseUrl + this.afficher, { headers });
  }
  

  AddEmployee(E:Employee,headers: HttpHeaders):Observable<Object>
  {
    return this.httpClient.post(`${this.baseUrl+this.ajouter}`,E,{headers});
  }

  getEmployeeById(id:number,headers: HttpHeaders):Observable<Employee>
  {
    return this.httpClient.get<Employee>(this.baseUrl+this.getById+`/${id}`,{headers});
  }

  ModifierEmployee(id:number,emp:Employee,headers: HttpHeaders):Observable<Object>
  {
    emp.idEmployee = id;
    return this.httpClient.put<Employee>(`${this.baseUrl+this.udpate}`,emp,{headers});
  }

  deleteEmployee(id:number,headers: HttpHeaders):Observable<Object>
  {
    return this.httpClient.delete(`${this.baseUrl+this.delete}/${id}`,{headers});
  }

  getEmployeeTrainingClasses(id:number,headers: HttpHeaders):Observable<Object>
  {
    return this.httpClient.get<Training[]>(`${this.baseUrl}/${id}/training-classes`,{headers});
  }


}
