import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayerPageComponent } from './pages/player-page/player-page.component';
import { LeagueStandingComponent } from './pages/league-standing/league-standing.component';

const routes: Routes = [
  {path: 'player', component:PlayerPageComponent},
  {path: 'league-standings', component:LeagueStandingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
