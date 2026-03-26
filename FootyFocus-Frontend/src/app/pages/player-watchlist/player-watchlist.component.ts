import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { PlayerInfo } from 'src/app/models/PlayerInfo';
import { PlayerSearch } from 'src/app/models/playerSearch';

import { PlayerWatchlist } from 'src/app/models/PlayerWatchlist';
import { PlayerInfoService } from 'src/app/services/player info service/player-info.service';
import { PlayerSearchService } from 'src/app/services/player search service/player-search.service';
import { PlayerWatchlistService } from 'src/app/services/player watchlist service/player-watchlist.service';

@Component({
  selector: 'app-player-watchlist',
  templateUrl: './player-watchlist.component.html',
  styleUrls: ['./player-watchlist.component.scss']
})
export class PlayerWatchlistComponent implements OnInit {
  leagues = [
    { code: 39, name: 'Premier League' },
    { code: 136, name: 'Serie A' },
    { code: 88, name: 'Eredivisie' },
    { code: 78, name: 'Bundesliga' },
    { code: 61, name: 'Ligue 1' },
    { code: 140, name: 'La Liga' }
  ];

  seasons = [2020, 2021, 2022, 2023, 2024];
  
  selectedLeague = 39; // Default value
  selectedSeason = 2023; // Default value
  searchQuery = '';

  players: PlayerInfo[] = [];

  constructor(
    private playerService: PlayerInfoService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    // Initialize form if needed
  }

  fetchPlayers(): void {
    this.playerService.searchPlayers(this.searchQuery, this.selectedLeague, this.selectedSeason)
      .subscribe(
        (data: PlayerInfo[]) => {
          this.players = data.map(player => ({
            ...player,
            showDetails: false
          }));
          console.log(this.players);
        },
        error => {
          console.error('Error fetching player data:', error);
        }
      );
  }

  
  
}
