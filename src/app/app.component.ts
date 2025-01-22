import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router'; // Import RouterOutlet
import { RouterModule } from '@angular/router'; // Import RouterModule

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  standalone: true, // Mark as standalone
  imports: [RouterOutlet, RouterModule] // Import RouterOutlet and RouterModule
})
export class AppComponent {
  title = 'user-auth-app';
}