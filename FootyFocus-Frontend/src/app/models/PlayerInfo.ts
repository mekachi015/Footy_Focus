export interface PlayerInfo{
  
  //player information
  firstname: string;
  lastname: string;
  age: number;
  height: string;
  weight: string;
  injured: boolean;
  photo: string;
  birthDate: string;
  birthPlace: string;
  position: string;
  captain: boolean;

  //player team information
  teamName: string;
  teamLogo: string;
  
  //player statistics
  //shots and saves
  goals: number;
  assists: number;
  totalShots: number;
  shotsOnTarget: number;
  saves: number;

  //passes
  totalPasses: number;
  keyPasses: number;
  passAccuracy: number;
  
  //defence
  totalTackles: number;
  blocks: number;
  interceptions: number;
  totalDuels: number;
  duelsWon: number;

  //dribbles
  dribbleAttempts: number;
  successfulDribbles: number;

  //fouls
  foulsDrawn: number;
  foulsCommited: number;

  //cards
  yellow: number;
  yellowRedCard: number;
  red: number;

  // penalties
  penaltiesWon: number;
  penaltiesCommited: number;
  penaltiesScored: number;
  penaltiesMissed: number;
  penaltiesSaved: number;
}