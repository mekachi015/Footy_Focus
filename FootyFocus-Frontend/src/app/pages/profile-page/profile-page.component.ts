import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth service/auth.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit{

  user: any = {};  // Initialize user object
  isEditing = false;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.loadUserInfo();
  }

  loadUserInfo(): void {
    this.authService.getUserProfile().subscribe({
      next: (userData) => {
        this.user = userData;  // Update user information with data from the service
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
    // You may want to add this logic to upload the file and update the user's profile picture
  }

  saveProfile(): void {
    // Remove email from the profile data to avoid changes to the email field
    const updatedUser = { ...this.user };
    delete updatedUser.email;

    this.authService.updateUserProfile(updatedUser).subscribe({
      next: (response) => {
        console.log('Profile updated successfully', response);
        this.isEditing = false;
        this.loadUserInfo();  // Reload user info after successful update
      },
      error: (err) => {
        console.error('Error updating profile', err);
      }
    });
  }}
