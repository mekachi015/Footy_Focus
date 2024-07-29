export interface Player{
    id: number;
    name: string;
    dateOfBirth: string;
    nationality: string;
    section: string;
    shirtNumber: number;

   currentTeam:{ 
    flag: string;
    code: string;
    name: string;
   }
}
    
