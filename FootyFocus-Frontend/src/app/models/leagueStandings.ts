// export interface LeagueStandings{  // an interface for getting the nested information within the json file
    
//     id: number ;
//     competition:{
    
//         competitionName: string;
//         competitionEmblem: string;
//     },

//     season:{
//         seasonStartDate: string;
//         seasonEndDate: string;
//         currentMatchDay: string;
//     },

//     standings:{
        
//         table: {
//             position: number;
            
//             team:{
//                 teamName: string;
//                 teamShortName: string;
//                 teamCrest: string;
//             },
//             matchesPlayed: number;
//             teamForm: string;
//             gamesWon: number;
//             gamesLost: number;
//             gamesDrew: number;
//             noOfPoints: number;
//             goalDifference: number;
//         }
//     }
//}

export interface LeagueStandings{

    id: number | null;
  competitionName: string;
  competitionEmblem: string;
  seasonStartDate: string;
  seasonEndDate: string;
  currentMatchDay: number;
  position: number;
  teamName: string;
  teamCrest: string;
  teamShortName: string;
  matchesPlayed: number;
  teamForm: string;
  gamesWon: number;
  gamesLost: number;
  gamesDrew: number;
  noOfPoints: number;
  goalDifference: number;
}
