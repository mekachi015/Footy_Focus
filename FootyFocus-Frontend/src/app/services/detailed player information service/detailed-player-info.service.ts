import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PlayerInformation } from 'src/app/models/detailedPlayerInfo';

@Injectable({
  providedIn: 'root'
})
export class DetailedPlayerInfoService {

  private apiUrl = 'http://localhost:8080/v4/persons';

  constructor(private http: HttpClient) { }

  getDetailerPlayerInfo(id: number): Observable<PlayerInformation>{
    return this.http.get<PlayerInformation>(`${this.apiUrl}/${id}`);
  }


}
