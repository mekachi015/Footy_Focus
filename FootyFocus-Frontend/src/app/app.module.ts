import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PlayerPageComponent } from './pages/player-page/player-page.component';
import { HttpClientModule } from '@angular/common/http';
import { LeagueStandingComponent } from './pages/league-standing/league-standing.component';
import { FormsModule } from '@angular/forms';
import { LoginRegisterComponent } from './pages/login-register/login-register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AlertModalComponent } from './components/alert-modal/alert-modal.component';
import { PlayerWatchlistComponent } from './pages/player-watchlist/player-watchlist.component';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MatchdayComponent } from './pages/matchday/matchday.component';
@NgModule({
  declarations: [
    AppComponent,
    PlayerPageComponent,
    LeagueStandingComponent,
    LoginRegisterComponent,
    AlertModalComponent,
    PlayerWatchlistComponent,
    NavbarComponent,
    MatchdayComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule, // addeds ngModel in the application
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
