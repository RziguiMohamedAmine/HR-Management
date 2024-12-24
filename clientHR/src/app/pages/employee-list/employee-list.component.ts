import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormsModule, FormControl, FormGroup, Validators, ReactiveFormsModule, FormBuilder } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SettingsComponent } from '../../components/settings/settings.component';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { Employee } from '../../models/employee';
import { EmployeeService } from '../../service/employee.service';
import { Fonction } from '../../models/fonction';
import Validation from '../../utils/validation';
import Swal from 'sweetalert2';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [FormsModule, CommonModule, SettingsComponent, RouterModule, ReactiveFormsModule ],
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css',
  //host: {ngSkipHydration: 'true'},
})
export class EmployeeListComponent implements OnInit {
  id!:number;
  url !: any;
  employee: Employee= new Employee();
  employees: Employee[] = [];




  constructor(public employeeService:EmployeeService,private router:Router,private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getEmployeeList();
    
  }

 
  delete(id: number)
  {const token = localStorage.getItem('auth-token');
  if (token) {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#ff902f',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!',
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire(
          'Deleted!',
          'Your file has been deleted.',
          'success'
        )
        this.employeeService.deleteEmployee(id,headers).subscribe(data=>{
          this.getEmployeeList();
        });
      }
    })            
  }
  }


  AddEmployee()
  {
    const token = localStorage.getItem('auth-token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
      this.employeeService.AddEmployee(this.employee,headers).subscribe(data=>{
      console.log(data);
    },error=>console.log(error));
  
  }
  readUrl(event:any) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();
  
      reader.onload = (event: ProgressEvent) => {
        this.url = (<FileReader>event.target).result;
      }
    
      reader.readAsDataURL(event.target.files[0]);
      this.employee.image=event.target.files[0].name;
    }
  }


 
  getEmployeeList(): void {
    const token = localStorage.getItem('auth-token');
    if (token) {
     // console.log(token);
      const headers = new HttpHeaders({
        Authorization: `Bearer ${token}`
      });
      this.employeeService.getEmployees(headers).subscribe(
        (data: Employee[]) => {
          this.employees = data;
        },
        (error) => {
          console.error('Error fetching employees:', error);
        }
      );
    } else {
      console.error('No auth token found!');
    }
  }




employeeId = '';   
employeeName = '';
  

editedEmployee = {
  firstName: 'John',
  lastName: 'Doe',
  username: 'johndoe',
  permissions: {
    holidays: true,
    leaves: false
  }
};

// Control modal visibility
showEditModal: boolean = true;

// Toggle the modal visibility
toggleEditModal() {
  this.showEditModal = !this.showEditModal;
}
onEditSubmit() {
  console.log('Edited Employee Data:', this.editedEmployee);
  // Handle the data submission logic here
  this.toggleEditModal(); // Close the modal after submission
}

// Control modal visibility
showModal: boolean = true;

// Function to toggle the modal visibility
toggleModal() {
  this.showModal = !this.showModal;
}

// Employee to be deleted
employeeToDelete: any = null;
showDeleteModal: boolean = true;

  // Toggle the Delete Modal
  toggleDeleteModal() {
    //this.employeeToDelete = employee;  // Store the employee data to be deleted
    this.showDeleteModal = !this.showDeleteModal;
  }

onDelete() {
  this.toggleDeleteModal();
  }

selectedDesignation = '';
designations = ['Web Developer', 'Web Designer', 'Android Developer', 'iOS Developer'];


onSearch() {
  console.log('Searching with:', this.employeeId, this.employeeName, this.selectedDesignation);
}

}
