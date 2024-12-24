import { Injectable } from '@angular/core';
import { Project } from '../models/project';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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

   getProjects(headers: HttpHeaders): Observable<Project[]> {
    return this.httpClient.get<Project[]>(`${this.baseUrl+this.afficher}`, { headers });
  }

  AddProject(E:Project,headers: HttpHeaders):Observable<Object>
  {
    return this.httpClient.post(`${this.baseUrl+this.ajouter}`,E,{headers});                        
  }

  getProjectById(id:number,headers: HttpHeaders):Observable<Project>
  {
    return this.httpClient.get<Project>(`${this.baseUrl+this.getById}/${id}`,{headers});
  }

  ModifierProject(id:number,p:Project,headers: HttpHeaders):Observable<Object>
  {
    p.idProjet = id;
    return this.httpClient.put<Project>(`${this.baseUrl+this.udpate}`,p,{headers});
  }

  deleteProject(id:number,headers: HttpHeaders):Observable<Object>
  {
    return this.httpClient.delete(`${this.baseUrl+this.delete}/${id}`,{headers});
  }

 
  getEmployees(projectId:number,headers: HttpHeaders): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(`${this.baseUrl}/${projectId}/employees`,{headers});
  }




}
