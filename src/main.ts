import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes'; // Import your routes
import { ToastrModule } from 'ngx-toastr'; // Import ToastrModule
import { provideAnimations } from '@angular/platform-browser/animations'; // Import animations
import { importProvidersFrom } from '@angular/core'; // Import importProvidersFrom

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes), // Provide the router with the defined routes
    importProvidersFrom(ToastrModule.forRoot({
      positionClass: 'toast-top-left', // Set position to top left
      timeOut: 3000, // Duration in milliseconds
      preventDuplicates: true, // Prevent duplicate notifications
    })), // Provide ToastrModule globally
    provideAnimations() // Provide animations
  ]
}).catch(err => console.error(err));