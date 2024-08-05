import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayerPageComponent } from './pages/player-page/player-page.component';
import { LeagueStandingComponent } from './pages/league-standing/league-standing.component';
import { LoginRegisterComponent } from './pages/login-register/login-register.component';
import { PlayerWatchlistComponent } from './pages/player-watchlist/player-watchlist.component';

const routes: Routes = [
  {path: 'player', component:PlayerPageComponent},
  {path: 'league-standings', component:LeagueStandingComponent},
  {path: 'login', component:LoginRegisterComponent},
  {path: 'player-wishlist', component: PlayerWatchlistComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
