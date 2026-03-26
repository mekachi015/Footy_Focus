// src/app/interfaces/matches.interface.ts

export interface Match {
    id: number;
    totalMatches: number;
    matchesPlayed: number;
    compName: string;
    compEmblem: string;
    utcDate: string;
    homeTeamName: string;
    homeTeamShortName: string;
    awayTeamShortName: string;
    homeTeamCrest: string;
    awayTeamName: string;
    awayTeamCrest: string;
    homeFullScore: number;
    awayFullScore: number;
    homeHalfScore: number;
    awayHalfScore: number;
    refName: string;
  }
  