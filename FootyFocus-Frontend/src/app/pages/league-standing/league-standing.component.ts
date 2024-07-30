import { Component, OnInit } from '@angular/core';
import { LeagueStandings } from 'src/app/models/leagueStandings';
import { LeagueStandingsService } from 'src/app/services/league standings service/league-standings.service';

@Component({
  selector: 'app-league-standing',
  templateUrl: './league-standing.component.html',
  styleUrls: ['./league-standing.component.scss']
})
export class LeagueStandingComponent implements OnInit {


  leagueStandings: LeagueStandings[] = [];


  constructor(private leagueService: LeagueStandingsService) {}

  leagueCodes: string[] = ['PL', 'SA', 'BL1', 'EC', 'CL', 'FL1', 'PD', 'DED'];
  seasonYear: number[] = [2020, 2021, 2022, 2023];

  // selectedSeasonYear: number = new Date().getFullYear(); //default to the current year
  selectedSeasonYear: number = 2023; //default to the current year
  selectedLeagueCode: string = 'DED';  // setting default to premier league


  ngOnInit(): void {
    
    this.fetchStandings();
  }

  fetchStandings(): void{
    this.leagueService.getLeagueStanding(this.selectedLeagueCode, this.selectedSeasonYear).subscribe(
      (data: LeagueStandings[]) => {
        this.leagueStandings = data;
      },

      (error) =>{
        console.error('Error fetching league standings')
      }
    )
  }
}
