import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, Observable, of } from 'rxjs';
import { environment } from '../../environments/environment';

const AUTH_API = 'http://localhost:8008/HrMangement/auth/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = environment.apiUrl;
  constructor(private http: HttpClient) { }

  login(credentials: { email: string; password: string }): Observable<any> {
    return this.http.post(this.apiUrl + '/auth/authenticate',credentials,
      httpOptions
    );
  }

  getUserDetailsFromToken(token: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/auth/session/${token}`);
  }
  
  register(firstname: string,lastname: string, email: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'signup',
      {
        firstname,
        lastname,
        email,
        password,
      },
      httpOptions
    );
  }

  logout(): void {
    localStorage.removeItem(environment.tokenKey);
  }

  getToken(): string | null {
    return localStorage.getItem(environment.tokenKey);
  }

  setToken(token: string): void {
    localStorage.setItem(environment.tokenKey, token);
  }

}

