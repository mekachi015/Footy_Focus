import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationRequest } from 'src/app/models/AuthenticateRequest';
import { RegisterRequest } from 'src/app/models/RegisterRequest';
import { AuthService } from 'src/app/services/auth service/auth.service';


@Component({
  selector: 'app-login-register',
  templateUrl: './login-register.component.html',
  styleUrls: ['./login-register.component.scss']
})
export class LoginRegisterComponent implements OnInit {
  
  
  loginForm!: FormGroup;
  registerForm!: FormGroup;
  successMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });

    this.registerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  onLogin(): void {
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value).subscribe({
        next: (response) => {
          this.successMessage = 'Login successful!';
          // Handle successful login, e.g., navigate to a different page
          this.router.navigate(['/matchday']);
        },
        error: (error) => {
          // Handle login error
          console.error('Login failed', error);
        }
      });
    }
  }

  onRegister(): void {
    if (this.registerForm.valid) {
      this.authService.register(this.registerForm.value).subscribe({
        next: (response) => {
          this.successMessage = 'Registration successful!';
          // Handle successful registration, e.g., navigate to a login page
          this.router.navigate(['/matchday']);
        },
        error: (error) => {
          // Handle registration error
          console.error('Registration failed', error);
        }
      });
    }
  }


}
