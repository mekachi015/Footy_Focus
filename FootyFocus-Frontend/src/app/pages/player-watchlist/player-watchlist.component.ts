import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Player } from 'src/app/models/Player';
import { PlayerWatchlist } from 'src/app/models/PlayerWatchlist';
import { PlayerWatchlistService } from 'src/app/services/player watchlist service/player-watchlist.service';

@Component({
  selector: 'app-player-watchlist',
  templateUrl: './player-watchlist.component.html',
  styleUrls: ['./player-watchlist.component.scss']
})
export class PlayerWatchlistComponent {

  watchlist: PlayerWatchlist[] = [];
  searchForm: FormGroup;
  searchResults: PlayerWatchlist[] = [];

  constructor(
    private playerWatchlistService: PlayerWatchlistService,
    private fb: FormBuilder
  ) {
    this.searchForm = this.fb.group({
      query: ['']
    });
  }

  ngOnInit(): void {
    this.playerWatchlistService.getWatchlist().subscribe(players => {
      this.watchlist = players;
    });
    this.playerWatchlistService.fetchWatchlist();
  }

  removePlayer(playerId: number): void {
    this.playerWatchlistService.removeFromWatchlist(playerId);
  }

  clearWatchlist(): void {
    this.playerWatchlistService.clearWatchlist();
  }

  searchPlayers(): void {
    const query = this.searchForm.get('query')?.value;
    this.playerWatchlistService.searchPlayers(query).subscribe(players => {
      this.searchResults = players;
    });
  }

}
