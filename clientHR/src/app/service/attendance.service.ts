import { Injectable } from '@angular/core';
import { Attendance } from '../models/attendance';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AttendanceService {

  public searchText!: string;

  private baseUrl ="http://localhost:8008/HrMangement/Présence";
  private afficher = "/afficherAllPrésence";
  private ajouter = "/ajouterPrésence";
  private getEmployeeAllAtteandances = "/afficherAllPrésence";
  private arrival = "/updateEmployee"; 
  private departure = "/arrival"; 
  private delete = "/departure";
  private getAllDayPresence = "/getAllDayPresence";
 
  attendance!:Attendance[];

  constructor(private httpClient:HttpClient) { 

  }

  getAllPresences(headers: HttpHeaders):Observable<Attendance[]> {
    return this.httpClient.get<Attendance[]>(`${this.baseUrl+this.afficher}`,{ headers });
  }

  getAttendanceByMonthAndYear(month: number, year: number,headers: HttpHeaders): Observable<Attendance[]> {
    return this.httpClient.get<Attendance[]>(`${this.baseUrl}?year=${year}&month=${month}`,{ headers });
  }



}
