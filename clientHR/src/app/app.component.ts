import { Component, ViewEncapsulation } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterModule, RouterOutlet, Router, ActivatedRoute} from '@angular/router';
import { HeaderComponent } from './layouts/header/header.component';
import { SidebarComponent } from './layouts/sidebar/sidebar.component';
import { HomeComponent } from './layouts/home/home.component';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from './auth/auth.service';
import { StorageService } from './auth/storage.service';
import { AuthStateService } from './auth/auth-state.service';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,HeaderComponent,SidebarComponent, CommonModule,RouterModule],
  // encapsulation: ViewEncapsulation.None,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {  
  isLoggedIn = false;
  isLoading = true;
  constructor(
    private authStateService: AuthStateService,
    private storageService: StorageService
  ) {}

  ngOnInit(): void {

    this.authStateService.isLoading$.subscribe(loading => {
      this.isLoading = loading;
    });


    // Subscribe to auth state changes
    this.authStateService.isLoggedIn$.subscribe(state => {
      this.isLoggedIn = state;
      console.log('Auth state changed:', state); // For debugging
    });

    // Check initial login state
    const initialLoginState = this.storageService.isLoggedIn();
    this.authStateService.setLoggedInState(initialLoginState);
  }
}
