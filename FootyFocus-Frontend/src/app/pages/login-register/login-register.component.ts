import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-register',
  templateUrl: './login-register.component.html',
  styleUrls: ['./login-register.component.scss']
})
export class LoginRegisterComponent implements OnInit {

  login: FormGroup;
  register: FormGroup;
  successMessage: string | null = null;
  error: string | null = null;
  isLoading = false;
  isLoginForm = true;

  constructor(private fb: FormBuilder, private router: Router) {
    this.login = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
    this.register = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      confirmPassword: ['', [Validators.required]]
    });
  }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  signInForm() {
    if (this.login.invalid) {
      return;
    }

    this.isLoading = true;
    // Simulate a network request
    setTimeout(() => {
      // Perform your login logic here
      this.isLoading = false;
      // On successful login, redirect to home
      this.router.navigate(['/league-standings']);
    }, 2000);
  }

  registerForm() {
    if (this.register.invalid) {
      return;
    }

    this.isLoading = true;
    // Simulate a network request
    setTimeout(() => {
      // Perform your register logic here
      this.isLoading = false;
      // On successful registration, redirect to home or login
      this.router.navigate(['/league-standings']);
    }, 2000);
  }

  switchToRegister() {
    this.isLoginForm = false;
  }

  switchToLogin() {
    this.isLoginForm = true;
  }
}
