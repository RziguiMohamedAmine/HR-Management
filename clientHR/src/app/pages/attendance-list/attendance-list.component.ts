import { Component } from '@angular/core';
import { SettingsComponent } from '../../components/settings/settings.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule ,Router} from '@angular/router';

@Component({
  selector: 'app-attendance-list',
  standalone: true,
  imports: [SettingsComponent,FormsModule, CommonModule, RouterModule],
  templateUrl: './attendance-list.component.html',
  styleUrl: './attendance-list.component.css'
})
export class AttendanceListComponent {

  constructor(private router: Router) {}

  navigateToAttendance(id: number): void {
    this.router.navigate(['/attendance', id]);
  }

}
