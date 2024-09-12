import { Component, OnInit } from '@angular/core';
import { LeagueStandings } from 'src/app/models/leagueStandings';
import { LeagueStandingsService } from 'src/app/services/league standings service/league-standings.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-league-standing',
  templateUrl: './league-standing.component.html',
  styleUrls: ['./league-standing.component.scss']
})
export class LeagueStandingComponent implements OnInit {


  leagueStandings: LeagueStandings[] = [];
  clickedTeam: string | null = null;  // Track the clicked team

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
  selectedSeasonYear: number = 2023; // Default to the current year
  selectedLeagueCode: string = 'PL';  // Default to Premier League

  ngOnInit(): void {
    this.fetchStandings();
  }

  fetchStandings(): void {
    // Check if the selected season year is within the allowed range
    if (this.selectedSeasonYear < 2020 || this.selectedSeasonYear > 2024) {
      Swal.fire({
        title: 'Invalid Year',
        text: 'Please enter a year between 2020 and 2024.',
        icon: 'error'
      });
      return; // Exit the method to prevent further execution
    }
  
    // Proceed with the API call if the year is valid
    this.leagueService.getLeagueStanding(this.selectedLeagueCode, this.selectedSeasonYear).subscribe(
      (data: LeagueStandings[]) => {
        this.leagueStandings = data;
      },
      (error) => {
        Swal.fire({
          title: 'Error Fetching Standings',
          text: 'An error occurred while fetching the league standings. Please try again later.',
          icon: 'error'
        });
        console.error('Error fetching league standings', error); // Keep the console log for debugging
      }
    );
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

  onTeamClick(teamName: string): void {
    this.clickedTeam = this.clickedTeam === teamName ? null : teamName; // Toggle visibility
  }

  
  

}
