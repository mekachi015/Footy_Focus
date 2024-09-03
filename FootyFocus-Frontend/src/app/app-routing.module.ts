import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayerPageComponent } from './pages/player-page/player-page.component';
import { LeagueStandingComponent } from './pages/league-standing/league-standing.component';
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
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
