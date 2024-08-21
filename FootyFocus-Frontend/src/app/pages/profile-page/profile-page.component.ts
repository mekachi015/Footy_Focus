import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Profile } from 'src/app/models/Profile';
import { AuthService } from 'src/app/services/auth service/auth.service';
import { ProfileService } from 'src/app/services/Profile service/profile.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit{

  user: any = {};  // Initialize user object
  isEditing = false;
  userProfile: Profile | undefined;
  userId: number | null = null; // Initialize userId as number or null
  constructor(private authService: AuthService,
    private profile: ProfileService,
    private router: Router
  ) {}

  ngOnInit(): void {

    this.userId = this.authService.getUserId();

    this.loadUserProfile()
  }

  loadUserProfile() {
    if (this.userId !== null) {
      this.profile.getUserProfile(this.userId).subscribe(
        (profile: Profile) => {
          this.userProfile = profile;
        },
        (error) => {
          console.error('Error fetching user profile:', error);
        }
      );
    }
  }

  logout(): void {
    this.authService.logout();
    window.alert("Logout succesful");
    this.router.navigate(["/login"]);
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
        this.loadUserProfile();  // Reload user info after successful update
      },
      error: (err) => {
        console.error('Error updating profile', err);
      }
    });
  }}
