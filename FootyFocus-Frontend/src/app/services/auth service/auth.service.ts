import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject, tap } from 'rxjs';
import { environment } from 'src/app/components/enviroment files/enviroment';
import { AuthenticationRequest } from 'src/app/models/AuthenticateRequest';
import { AuthenticationResponse } from 'src/app/models/AuthenticationResponse';
import { RegisterRequest } from 'src/app/models/RegisterRequest';

@Injectable({
  providedIn: 'root'
})

  export class AuthService {
  
    private UserLoggedIn: boolean = false;
  private loggedInEmail: string = '';
  private userId: number | null = null;
  private token: string | null = null;
  authChanged = new Subject<boolean>();

  private authStatusListener = new BehaviorSubject<boolean>(false);

  private apiUrl = `${environment.apiBaseUrl}/api/v1/auth`;

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.apiUrl}/authenticate`, { email, password }).pipe(
      tap(response => {
        this.token = response.token;
        this.UserLoggedIn = true;
        this.loggedInEmail = email;
        this.userId = this.extractUserIdFromToken(response.token);
        localStorage.setItem('loggedInUserEmail', email);
        localStorage.setItem('token', response.token);
        this.authStatusListener.next(true);
      })
    );
  }

  logout(): void {
    this.UserLoggedIn = false;
    this.loggedInEmail = '';
    this.token = null;
    this.userId = null;
    localStorage.removeItem('loggedInUserEmail');
    localStorage.removeItem('token');
    this.authStatusListener.next(false);
  }

  getToken(): string | null {
    return this.token;
  }

  isAuthenticated(): boolean {
    return !!this.token;
  }

  getAuthStatusListener(): Observable<boolean> {
    return this.authStatusListener.asObservable();
  }

  getIsLoggedIn(): boolean {
    return this.UserLoggedIn;
  }

  getLoggedInUserEmail(): string {
    return this.loggedInEmail;
  }

  initAuth(): void {
    const userEmail = localStorage.getItem('loggedInUserEmail');
    const token = localStorage.getItem('token');
    if (userEmail && token) {
      this.UserLoggedIn = true;
      this.loggedInEmail = userEmail;
      this.token = token;
      this.userId = this.extractUserIdFromToken(token);
      this.authStatusListener.next(true);
    }
  }

  getUserId(): number | null {
    return this.userId;
  }

  private extractUserIdFromToken(token: string): number | null {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.id || null;
    } catch (error) {
      return null;
    }
  }

  register(registerRequest: RegisterRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.apiUrl}/register`, registerRequest);
  }

  loginUser(authenticationRequest: AuthenticationRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.apiUrl}/authenticate`, authenticationRequest);
  }
  
  }