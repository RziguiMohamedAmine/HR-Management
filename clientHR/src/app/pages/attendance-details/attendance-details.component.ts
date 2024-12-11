import { Component } from '@angular/core';
import { SettingsComponent } from '../../components/settings/settings.component';

@Component({
  selector: 'app-attendance-details',
  standalone: true,
  imports: [SettingsComponent],
  templateUrl: './attendance-details.component.html',
  styleUrl: './attendance-details.component.css'
})
export class AttendanceDetailsComponent {

}
