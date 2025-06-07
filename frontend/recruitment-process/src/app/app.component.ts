import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ApplyJobComponent } from './apply-job/apply-job.component';
import { HttpClientModule } from '@angular/common/http';; 

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,ApplyJobComponent,HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'recruitment-process';
}
