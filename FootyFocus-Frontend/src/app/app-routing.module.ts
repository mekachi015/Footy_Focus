import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayerPageComponent } from './pages/player-page/player-page.component';
import { LeagueStandingComponent } from './pages/league-standing/league-standing.component';
<<<<<<< HEAD
import { DetailedPlayerComponent } from './pages/detailed-player/detailed-player.component';

const routes: Routes = [
  {path: 'player', component:PlayerPageComponent},
  {path: 'league-standings', component:LeagueStandingComponent},
  {path: 'players/:id', component:DetailedPlayerComponent}
=======
import { LoginRegisterComponent } from './pages/login-register/login-register.component';
import { PlayerWatchlistComponent } from './pages/player-watchlist/player-watchlist.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MatchdayComponent } from './pages/matchday/matchday.component';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';
import { AuthGuard } from './services/auth gaurd/auth-gaurd';

const routes: Routes = [
  {path: 'player', component:PlayerPageComponent},
  {path: 'league-standings', component:LeagueStandingComponent, canActivate: [AuthGuard] },
  {path: 'login', component:LoginRegisterComponent},
  {path: 'player-wishlist', component: PlayerWatchlistComponent, canActivate: [AuthGuard] },
  {path: 'matchday', component: MatchdayComponent},
  {path: 'profile', component:ProfilePageComponent, canActivate: [AuthGuard] }
 
>>>>>>> 3830bf3f8c969c36802466de2a2fe0e3c9a89db1
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
