import { HttpClient, HttpHeaders } from '@angular/common/http';
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
    private userId: number | null = null; // Add a userId field
    private token: string | null = null;
    authChanged = new Subject<boolean>();
  
    private authStatusListener = new BehaviorSubject<boolean>(false);
  
    private baseUrl = 'http://localhost:8080/api/v1/auth';
  
    constructor(private http: HttpClient) { }
  
    login(email: string, token: string): void {
      this.UserLoggedIn = true;
      this.token = token;
      this.loggedInEmail = email;
      localStorage.setItem('loggedInUserEmail', email);
      localStorage.setItem('token', token);
      this.userId = this.extractUserIdFromToken(token);
      this.authStatusListener.next(true);
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
      return !!this.token; // Returns true if token is not null or undefined
    }
  
    getAuthStatusListener(): Observable<boolean> {
      return this.authStatusListener.asObservable();
    }
  
    getIsLoggedIn(): boolean {
      return this.UserLoggedIn;
    }
  
    getLoggedInUserEmail(): string {
      return localStorage.getItem('loggedInUserEmail') || this.loggedInEmail;
    }
  
    initAuth(): void {
      const userEmail = localStorage.getItem('loggedInUserEmail');
      const token = localStorage.getItem('token');
      if (userEmail && token) {
        this.UserLoggedIn = true;
        this.loggedInEmail = userEmail;
        this.token = token;
        this.userId = this.extractUserIdFromToken(token); // Extract and store the user ID
        this.authStatusListener.next(true);
      } else {
        // console.log('No token found in local storage.');
      }
    }
  
    getUserId(): number | null {
      return this.userId;
    }

    // getUserProfile(): Observable<any> {
    //   const token = this.getToken();
    //   const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    //   return this.http.get(`${this.baseUrl}/user`, { headers });
    // }

    getUserProfileByEmail(): Observable<any> {
      const email = this.getLoggedInUserEmail();
      const token = this.getToken();
      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
      return this.http.get(`${this.baseUrl}/user/email/${email}`, { headers });
    }
    

    updateUserProfile(userData: any): Observable<any> {
      const token = this.getToken();
      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
      return this.http.put(`${this.baseUrl}/user`, userData, { headers });
    }
  
    // private extractUserIdFromToken(token: string): number | null {
    //   try {
    //     const payload = JSON.parse(atob(token.split('.')[1]));
    //     return payload.id || null; // Assuming the user ID is stored in the "id" field
    //     console.log("The user id is:", this.userId);
    //   } catch (error) {
    //     console.error('Error decoding token:', error);
    //     return null;
    //   }
    // } 

    private extractUserIdFromToken(token: string): number | null {
      try {
        const payload = JSON.parse(atob(token.split('.')[1])); // Decode the token's payload
        console.log("Decoded payload:", payload); // To see the full payload
        return payload.id || null; // Assuming the user ID is stored in the "id" field
      } catch (error) {
        console.error('Error decoding token:', error);
        return null;
      }
    }
    

    
  
}