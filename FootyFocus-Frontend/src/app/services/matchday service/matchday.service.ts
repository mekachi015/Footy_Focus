import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Match } from 'src/app/models/matches';

@Injectable({
  providedIn: 'root'
})
export class MatchdayService {

  constructor(private http: HttpClient) { }

  private apiUrl = 'http://localhost:8080/v4/competitions';

  // getMatches(competitionCode: string, matchday: number, season: number): Observable<any[]>{
  //   const url = `${this.apiUrl}/${competitionCode}/matches?matchday=${matchday}&season=${season}`;
  //   const headers = new HttpHeaders({
  //     'x-auth-token': '5576d3daf0c143b79ccbb7c9a1b22607',
  //     'Content-Type': 'application/json' 
  //   });
    
  //   return this.http.get<{ matches: Matches[] }>(url, { headers })
  //     .pipe(
  //       map(response => response.matches) // Adjust this mapping based on the actual API response structure
  //     );
  
  // }

  getMatches(leagueCode: string, season: string, matchday: number): Observable<Match[]> {
    const url = `${this.apiUrl}/${leagueCode}/matches?matchday=${matchday}&season=${season}`;
    return this.http.get<Match[]>(url);
  }
}
