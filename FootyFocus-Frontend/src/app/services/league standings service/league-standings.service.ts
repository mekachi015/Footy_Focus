import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LeagueStandings } from 'src/app/models/leagueStandings';

@Injectable({
  providedIn: 'root'
})
export class LeagueStandingsService {
  
  private apiUrl = "http://localhost:8080/v4/competitions";

  constructor(private http: HttpClient) { }

  getLeagueStanding(leagueCode: string, seasonYear: number) : Observable<any>{

    const url = `${this.apiUrl}/${leagueCode}/standings?season=${seasonYear}`;
    const headers = new HttpHeaders({
      'x-auth-token': '5576d3daf0c143b79ccbb7c9a1b22607',
      'Content-Type': 'application/json' 
    });

      return this.http.get<LeagueStandings>(url, {headers});
  }


  }

