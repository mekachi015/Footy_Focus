import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/app/components/enviroment files/enviroment';
import { Profile } from 'src/app/models/Profile';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private baseUrl = "http://localhost:8080/api/v1/auth/authenticate"; 

  private apiUrl = "http://localhost:8080/api/v1/auth"

  constructor(private http: HttpClient) { }

  getUserProfile(userId: number): Observable<Profile> {
    const url = `${this.baseUrl}/user/${userId}`;
    return this.http.get<Profile>(url);
  }

  getUserProfileByEmail(email: string): Observable<Profile> {
    const url = `${this.baseUrl}/user/email/${email}`; // Corrected URL
    return this.http.get<Profile>(url); 
  }


  // Add the update profile method
  updateUserProfile(email: string, profile: Profile): Observable<any> {
    const url = `${this.apiUrl}/user/update/${email}`;

    // Add authorization headers if necessary
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}` // Assuming you're using JWT for authentication
    });

    return this.http.put(url, profile, { headers });  // PUT request to update the profile
  }
  
}
