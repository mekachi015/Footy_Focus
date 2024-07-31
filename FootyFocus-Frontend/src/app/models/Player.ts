export interface Player{ // an interface that keeps track of the 10 scorers of a league

    id: number | null;
    compName: string;
    compCode: string | null;
    compEmblem: string;
    seasonStartDate: string;
    seasonEndDate: string;
    currentMatchday: number;
    playerName: string;
    dateOfBirth: string | null;
    nationality: string;
    section: string;
    teamName: string;
    teamShortName: string;
    teamCrest: string;
    goalsScored: number;
    noOfAssits: number;
}
    
