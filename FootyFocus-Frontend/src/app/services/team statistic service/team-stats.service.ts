import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { TeamStatistics } from 'src/app/models/TeamStats';
import { environment } from 'src/app/components/enviroment files/enviroment';

@Injectable({
  providedIn: 'root'
})
export class TeamStatisticsService {

  private static readonly API_URL = `${environment.apiBaseUrl}/teams/statistics`;
  private static readonly API_TOKEN = environment.apiToken;

  public leagueCodes: [number, string][] = [
    [39, 'Premier League'],
    [140, 'La Liga'],
    [135, 'Serie A'],
    [78, 'Bundesliga'],
    [61, 'Ligue 1'],
    [88, 'Eresdivisie']
    // Add more league codes and names as needed
  ];


 
    public plTeams: [number, string][] = [
      [33, 'Manchester United'],
      [40, 'Liverpool'],
      [50, 'Machester City'],
      [49, 'Chelsea'],
      [42, 'Arsenal'],
      [47, 'Tottenham']
    ];

    public laLigaTeams: [number, string][] = [
      [541, 'Real Madrid'],
      [529, 'Barcelona'],
      [530, 'Atletico Madrid']
    ];

    public serieATeams: [number, string][] = [
      [496, 'Juventus'],
      [489, 'Ac Milan'],
      [505, 'Inter Milan']
    ];

    public bundesligaCodes: [number, string][] = [
      [157, 'Bayern Munich'],
      [165, 'Borussia Dortmund'],
      [168, 'Bayer Leverkusen'],
      [173, 'Rb Leipzig'],
      [163, 'Bayern Mochengab']
      // Add more team codes and names as needed
    ];

    public ligueOneTeams: [number, string][] = [
      [85, 'Paris Saint-German'],
      [91, 'Monaco'],
      [80, 'Lyon'],
      [81, 'Marseille'],
    ];

    public ducthTeams: [number, string][] = [
      [194, 'Ajax'],
      [197, 'PSV Edinhoven'],
      [209, 'Feyenoord']
    ];

    constructor(private http: HttpClient) { }

  fetchTeamStatistics(leagueCode: number, season: number, teamCode: number): Observable<TeamStatistics> {
    const url = `${TeamStatisticsService.API_URL}?league=${leagueCode}&season=${season}&team=${teamCode}`;
    const headers = new HttpHeaders({
      'x-rapidapi-key': TeamStatisticsService.API_TOKEN,
      'x-rapidapi-host': 'v3.football.api-sports.io'
    });

    return this.http.get<any>(url, { headers }).pipe(
      map(response => this.transformResponse(response)),
      catchError(error => {
        console.error('Error fetching team statistics:', error);
        return throwError(() => new Error('Failed to fetch team statistics'));
      })
    );
  }

  private transformResponse(response: any): TeamStatistics {
    if (!response) {
      throw new Error('No response data');
    }

    return {
      id: response.id ?? 0, // Default values if null or undefined
      leagueId: response.leagueId ?? 0,
      leagueName: response.leagueName ?? 'Unknown League',
      leagueCountry: response.leagueCountry ?? 'Unknown Country',
      leagueLogo: response.leagueLogo ?? '',
      leagueFlag: response.leagueFlag ?? '',
      season: response.season ?? new Date().getFullYear(),
      teamId: response.teamId ?? 0,
      teamName: response.teamName ?? 'Unknown Team',
      teamLogo: response.teamLogo ?? '',
      form: response.form ?? '',
      homePlayed: response.homePlayed ?? 0,
      awayPlayed: response.awayPlayed ?? 0,
      totalPlayed: response.totalPlayed ?? 0,
      homeWins: response.homeWins ?? 0,
      awayWins: response.awayWins ?? 0,
      totalWins: response.totalWins ?? 0,
      homeDraws: response.homeDraws ?? 0,
      awayDraws: response.awayDraws ?? 0,
      totalDraws: response.totalDraws ?? 0,
      homeLoses: response.homeLoses ?? 0,
      awayLoses: response.awayLoses ?? 0,
      totalLoses: response.totalLoses ?? 0,
      goalsForHome: response.goalsForHome ?? 0,
      goalsForAway: response.goalsForAway ?? 0,
      goalsForTotal: response.goalsForTotal ?? 0,
      goalsAgainstHome: response.goalsAgainstHome ?? 0,
      goalsAgainstAway: response.goalsAgainstAway ?? 0,
      goalsAgainstTotal: response.goalsAgainstTotal ?? 0,
      cleanSheetHome: response.cleanSheetHome ?? 0,
      cleanSheetAway: response.cleanSheetAway ?? 0,
      cleanSheetTotal: response.cleanSheetTotal ?? 0,
      failedToScoreHome: response.failedToScoreHome ?? 0,
      failedToScoreAway: response.failedToScoreAway ?? 0,
      failedToScoreTotal: response.failedToScoreTotal ?? 0,
      yellowCards: response.yellowCards ?? 0,
      redCards: response.redCards ?? 0,
      biggestWinStreak: response.biggestWinStreak ?? 0,
      biggestDrawStreak: response.biggestDrawStreak ?? 0,
      biggestLoseStreak: response.biggestLoseStreak ?? 0,
      biggestHomeWin: response.biggestHomeWin ?? '',
      biggestAwayWin: response.biggestAwayWin ?? '',
      biggestHomeLose: response.biggestHomeLose ?? '',
      biggestAwayLose: response.biggestAwayLose ?? '',
      biggestGoalsForHome: response.biggestGoalsForHome ?? 0,
      biggestGoalsForAway: response.biggestGoalsForAway ?? 0,
      biggestGoalsAgainstHome: response.biggestGoalsAgainstHome ?? 0,
      biggestGoalsAgainstAway: response.biggestGoalsAgainstAway ?? 0
    };
  }
}
