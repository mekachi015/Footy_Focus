import { Component, OnInit } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { Match } from 'src/app/models/matches';
import { MatchdayService } from 'src/app/services/matchday service/matchday.service';
import Swal from 'sweetalert2';

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
          },
          error: (err) => {
            // Show SweetAlert2 error message for API errors
            Swal.fire({
              title: 'Error Fetching Matches',
              text: 'An error occurred while fetching matches. Please try again later.',
              icon: 'error'
            });
            this.error = 'An error occurred while fetching matches.';
            this.matches = [];
          }
        });
    } else {
      // Show SweetAlert2 error message for invalid matchday
      Swal.fire({
        title: 'Invalid Matchday',
        text: 'Please enter a matchday between 1 and 38.',
        icon: 'warning'
      });
      this.error = 'Please enter a matchday between 1 and 38.';
    }
  }
  
  
}
