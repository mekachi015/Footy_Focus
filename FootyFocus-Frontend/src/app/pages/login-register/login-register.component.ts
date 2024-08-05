import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-register',
  templateUrl: './login-register.component.html',
  styleUrls: ['./login-register.component.scss']
})
export class LoginRegisterComponent implements OnInit {

  login: FormGroup;
  register: FormGroup;
  isLoginForm: boolean = true;
  successMessage: string | null = null;
  error: string | null = null;

  constructor(private fb: FormBuilder) {
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

  ngOnInit(): void {}

  signInForm(): void {
    if (this.login.valid) {
      // Perform login logic
      this.successMessage = 'Login successful!';
    } else {
      this.error = 'Please fill in all fields correctly.';
    }
  }

  registerForm(): void {
    if (this.register.valid) {
      // Perform register logic
      this.successMessage = 'Registration successful!';
    } else {
      this.error = 'Please fill in all fields correctly.';
    }
  }

  switchToLogin(): void {
    this.isLoginForm = true;
  }

  switchToRegister(): void {
    this.isLoginForm = false;
  }
}
