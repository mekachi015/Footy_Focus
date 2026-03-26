import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PlayerInfo } from 'src/app/models/PlayerInfo';

@Injectable({
  providedIn: 'root'
})
export class PlayerInfoService {

  private apiUrl = 'http://localhost:8080/players'; // Backend URL

  constructor(private http: HttpClient) { }

  searchPlayers(search: string, league: number, season: number): Observable<PlayerInfo[]> {
    const params = new HttpParams()
      .set('search', search)
      .set('league', league.toString())
      .set('season', season.toString());

    return this.http.get<PlayerInfo[]>(this.apiUrl, { params });
  }
}
