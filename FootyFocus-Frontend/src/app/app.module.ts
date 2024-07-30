import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PlayerPageComponent } from './pages/player-page/player-page.component';
import { HttpClientModule } from '@angular/common/http';
import { LeagueStandingComponent } from './pages/league-standing/league-standing.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    PlayerPageComponent,
    LeagueStandingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule // addeds ngModel in the application
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
