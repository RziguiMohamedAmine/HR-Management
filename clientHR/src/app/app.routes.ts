import { Routes,RouterModule } from '@angular/router';
import { EmployeeListComponent } from './pages/employee-list/employee-list.component';
import { EmployeeFormComponent } from './pages/employee-form/employee-form.component';
import { EmployeeDetailsComponent } from './pages/employee-details/employee-details.component';
import { HomeComponent } from './layouts/home/home.component';
import { LeavesListComponent } from './pages/leaves-list/leaves-list.component';
import { AttendanceListComponent } from './pages/attendance-list/attendance-list.component';
import { AttendanceDetailsComponent } from './pages/attendance-details/attendance-details.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { AuthGuard } from './auth/auth.guard';

export const routes: Routes = [
    { path: '', component: HomeComponent,canActivate: [AuthGuard], pathMatch: 'full' },
    {
        path: 'employees',
        component: EmployeeListComponent, canActivate: [AuthGuard],
        children: [
          { path: 'add', component: EmployeeFormComponent },  // Add Employee form route
          { path: 'edit/:idEmployee', component: EmployeeFormComponent }, // Edit Employee route
        ]
         },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'leaves', component: LeavesListComponent, canActivate: [AuthGuard],},
    { path: 'attendance', component: AttendanceListComponent, canActivate: [AuthGuard], },
    { path: 'employees/:idEmployee', component: EmployeeDetailsComponent, canActivate: [AuthGuard] },
    { path: 'attendance/:id', component: AttendanceDetailsComponent ,canActivate: [AuthGuard],},
    { path: '**', redirectTo: '', pathMatch: 'full' }
];
 