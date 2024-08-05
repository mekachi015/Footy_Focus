import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private UserLoggedIn: boolean = false;
  private loggedInEmail: string = '';
  private userId: number | null = null;
  private token: string | null = null;
  authChanged = new Subject<boolean>();

  private authStatusListner = new BehaviorSubject<boolean>(false);

  private baseUrl = 'http://localhost:8080/api/v1/auth';
  constructor(private http: HttpClient) { }

  login(user: { userName: string; password: string }): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/login`, user).pipe(
      tap(response => {
        this.UserLoggedIn = true;
        this.token = response.token;
        this.loggedInEmail = user.userName;
        localStorage.setItem('loggedInUserEmail', user.userName);
        localStorage.setItem('token', response.token); // Store the token in localStorage
        this.userId = this.extractUserIdFromToken(response.token); // Extract and store the user ID
        this.authStatusListner.next(true);
      })
    );
  }

  logout(): void {
    this.UserLoggedIn = false;
    this.loggedInEmail = '';
    this.token = null;
    this.userId = null; // Clear the user ID
    localStorage.removeItem('loggedInUserEmail');
    localStorage.removeItem('token'); // Remove the token from localStorage
    this.authStatusListner.next(false);
  }

  getToken(): string | null {
    return this.token;
  }

  isAuthenticated(): boolean {
    return !!this.token; // Returns true if token is not null or undefined
  }

  getAuthStatusListener(): Observable<boolean> {
    return this.authStatusListner.asObservable();
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
      this.userId = this.extractUserIdFromToken(token); // Extract and store the user ID
      this.authStatusListner.next(true);
    } else {
      // console.log('No token found in local storage.');
    }
  }

  private extractUserIdFromToken(token: string): number | null {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.id || null; // Assuming the user ID is stored in the "id" field
    } catch (error) {
      // console.error('Error decoding token:', error);
      return null;
    }
  }

  getUserId(): number | null {
    return this.userId;
  }
}
