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

  login!: FormGroup;
  register!: FormGroup;
  error: string | null = null;
  isLoading = false;
  isLoginForm = true;
  successMessage: string | null = null;
  
  email: string = '';
  password: string = '';
  
  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.login = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });

    this.register = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    });
  }

  // signInForm(): void {
  //   if (this.login.invalid) {
  //     return;
  //   }

  //   this.isLoading = true;
  //   const { email, password } = this.login.value;
  //   this.authService.login(email, password).subscribe(
  //     response => {
  //       this.isLoading = false;
  //       this.router.navigate(['/dashboard']);
  //     },
  //     error => {
  //       this.isLoading = false;
  //       this.error = 'Login failed. Please check your credentials.';
  //     }
  //   );
  // }

  onLogin() {
    const authRequest: AuthenticationRequest = { email: this.email, password: this.password };
    this.authService.loginUser(authRequest).subscribe({
      next: response => {
        console.log('Login successful');
        this.router.navigate(['/dashboard']);
      },
      error: err => {
        console.error('Login failed', err);
      }
    });
  }

  registerForm(): void {
    if (this.register.invalid) {
      return;
    }

    this.isLoading = true;
    const request: RegisterRequest = this.register.value;
    this.authService.register(request).subscribe(
      response => {
        this.isLoading = false;
        this.successMessage = 'Registration successful. Please login.';
        this.switchToLogin();
      },
      error => {
        this.isLoading = false;
        this.error = 'Registration failed. Please try again.';
      }
    );
  }
  
  switchToLogin(): void {
    this.isLoginForm = true;
    this.error = null;
  }

  switchToRegister(): void {
    this.isLoginForm = false;
    this.error = null;
  }
  
}
