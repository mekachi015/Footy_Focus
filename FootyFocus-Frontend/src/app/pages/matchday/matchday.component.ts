import { Component, OnInit } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { Match } from 'src/app/models/matches';
import { MatchdayService } from 'src/app/services/matchday service/matchday.service';

@Component({
  selector: 'app-matchday',
  templateUrl: './matchday.component.html',
  styleUrls: ['./matchday.component.scss']
})
export class MatchdayComponent implements OnInit {

  leagueCodes: { code: string, name: string }[] = [
    { code: 'PL', name: 'Premier League' },
    { code: 'BL1', name: 'Bundesliga' },
    { code: 'SA', name: 'Serie A' },
    { code: 'FL1', name: 'Ligue 1' },
    { code: 'PD', name: 'La Liga' },
    { code: 'DED', name: 'Eredivisie' }
  ];
  
  seasonYear: number[] = [2020, 2021, 2023, 2024];


  constructor(private matchdayService: MatchdayService) { }

  selectedLeague = 'SA';
  selectedSeason = '2023';
  selectedMatchDay  = '1';
  matchday: number | undefined;
  matches: any[] = [];
  error: string | null = null;

  ngOnInit(): void {
    // this.fetchMatches();
  }

  public today: Date = new Date();

  fetchMatches() {
    if (this.matchday && this.matchday >= 1 && this.matchday <= 38) {
      this.matchdayService.getMatches(this.selectedLeague, this.selectedSeason, this.matchday)
        .subscribe({
          next: (data: Match[]) => {
            this.matches = data;
            this.error = null;
            
            // Log the refName of each match
            this.matches.forEach(match => {
              console.log('Referee Name:', match.refName);
            });
          },
          error: (err) => {
            this.error = 'An error occurred while fetching matches.';
            this.matches = [];
          }
        });
    } else {
      this.error = 'Please enter a matchday between 1 and 38.';
    }
  }
  
}
