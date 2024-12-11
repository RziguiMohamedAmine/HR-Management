import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SettingsComponent } from '../../components/settings/settings.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-leaves-list',
  standalone: true,
  imports: [FormsModule, CommonModule, SettingsComponent, RouterModule],
  templateUrl: './leaves-list.component.html',
  styleUrl: './leaves-list.component.css'
})
export class LeavesListComponent {

}
