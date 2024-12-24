import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthStateService {
  private loggedIn = new BehaviorSubject<boolean>(false);
  private loading = new BehaviorSubject<boolean>(true); // Add loading state
  
  isLoggedIn$ = this.loggedIn.asObservable();
  isLoading$ = this.loading.asObservable()



  private isBrowser(): boolean {
    return typeof window !== 'undefined' && typeof window.localStorage !== 'undefined';
  }

  constructor() {
    // Initialize from localStorage on service creation
    if (this.isBrowser()) {
    const token = localStorage.getItem('auth-token');
    if (token) {
      this.loggedIn.next(true);
    }
    this.loading.next(false);
    }
  }

  setLoggedInState(state: boolean): void {
    this.loggedIn.next(state);
  }

  setLoadingState(state: boolean): void {
    this.loading.next(state);
  }

  getLoggedInState(): boolean {
    return this.loggedIn.getValue();
  }
}
