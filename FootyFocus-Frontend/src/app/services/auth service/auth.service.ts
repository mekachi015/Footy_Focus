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

  register(request: RegisterRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.apiUrl}/register`, request);
  }

  login(request: AuthenticationRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.apiUrl}/authenticate`, request);
  }

  getUserInfo(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/user`);
  }
  
  
}