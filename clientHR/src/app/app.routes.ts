import { Routes,RouterModule } from '@angular/router';
import { EmployeeListComponent } from './pages/employee-list/employee-list.component';
import { EmployeeFormComponent } from './pages/employee-form/employee-form.component';
import { EmployeeDetailsComponent } from './pages/employee-details/employee-details.component';
import { HomeComponent } from './layouts/home/home.component';
import { LeavesListComponent } from './pages/leaves-list/leaves-list.component';
import { AttendanceListComponent } from './pages/attendance-list/attendance-list.component';
import { AttendanceDetailsComponent } from './pages/attendance-details/attendance-details.component';

export const routes: Routes = [
    { path: '', component: HomeComponent, pathMatch: 'full' },
    { path: 'employees', component: EmployeeListComponent },
    { path: 'leaves', component: LeavesListComponent },
    { path: 'attendance', component: AttendanceListComponent },
    { path: 'employees/add', component: EmployeeFormComponent },
    { path: 'employees/edit/:id', component: EmployeeFormComponent },
    { path: 'employees/:id', component: EmployeeDetailsComponent },
    { path: 'attendance/:id', component: AttendanceDetailsComponent },
    { path: '**', redirectTo: '', pathMatch: 'full' }
];
 