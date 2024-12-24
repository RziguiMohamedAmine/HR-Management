import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';
import { AuthService } from './auth.service';


const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  private apiUrl = 'http://localhost:8008/HrMangement/auth/session'; // Backend endpoint
  constructor(private http: HttpClient,private authService:AuthService) { }

  private tokenKey = 'auth-token';

  private isBrowser(): boolean {
    return typeof window !== 'undefined' && typeof window.localStorage !== 'undefined';
  }


  getToken(): string | null {
    if (this.isBrowser()) {
      return localStorage.getItem('auth-token');
    }
    return null;
  }

  setToken(token: string): void {
    if (this.isBrowser()) {
      localStorage.setItem(this.tokenKey, token);
    }
  }

  clearToken(): void {
    if (this.isBrowser()) {
      localStorage.removeItem(this.tokenKey);
    }
  }

  getUserDetails(): Observable<any> {
    const token = this.getToken();
    if (token) {
      return this.authService.getUserDetailsFromToken(token);
    }
    throw new Error('Token not found!');
  }

  isTokenExpired(token: string): boolean {
    const expiry = (JSON.parse(atob(token.split('.')[1]))).exp;
    return (Math.floor(new Date().getTime() / 1000)) >= expiry;
  }
  
  isLoggedIn(): boolean {
    const token = this.getToken();
    if (token && !this.isTokenExpired(token)) {
      return true;
    }
    this.clearToken(); // Clear expired token
    return false;
  }

  initializeAuthenticationStatus(): void {
    this.isLoggedIn(); // Pre-check authentication during app initialization
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
  }

}
