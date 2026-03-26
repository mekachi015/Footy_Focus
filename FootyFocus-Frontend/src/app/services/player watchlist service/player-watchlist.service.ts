import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { PlayerWatchlist } from 'src/app/models/PlayerWatchlist';

@Injectable({
  providedIn: 'root'
})
export class PlayerWatchlistService {

  private baseUrl = 'http://localhost:8080/api/v1/players';
  private watchlist = new BehaviorSubject<PlayerWatchlist[]>([]);

  constructor(private http: HttpClient) { }

  getWatchlist(): Observable<PlayerWatchlist[]> {
    return this.watchlist.asObservable();
  }

  fetchWatchlist(): void {
    this.http.get<PlayerWatchlist[]>(`${this.baseUrl}/watchlist`).subscribe(players => {
      this.watchlist.next(players);
    });
  }

  removeFromWatchlist(playerId: number): void {
    this.http.delete(`${this.baseUrl}/watchlist/${playerId}`).subscribe(() => {
      const updatedWatchlist = this.watchlist.getValue().filter(player => player.id !== playerId);
      this.watchlist.next(updatedWatchlist);
    });
  }

  clearWatchlist(): void {
    this.http.delete(`${this.baseUrl}/watchlist`).subscribe(() => {
      this.watchlist.next([]);
    });
  }

  searchPlayers(query: string): Observable<PlayerWatchlist[]> {
    return this.http.get<PlayerWatchlist[]>(`${this.baseUrl}/search?query=${query}`);
  }
}
