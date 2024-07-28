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

  ngOnInit(): void {
      this.playerService.getPlayers().subscribe((player: Player[]) => {
        this.players = player;
      })
  }

}
