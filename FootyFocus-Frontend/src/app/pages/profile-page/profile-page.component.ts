import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth service/auth.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit{

  user: any = {};  // Replace with your actual user model
  isEditing = false;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.loadUserInfo();
  }

  loadUserInfo(): void {
    this.authService.getUserInfo().subscribe({
      next: (userData) => {
        this.user = userData;
      },
      error: (err) => {
        console.error('Error fetching user info', err);
      }
    });
  }

  toggleEdit(): void {
    this.isEditing = !this.isEditing;
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    // Handle file upload logic here
  }

  saveProfile(): void {
    // Implement save profile logic here
    this.isEditing = false;
  }

}
