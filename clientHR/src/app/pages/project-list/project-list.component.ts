import { Component, OnInit } from '@angular/core';
import { ProjetService } from '../../service/projet.service';
import { Project } from '../../models/project';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-project-list',
  standalone: true,
  imports: [],
  templateUrl: './project-list.component.html',
  styleUrl: './project-list.component.css'
})
export class ProjectListComponent implements OnInit {

  project: Project[] = [];




  constructor(public projectService:ProjetService,private router:Router,private formBuilder: FormBuilder) { }

  ngOnInit(): void {
   // this.getEmployeeList();
    
  }



}
