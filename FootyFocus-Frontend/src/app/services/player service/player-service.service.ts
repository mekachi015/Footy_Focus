import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Player } from 'src/app/models/Player';

@Injectable({
  providedIn: 'root'
})
export class PlayerServiceService {

  private apiUrl = 'http://localhost:8080/v4/persons'; // a link to the persons endpoint

  constructor(private http: HttpClient) { }

  getPlayers(): Observable<Player[]>{
    return this.http.get<Player[]>(this.apiUrl);
  }

  getPlayerById(playerId: number): Observable<any>{
    const url = `${this.apiUrl}/${playerId}`
    const headers = new HttpHeaders({
      'x-auth-token' : '5576d3daf0c143b79ccbb7c9a1b22607',
      'Content-Type': 'application/json'
    });

    return this.http.get<Player>(url, {headers});
  }

  
}
