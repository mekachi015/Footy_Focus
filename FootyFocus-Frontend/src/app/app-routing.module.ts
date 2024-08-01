import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayerPageComponent } from './pages/player-page/player-page.component';
import { LeagueStandingComponent } from './pages/league-standing/league-standing.component';
import { DetailedPlayerComponent } from './pages/detailed-player/detailed-player.component';

const routes: Routes = [
  {path: 'player', component:PlayerPageComponent},
  {path: 'league-standings', component:LeagueStandingComponent},
  {path: 'players/:id', component:DetailedPlayerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
