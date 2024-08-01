import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PlayerInformation } from 'src/app/models/detailedPlayerInfo';
import { DetailedPlayerInfoService } from 'src/app/services/detailed player information service/detailed-player-info.service';

@Component({
  selector: 'app-detailed-player',
  templateUrl: './detailed-player.component.html',
  styleUrls: ['./detailed-player.component.scss']
})
export class DetailedPlayerComponent implements OnInit{

  
  player: PlayerInformation | undefined;

  constructor(
    private route: ActivatedRoute,
    private playerService: DetailedPlayerInfoService
  ) { }

  ngOnInit(): void {
      this.route.params.subscribe(params => {
        const id = +params['id']

        this.playerService.getDetailerPlayerInfo(id).subscribe(
          data=> {this.player = data
            console.log(this.player);
          }
        )
      })
  }
  }


