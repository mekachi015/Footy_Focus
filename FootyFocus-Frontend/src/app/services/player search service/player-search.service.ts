import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Player } from 'src/app/models/Player';
import { PlayerSearch } from 'src/app/models/playerSearch';

@Injectable({
  providedIn: 'root'
})
export class PlayerSearchService {

  private apiUrl = 'http://localhost:8080/players'; // Adjust URL as needed

  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'X-RapidAPI-Key': 'your_api_token_here' // You may need to pass the API token if required
  });

  constructor(private http: HttpClient) { }

  // Method to fetch players from the API
  getPlayers(search: string, league: number, season: number): Observable<PlayerSearch[]> {
    let params = new HttpParams()
      .set('search', search)
      .set('league', league.toString())
      .set('season', season.toString());

    return this.http.get<PlayerSearch[]>(this.apiUrl, { params, headers: this.headers });
  }
}
