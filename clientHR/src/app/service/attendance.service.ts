import { Injectable } from '@angular/core';
import { Attendance } from '../models/attendance';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AttendanceService {

  public searchText!: string;

  private baseUrl ="http://localhost:8008/Présence";
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

  getAllPresences():Observable<Attendance[]> {
    return this.httpClient.get<Attendance[]>(`${this.baseUrl+this.afficher}`);
  }

  getAttendanceByMonthAndYear(month: number, year: number): Observable<Attendance[]> {
    return this.httpClient.get<Attendance[]>(`${this.baseUrl}?year=${year}&month=${month}`);
  }



}
