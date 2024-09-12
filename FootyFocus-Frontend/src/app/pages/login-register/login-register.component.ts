import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth service/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login-register',
  templateUrl: './login-register.component.html',
  styleUrls: ['./login-register.component.scss']
})
export class LoginRegisterComponent implements OnInit {

  public login!: FormGroup;
  public register!: FormGroup;
  public isRegisterMode: boolean = false; // Track the current form mode
  public userLoggedIn: boolean = false;
  public userEmail: string = '';
  public error: string | null = null;
  public successMessage: string | null = null; // Added success message variable
  private token: string | undefined;

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    // Initialize the signIn form group with email and password form controls
    this.login = this.formBuilder.group({
      email: ["", [Validators.required, Validators.email]],
      password: ["", Validators.required]
    });

    // Initialize the registration form group with required controls
    this.register = this.formBuilder.group({
      firstName: ["", Validators.required],
      lastName: ["", Validators.required],
      email: ["", [Validators.required, Validators.email]],
      password: ["", Validators.required]
    });

    // Check if token exists in localStorage on component initialization
    const token = localStorage.getItem('token');
    if (token) {
      // If token exists, attempt automatic login
      this.authService.login(this.userEmail, token);
    } else {
      // If no token, navigate user to login page
      this.router.navigate(['/login']);
    }
  }

  // Function to convert email to lowercase on input
  onEmailInput(): void {
    const emailControl = this.login.get('email');
    const lowercaseEmail = emailControl?.value.toLowerCase();
    emailControl?.setValue(lowercaseEmail, { emitEvent: false });
  }

  // Function called when user submits the login form
  signInForm(): void {
    this.http.post<{ token: string }>("http://localhost:8080/api/v1/auth/authenticate", this.login.value)
      .subscribe(
        resp => {
          const token = resp.token;
          if (token) {
            this.token = token;
            this.userEmail = this.login.value.email;
            localStorage.setItem("token", token);
            this.userLoggedIn = true;
            this.error = null; // Clear any previous error message
            
            // Show success alert
            Swal.fire({
              title: 'Login Successful',
              text: 'You will be redirected to the Matchday.',
              icon: 'success',
              timer: 1500, // Optional: Auto-close after 1.5 seconds
              willClose: () => {
                // Navigate to home upon successful login
                this.router.navigate(["/matchday"]);
                this.authService.login(this.userEmail, token);
              }
            });
          } else {
            this.error = "User not found";
          }
        },
        (error: HttpErrorResponse) => {
          // Show error alert based on HTTP error status
          if (error.status === 401) {
            Swal.fire({
              title: 'Login Failed',
              text: 'Incorrect password or User does not exist. Please try again.',
              icon: 'error'
            });
          } else if (error.status === 404) {
            Swal.fire({
              title: 'User Not Found',
              text: 'Please check your email.',
              icon: 'error'
            });
          } else {
            Swal.fire({
              title: 'Error',
              text: 'Something went wrong. Please try again later.',
              icon: 'error'
            });
          }
        }
      );
  }
  
 
  onRegister(): void {
    this.http.post<{ token: string }>("http://localhost:8080/api/v1/auth/register", this.register.value)
      .subscribe(
        () => {
          // On successful registration, log the user in automatically
          const loginData = {
            email: this.register.value.email,
            password: this.register.value.password
          };
  
          this.http.post<{ token: string }>("http://localhost:8080/api/v1/auth/authenticate", loginData)
            .subscribe(
              resp => {
                const token = resp.token;
                if (token) {
                  this.token = token;
                  this.userEmail = this.register.value.email;
                  localStorage.setItem("token", token);
                  this.userLoggedIn = true;
                  
                  // Show success alert for registration and auto-login
                  Swal.fire({
                    title: 'Registration Successful',
                    text: 'You are now logged in and will be redirected to Matchday.',
                    icon: 'success',
                    timer: 1500, // Optional: Auto-close after 1.5 seconds
                    willClose: () => {
                      // Navigate to home upon successful login
                      this.router.navigate(["/matchday"]);
                      this.authService.login(this.userEmail, token);
                    }
                  });
                }
              },
              (error: HttpErrorResponse) => {
                // Show error alert if login fails after registration
                Swal.fire({
                  title: 'Login Failed',
                  text: 'Login failed after registration. Please try logging in manually.',
                  icon: 'error'
                });
              }
            );
        },
        (error: HttpErrorResponse) => {
          // Show error alert based on registration error
          if (error.status === 400) {
            Swal.fire({
              title: 'Registration Error',
              text: 'Invalid registration details. Please check your input.',
              icon: 'error'
            });
          } else {
            Swal.fire({
              title: 'Error',
              text: 'Something went wrong. Please try again later.',
              icon: 'error'
            });
          }
        }
      );
  }
  
  

  // Function to switch between login and registration forms
  toggleForm(): void {
    this.isRegisterMode = !this.isRegisterMode;
    this.error = null; // Clear any error messages when toggling forms
    this.successMessage = null; // Clear success messages as well
  }
}
