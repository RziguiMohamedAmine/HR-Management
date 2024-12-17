import { Component, OnInit } from '@angular/core';
import { SettingsComponent } from '../../components/settings/settings.component';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormsModule } from '@angular/forms';
import { EmployeeService } from '../../service/employee.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Employee } from '../../models/employee';
import { ProjetService } from '../../service/projet.service';
import { Project } from '../../models/project';

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
    this.employeeService.getEmployeeById(this.id).subscribe(data=>{
      this.employee = data;
      this.idProject = data.projet.idProjet
      this.projectService.getProjectById(data.projet.idProjet).subscribe(data=>{
        this.projet = data;
      })
      //console.log(this.employee)
      this.getEmployeeList(this.employee)
  });
 
  }


  getEmployeeList(emp:Employee): void {
   
    this.projectService.getEmployees(emp.projet.idProjet).subscribe((data: Employee[]) => {
      this.employees = data;
    // console.log(this.employees)
    });
  } 




}
