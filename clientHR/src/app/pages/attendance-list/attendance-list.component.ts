import { Component, OnInit } from '@angular/core';
import { AttendanceService } from '../../service/attendance.service';
import { Router, RouterModule } from '@angular/router';
import { Attendance } from '../../models/attendance';
import { SettingsComponent } from '../../components/settings/settings.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-attendance-list',
  standalone: true,
  templateUrl: './attendance-list.component.html',
  imports: [SettingsComponent, FormsModule, CommonModule, RouterModule],
  styleUrl: './attendance-list.component.css',
})
export class AttendanceListComponent implements OnInit {
  attendances: Attendance[] = [];
  employees: any[] = [];
  years: number[] = [];
  selectedMonth = new Date().getMonth() + 1;
  selectedYear = new Date().getFullYear();
  days: number[] = [];

  months = [
    'January', 'February', 'March', 'April', 'May', 'June',
    'July', 'August', 'September', 'October', 'November', 'December'
  ];

  constructor(
    public attendanceService: AttendanceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.generateYears();
    this.updateDays();
    this.fetchAttendanceData();
  }

  // Fetch attendance data for selected month and year
  fetchAttendanceData(): void {
    const token = localStorage.getItem('auth-token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
    if(token){
    this.attendanceService.getAttendanceByMonthAndYear(this.selectedMonth, this.selectedYear,headers)
      .subscribe({
        next: (data: Attendance[]) => {
          this.attendances = data;
          this.processAttendanceData();
        },
        error: (error) => {
          console.error('Error fetching attendances:', error);
          this.employees = [];
        }
      });
  }
}

  // Process attendance data for the current month and year
  processAttendanceData(): void {
    const employeeMap = new Map();
  
    this.attendances.forEach((entry) => {
      const entryDate = new Date(entry.day);
      const employee = entry.employee;
  
      if (!employeeMap.has(employee.idEmployee)) {
        employeeMap.set(employee.idEmployee, {
          id: employee.idEmployee,
          name: `${employee.nom} ${employee.prenom}`,
          attendance: {},
        });
      }
  
      const employeeRecord = employeeMap.get(employee.idEmployee);
      const dayNumber = entryDate.getDate();
      employeeRecord.attendance[dayNumber] = entry.absent ? 'fa fa-close text-danger' : 'fa-solid fa-check text-success';
    });
  
    this.employees = Array.from(employeeMap.values());
  }

  // Handle month or year change
  onDateChange(): void {
    this.updateDays();
    this.fetchAttendanceData(); // Fetch data for new month/year
  }

  // Update days for the selected month
  updateDays(): void {
    const daysInMonth = new Date(this.selectedYear, this.selectedMonth, 0).getDate();
    this.days = Array.from({ length: daysInMonth }, (_, i) => i + 1);
  }

  // Generate years for the dropdown
  generateYears(): void {
    const currentYear = new Date().getFullYear();
    this.years = Array.from({ length: 10 }, (_, i) => currentYear - i);
  }

  // Get attendance status for a specific employee and day
  getAttendanceStatus(employee: any, day: number): string {
    return employee.attendance[day] || '-';
  }

  // Navigate to individual attendance details
  navigateToAttendance(id: number): void {
    this.router.navigate(['/attendance', id]);
  }
}