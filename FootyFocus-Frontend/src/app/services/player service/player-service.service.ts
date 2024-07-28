import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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

  
}
