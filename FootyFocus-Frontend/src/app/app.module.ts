import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PlayerPageComponent } from './pages/player-page/player-page.component';
import { HttpClientModule } from '@angular/common/http';
import { LeagueStandingComponent } from './pages/league-standing/league-standing.component';
import { FormsModule } from '@angular/forms';
<<<<<<< HEAD
import { DetailedPlayerComponent } from './pages/detailed-player/detailed-player.component';
import { DetailedPlayerInfoService } from './services/detailed player information service/detailed-player-info.service';

=======
import { LoginRegisterComponent } from './pages/login-register/login-register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AlertModalComponent } from './components/alert-modal/alert-modal.component';
import { PlayerWatchlistComponent } from './pages/player-watchlist/player-watchlist.component';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MatchdayComponent } from './pages/matchday/matchday.component';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';
import { CoolFormComponent } from './components/cool-form/cool-form.component';
>>>>>>> 3830bf3f8c969c36802466de2a2fe0e3c9a89db1
@NgModule({
  declarations: [
    AppComponent,
    PlayerPageComponent,
    LeagueStandingComponent,
<<<<<<< HEAD
    DetailedPlayerComponent
=======
    LoginRegisterComponent,
    AlertModalComponent,
    PlayerWatchlistComponent,
    NavbarComponent,
    MatchdayComponent,
    ProfilePageComponent,
    CoolFormComponent
>>>>>>> 3830bf3f8c969c36802466de2a2fe0e3c9a89db1
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule, // addeds ngModel in the application
    ReactiveFormsModule
  ],
  providers: [DetailedPlayerInfoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
