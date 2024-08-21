import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/app/components/enviroment files/enviroment';
import { Profile } from 'src/app/models/Profile';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private baseUrl = "http://localhost:8080/api/v1/auth/authenticate"; 

  constructor(private http: HttpClient) { }

  getUserProfile(userId: number): Observable<Profile> {
    const url = `${this.baseUrl}/user/${userId}`;
    return this.http.get<Profile>(url);
  }

}
