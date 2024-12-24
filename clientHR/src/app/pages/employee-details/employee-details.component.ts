import { Component, OnInit } from '@angular/core';
import { SettingsComponent } from '../../components/settings/settings.component';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormsModule } from '@angular/forms';
import { EmployeeService } from '../../service/employee.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Employee } from '../../models/employee';
import { ProjetService } from '../../service/projet.service';
import { Project } from '../../models/project';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-employee-details',
  standalone: true,
  imports: [SettingsComponent,FormsModule, CommonModule, RouterModule],
  templateUrl: './employee-details.component.html',
  styleUrl: './employee-details.component.css'
})
export class EmployeeDetailsComponent implements OnInit{
  id!:any;
  employee !: any;
  projet !: any;
  employees: Employee[] = [];
  idProject !:number;
  constructor(public employeeService:EmployeeService,public projectService:ProjetService,private router:Router,private formBuilder: FormBuilder,private ac : ActivatedRoute) { }

  ngOnInit(): void {
    //this.getEmployeeList()
     this.id = this.ac.snapshot.params['idEmployee'];
    this.employee = new Employee();
    this.projet = new Project();
    const token = localStorage.getItem('auth-token');
    if (token) {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
    this.employeeService.getEmployeeById(this.id,headers).subscribe(data=>{
      this.employee = data;
      console.log(data)
      //this.idProject = data.projet.idProjet
      if(data.projet){
        this.projectService.getProjectById(data.projet.idProjet,headers).subscribe(data=>{
          this.projet = data;
        })
        this.getEmployeeList(this.employee)
      }
      
      //console.log(this.employee)
      
  });
      } else {
        console.error('No auth token found!');
      }
  }


  getEmployeeList(emp:Employee): void {
    const token = localStorage.getItem('auth-token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
    this.projectService.getEmployees(emp.projet.idProjet,headers).subscribe((data: Employee[]) => {
      this.employees = data;
    // console.log(this.employees)
    });
  } 




}
