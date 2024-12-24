import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { AuthService } from '../../auth/auth.service';
import { StorageService } from '../../auth/storage.service';
import { AuthStateService } from '../../auth/auth-state.service';



@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule,CommonModule, RouterModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  loginForm!: FormGroup;
  errorMessage = '';
  isLoading = false;
  submitted = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private authStateService: AuthStateService,
    private storageService: StorageService
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [
        Validators.required,
        Validators.email,
        Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')
      ]],
      password: ['', [
        Validators.required,
        Validators.minLength(6)
      ]]
    });
  }

  // Getter for easy access to form fields
  get f() { 
    return this.loginForm.controls; 
  }

  // Error message getters
  getErrorMessage(field: string): string {
    if (!this.submitted) return '';
    
    const control = this.f[field];
    if (!control || !control.errors) return '';

    if (field === 'email') {
      if (control.errors['required']) return 'Email is required';
      if (control.errors['email'] || control.errors['pattern']) return 'Please enter a valid email';
    }

    if (field === 'password') {
      if (control.errors['required']) return 'Password is required';
      if (control.errors['minlength']) return 'Password must be at least 6 characters';
    }

    return '';
  }

  onSubmit(): void {
    this.submitted = true;
    this.errorMessage = '';

    if (this.loginForm.invalid) {
      return;
    }

    this.isLoading = true;

    this.authService.login(this.loginForm.value).subscribe({
      next: (data) => {
        // Show loading state for 2 seconds
        setTimeout(() => {
          this.storageService.setToken(data.token);
          this.authStateService.setLoggedInState(true);
          this.isLoading = false;
          this.router.navigate(['/employees']);
        }, 2000);
      },
      error: (err) => {
        this.isLoading = false;
        this.errorMessage = 'Invalid credentials';
      }
    });
  }
}