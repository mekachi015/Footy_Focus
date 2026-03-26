import { Component, OnInit } from '@angular/core';
<<<<<<< HEAD
import { Player } from 'src/app/models/Player';
import { Router } from '@angular/router';
import { PlayerServiceService } from 'src/app/services/player service/player-service.service';
=======
import { TeamStatistics } from 'src/app/models/TeamStats';
import { TeamStatisticsService } from 'src/app/services/team statistic service/team-stats.service';
>>>>>>> 3830bf3f8c969c36802466de2a2fe0e3c9a89db1

@Component({
  selector: 'app-player-page',
  templateUrl: './player-page.component.html',
  styleUrls: ['./player-page.component.scss']
})
export class PlayerPageComponent implements OnInit {

  public leagueCodes: [number, string][] = [];
  public teamCodes: [number, string][] = [];
  public selectedLeagueCode: number | null = null;
  public selectedTeamCode: number | null = null;
  public season: number | null = null;
  public selectedSeason: number | null= null;
  public teamStatistics: TeamStatistics | null = null;
  public error: string | null = null;
  public showFullInfo: boolean = false; 

<<<<<<< HEAD
  constructor (private playerService: PlayerServiceService, private router: Router){}

  //idPlayer: number;
  
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

      console.log(this.players);
    },
=======
  constructor(private teamStatsService: TeamStatisticsService) { }

  ngOnInit(): void {
    this.leagueCodes = this.teamStatsService.leagueCodes;
    this.teamCodes = this.teamStatsService.plTeams; // Default to Premier League teams; adjust as needed

    this.selectedLeagueCode = 39;
    this.selectedTeamCode = 33;
    this.selectedSeason = 2024
  }

  onSearch(): void {
    if (this.selectedLeagueCode && this.selectedTeamCode && this.season) {
      this.teamStatsService.fetchTeamStatistics(this.selectedLeagueCode, this.season, this.selectedTeamCode)
        .subscribe({
          next: (data: TeamStatistics) => {
            this.teamStatistics = data;
            this.error = null; // Clear any previous error
>>>>>>> 3830bf3f8c969c36802466de2a2fe0e3c9a89db1

            console.log(this.teamStatistics);
          },
          error: (err) => {
            console.error('Error:', err);
            this.teamStatistics = null; // Clear any previous data
            this.error = 'Failed to fetch team statistics';
          }
        });
    } else {
      this.error = 'Please fill in all required fields';
    }
  }

<<<<<<< HEAD
  goToPlayerDetail(id: number): void {
    this.router.navigate(['/players', id]);
  }
  // getPlayerById(): void{
  //   this.playerService.getPlayerById(this.idPlayer).subscribe(
  //     (data: Player[]) => {
  //       this.players = data;
  //     },
=======
  onLeagueChange(event: Event): void {
    const target = event.target as HTMLSelectElement;
    this.selectedLeagueCode = Number(target.value);
>>>>>>> 3830bf3f8c969c36802466de2a2fe0e3c9a89db1

    // Update teamCodes based on selected league
    switch (this.selectedLeagueCode) {
      case 39:
        this.teamCodes = this.teamStatsService.plTeams;
        break;
      case 140:
        this.teamCodes = this.teamStatsService.laLigaTeams;
        break;
      case 135:
        this.teamCodes = this.teamStatsService.serieATeams;
        break;
      case 78:
        this.teamCodes = this.teamStatsService.bundesligaCodes;
        break;
      case 61:
        this.teamCodes = this.teamStatsService.ligueOneTeams;
        break;
      case 88:
        this.teamCodes = this.teamStatsService.ducthTeams;
        break;
      default:
        this.teamCodes = [];
        break;
    }
  }

  toggleInfo(): void {
    this.showFullInfo = !this.showFullInfo;
  }
}
