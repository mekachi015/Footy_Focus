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
  
    private authStatusListener = new BehaviorSubject<boolean>(false);
  
    private baseUrl = 'http://localhost:8080/api/v1/auth';
  
    constructor(private http: HttpClient) {}
  
    login(email: string, password: string): Observable<{ token: string }> {
      return this.http.post<{ token: string }>(`${this.baseUrl}/authenticate`, { email, password });
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
    }}
