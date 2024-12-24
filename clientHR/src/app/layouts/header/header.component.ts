import { Component } from '@angular/core';
import { StorageService } from '../../auth/storage.service';
import { Router, RouterModule } from '@angular/router';
import { AuthStateService } from '../../auth/auth-state.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  userDetails: any | null = null;
  isLoading = false;
  error: string | null = null;
  constructor(private storageService: StorageService, private router: Router, private authStateService: AuthStateService) {}
 
  ngOnInit(): void {
    this.loadUserDetails();
  }

  loadUserDetails(): void {
    this.isLoading = true;
    this.error = null;

    this.storageService.getUserDetails().subscribe({
      next: (details) => {
        this.userDetails = details;
        console.log(this.userDetails);
        this.isLoading = false;
      },
      error: (err) => {
        this.error = 'Failed to load user details';
        this.isLoading = false;
        console.error('Error loading user details:', err);
      }
    });
  }

  logout(): void {
   // window.location.reload();
    this.storageService.clearToken();
    this.authStateService.setLoggedInState(false); // Notify state change
    this.router.navigate(['/login']); // Redirect to the login page
  }
}
