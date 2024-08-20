export interface PlayerSearch {
    firstName: string;
    lastName: string;
    playerAge: number;
    dateOfBirth: string;
    placeOfBirth: string;
    countryOfBirth: string;
    height: number;
    weight: number;
    injuredStatus: boolean;
    playerPhoto: string;
    teamName?: string;
    teamLogo?: string;
    leagueName?: string;
    leagueLogo?: string;
    noAppearences?: number;
    minutesPlayed?: number;
    position?: string;
    rating?: number;
    captain?: boolean;
    shotsTaken?: number;
    shotsOnTarget?: number;
    totalGoalsScored?: number;
    goalsConceeded?: number;
    noAssits?: number;
    noSaves?: string;
    totalPasses?: number;
    totalKeyPasses?: number;
    passingAccuracy?: number;
    totalTackles?: number;
    blocks?: number;
    interceptions?: number;
    totalDuels?: number;
    duelsWon?: number;
    dribbleAttempts?: number;
    successfullDribbles?: number;
    foulsDrawn?: number;
    foulsCommitted?: number;
    noYellowCards?: number;
    noYellowRedCards?: number;
    noRedCards?: number;
    penaltiesWon?: number;
    penaltiesCommited?: number;
    penaltiesScored?: number;
    penaltiesMissed?: number;
    penalitesSaved?: number;
  }