import { Component, OnInit } from '@angular/core';
import { Player } from 'src/app/models/Player';
import { PlayerServiceService } from 'src/app/services/player service/player-service.service';

@Component({
  selector: 'app-player-page',
  templateUrl: './player-page.component.html',
  styleUrls: ['./player-page.component.scss']
})
export class PlayerPageComponent implements OnInit{

  players: Player[] = [];

  constructor (private playerService: PlayerServiceService){}

  idPlayer : number = 44;
  leagueCodes: { code: string, name: string }[] = [
    { code: 'PL', name: 'Premier League' },
    { code: 'BL1', name: 'Bundesliga' },
    { code: 'SA', name: 'Serie A' },
    { code: 'FL1', name: 'Ligue 1' },
    { code: 'PD', name: 'La Liga' },
    { code: 'DED', name: 'Eredivisie' }
  ];
  
  seasonYear: number[] = [2020, 2021, 2022, 2023];

  // selectedSeasonYear: number = new Date().getFullYear(); //default to the current year
  selectedSeasonYear: number = 2023; //default to the current year
  selectedLeagueCode: string = 'PL';  // setting default to premier league

  ngOnInit(): void {
     this.fetchTopPlayers();
  }

  fetchTopPlayers(): void{
    this.playerService.getTopTenPlayers(this.selectedLeagueCode, this.selectedSeasonYear)
    .subscribe((data: Player[]) =>{
      this.players = data;
    },

    (error) => {
      console.error('Error fetching top ten players')
    })
  }

  // getPlayerById(): void{
  //   this.playerService.getPlayerById(this.idPlayer).subscribe(
  //     (data: Player[]) => {
  //       this.players = data;
  //     },

  //     (error) => {
  //       console.error('Error fetching players');
  //     }
  //   )
  // }
}
