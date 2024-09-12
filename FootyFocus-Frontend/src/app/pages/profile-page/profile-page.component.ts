import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Profile } from 'src/app/models/Profile';
import { AuthService } from 'src/app/services/auth service/auth.service';
import { ProfileService } from 'src/app/services/Profile service/profile.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit{

  user: any = {};
  isEditing: boolean = false;

  constructor(private authService: AuthService,
    private route: Router,
     private profileService: ProfileService) {}

  ngOnInit(): void {
    this.loadUserProfile();
  }

  loadUserProfile(): void {
    this.authService.getUserProfileByEmail().subscribe(
      (data) => {
        this.user = data;
      },
      (error) => {
        console.error('Error fetching user profile', error);
      }
    );
  }

  toggleEdit(): void {
    this.isEditing = !this.isEditing;
  }

  saveProfile(): void {
    // // Implement save functionality
    // this.profileService.updateUserProfile(this.user).subscribe(
    //   (response) => {
    //     console.log('Profile updated successfully');
    //     this.toggleEdit();
    //   },
    //   (error) => {
    //     console.error('Error updating profile', error);
    //   }
    // );
  }

  logout(): void {
    this.authService.logout();
    Swal.fire({
      title: "Logged out successfully",
      text: "Redirecting to login",
      icon: "success",
      timer: 2000,  // Optional: Auto-close the alert after 2 seconds
      willClose: () => {
        this.route.navigate(['/login']);  // Replace '/login' with your actual login route
      }
    });
    
  }

  onFileSelected(event: any): void {
    // Handle file upload
  }
}
