import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { FormsModule } from '@angular/forms'; // Import FormsModule
import { Router } from '@angular/router'; // Import Router

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true, // Mark as standalone
  imports: [FormsModule] // Import FormsModule
})
export class LoginComponent {
  credentials = {
    email: '',
    password: ''
  };

  constructor(private toastr: ToastrService, private router: Router) {} // Inject Router

  onLogin() {
    const { email, password } = this.credentials;

    // Simulate login logic (replace with actual API call)
    if (email === 'user@example.com' && password === 'password123') {
      this.toastr.success('User  successfully logged in!', 'Success');
      this.router.navigate(['/dashboard']);
    } else {
      this.toastr.error('Wrong username or password.', 'Login Failed');
    }
  }

  onForgotPassword() {
    this.toastr.warning('Forgot password functionality not implemented.', 'Warning');
  }

  onRegisterRedirect() {
    this.router.navigate(['/register']); // Navigate to the register page immediately
  }
}