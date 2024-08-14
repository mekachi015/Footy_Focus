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

  getLimitedStandings(): LeagueStandings[] {
    const teamLimit = this.selectedLeagueCode === 'BL1' || this.selectedLeagueCode === 'FL1' || this.selectedLeagueCode === 'DED' ? 18 : 20;
    const uniqueStandings = this.filterDuplicates(this.leagueStandings);
    return uniqueStandings.slice(0, teamLimit);
  }

  filterDuplicates(standings: LeagueStandings[]): LeagueStandings[] {
    const uniqueTeams = new Map<string, LeagueStandings>();
    standings.forEach(item => {
      if (!uniqueTeams.has(item.teamName)) {
        uniqueTeams.set(item.teamName, item);
      }
    });
    return Array.from(uniqueTeams.values());
  }

  //Ligue1 - 18
  //bundesliga - 18
  // eredivisie - 
}
