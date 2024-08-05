import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth service/auth.service';
import { Router } from '@angular/router'; 
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-login-register',
  templateUrl: './login-register.component.html',
  styleUrls: ['./login-register.component.scss']
})
export class LoginRegisterComponent implements OnInit {

  login: FormGroup = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required]
  }); // Initialize the form group
  error: string | null = null;
  successMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Initialization logic if needed
  }

  signInForm() {
    if (this.login.invalid) {
      return;
    }

    const { email, password } = this.login.value;
    this.authService.login({ userName: email, password }).subscribe(
      (response) => {
        localStorage.setItem('token', response.token);
        console.log('Login successful', response);
        this.router.navigate(['/home']);
      },
      (error) => {
        this.error = 'Login error: ' + (error?.message || 'Unknown error');
        console.error('Login error', error);
      }
    );
  }
}
