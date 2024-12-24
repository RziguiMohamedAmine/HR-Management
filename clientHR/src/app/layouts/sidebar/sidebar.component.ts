import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css',
  //host: {ngSkipHydration: 'true'},
})
export class SidebarComponent {
  openMenus: { [key: string]: boolean } = {};

  toggleSubmenu(menuId: string, event: Event): void {
    event.preventDefault(); // Prevent the # from being added to URL
    this.openMenus[menuId] = !this.openMenus[menuId];
  }

  isSubmenuOpen(menuId: string): boolean {
    return this.openMenus[menuId] || false;
  }
}
