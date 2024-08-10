import { Component } from '@angular/core';
import {Router} from '@angular/router';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  constructor(private router: Router){}

  navigateToHome() {
    this.router.navigate(['/home']);
  }

  navigateToTop10() {
    this.router.navigate(['/player']);
  }

  navigateToWatchlist() {
    this.router.navigate(['/player-wishlist']);
  }

  navigateLeagueStandings() {
    this.router.navigate(['/league-standings']);
  }

  navigateToProfile() {
    this.router.navigate(['/profile']);
  }
}
