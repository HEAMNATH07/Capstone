import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  imports: [CommonModule,FontAwesomeModule],
})
export class DashboardComponent {
  user = {
    name: 'John Doe',
    email: 'john.doe@example.com',
    employeeId: 'EMP12345'
  };

  showDropdown = false;

  toggleDropdown() {
    this.showDropdown = !this.showDropdown;
  }
  constructor(private router: Router) {}

  // ... previous code remains the same
  navigateTo(page: string) {
    this.router.navigate([`/${page}`]);
  }

  // ... the rest of the component

  logout() {
    // Implement logout logic here
    console.log('User  logged out');
  }
  latestEvents = [
    { name: 'Event 1', date: '2023-10-01', time: '10:00 AM' },
    { name: 'Event 2', date: '2023-10-02', time: '11:00 AM' },
    { name: 'Event 3', date: '2023-10-03', time: '12:00 PM' },
    { name: 'Event 4', date: '2023-10-04', time: '01:00 PM' },
    { name: 'Event 5', date: '2023-10-05', time: '02:00 PM' },
  ];
}