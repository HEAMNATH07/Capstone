import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { FormsModule } from '@angular/forms'; // Import FormsModule
import { Router } from '@angular/router'; // Import Router

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  standalone: true, // Mark as standalone
  imports: [FormsModule] // Import FormsModule
})
export class RegisterComponent {
  user = {
    username: '',
    email: '',
    employeeId: '',
    password: '',
    confirmPassword: ''
  };

  constructor(private toastr: ToastrService, private router: Router) {} // Inject Router

  onRegister() {
    const { password, confirmPassword } = this.user;

    if (password !== confirmPassword) {
      this.toastr.warning('Confirm password and password do not match.', 'Warning');
      return;
    }

    // Simulate registration logic (replace with actual API call)
    this.toastr.success('User  created successfully!', 'Success');
  }

  onCancel() {
    // Logic to redirect to login page
    this.router.navigate(['/login']); // Redirect to the login page
  }

  onRegisterRedirect() {
    // Logic to redirect to login page
    this.toastr.info('Redirecting to login page...', 'Redirecting');
    this.router.navigate(['/login']); // Navigate to the login page
  }
}