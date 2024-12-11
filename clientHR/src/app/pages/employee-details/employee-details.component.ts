import { Component } from '@angular/core';
import { SettingsComponent } from '../../components/settings/settings.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-employee-details',
  standalone: true,
  imports: [SettingsComponent,FormsModule, CommonModule],
  templateUrl: './employee-details.component.html',
  styleUrl: './employee-details.component.css'
})
export class EmployeeDetailsComponent {


}
