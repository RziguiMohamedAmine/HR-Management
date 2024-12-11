import { Component } from '@angular/core';
import { SettingsComponent } from '../../components/settings/settings.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [SettingsComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  host: {ngSkipHydration: 'true'},
})
export class HomeComponent {

}
