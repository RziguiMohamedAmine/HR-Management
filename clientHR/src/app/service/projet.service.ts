import { Injectable } from '@angular/core';
import { Project } from '../models/project';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../models/employee';

@Injectable({
  providedIn: 'root'
})
export class ProjetService {

  private baseUrl ="http://localhost:8008/HrManagement/Project";
  private afficher = "/afficherAllProject";
  private ajouter = "/ajouterProject";
  private getById = "/afficherProjet";
  private udpate = "/updateProject"; 
  private delete = "/deleteProject";
 
  projects!:Project[];


  constructor(private httpClient:HttpClient) {
    
   }

   getProjects(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(`${this.baseUrl+this.afficher}`);
  }

  AddProject(E:Project):Observable<Object>
  {
    return this.httpClient.post(`${this.baseUrl+this.ajouter}`,E);                        
  }

  getProjectById(id:number):Observable<Project>
  {
    return this.httpClient.get<Project>(`${this.baseUrl+this.getById}/${id}`);
  }

  ModifierProject(id:number,p:Project):Observable<Object>
  {
    p.idProjet = id;
    return this.httpClient.put<Project>(`${this.baseUrl+this.udpate}`,p);
  }

  deleteProject(id:number):Observable<Object>
  {
    return this.httpClient.delete(`${this.baseUrl+this.delete}/${id}`);
  }

 
  getEmployees(projectId:number): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(`${this.baseUrl}/${projectId}/employees`);
  }




}
