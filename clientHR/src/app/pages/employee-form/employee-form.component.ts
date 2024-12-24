import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormsModule, FormControl, FormGroup, Validators, ReactiveFormsModule, FormBuilder } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SettingsComponent } from '../../components/settings/settings.component';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { Employee } from '../../models/employee';
import { EmployeeService } from '../../service/employee.service';
import { Fonction } from '../../models/fonction';
import Validation from '../../utils/validation';
import { ChangeDetectorRef } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [FormsModule, CommonModule, SettingsComponent, RouterModule, ReactiveFormsModule ],
  templateUrl: './employee-form.component.html',
  styleUrl: './employee-form.component.css'
})
export class EmployeeFormComponent implements OnInit{

  url !: any;
  employee: Employee= new Employee();
  isEditMode: boolean = false;
  submitted = false;
  employeeId !:number

  form: FormGroup = new FormGroup({
    prenom: new FormControl(''),
    nom: new FormControl(''),
    email: new FormControl(''),
    fonction: new FormControl(''),
    mobile: new FormControl(''),
    addresse: new FormControl(''),
    dateNaissance: new FormControl(''),
    image: new FormControl(''),
    
  });
  
  fonctions = Object.values(Fonction);
  constructor(
    public employeeService:EmployeeService,
    private formBuilder: FormBuilder, 
    private router:Router,
    private cdr: ChangeDetectorRef,
    private route: ActivatedRoute
    ) {}

  ngOnInit(): void {
   this.employeeId = this.route.snapshot.params['idEmployee'];
    if (this.employeeId) {
      this.isEditMode = true;
      this.getEmployeeDetails(this.employeeId);
    }
    this.form = this.formBuilder.group(
      {
        prenom: ['', 
          [ 
            Validators.required,
            Validators.minLength(6),
          ]
          ],
        nom: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(20),
          ],
        ],
        fonction: [
          '', 
          [
            Validators.required 
          ]
        ],
        mobile: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(8), Validators.pattern(/^\d+$/)]],
        email: ['', [Validators.required, Validators.email]],
        addresse: [
          '', 
          [
            Validators.required,
            Validators.minLength(5), 
            Validators.maxLength(30),   
          ]
        ],
        dateNaissance: ['', [Validators.required]],
        image: ['', [Validators.required]],
      
      }
    );
  }

  getEmployeeDetails(id: number): void {
    const token = localStorage.getItem('auth-token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
    this.employeeService.getEmployeeById(id,headers).subscribe({
      next: (data) => {
        this.employee = data;

        // Set form values (including image URL or path)
        this.form.patchValue({
          prenom: data.prenom,
          nom: data.nom,
          email: data.email,
          mobile: data.mobile,
          fonction: data.fonction,
          addresse: data.addresse,
          dateNaissance: data.dateNaissance,
          image: data.image // This is the image path or URL
        });
      },
      error: (err) => console.error('Error fetching employee details:', err),
    });
  }




  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }


  readUrl(event:any) {
    if (event.target.files && event.target.files[0]) {
      
      var reader = new FileReader();
  
      reader.onload = (event: ProgressEvent) => {
        this.url = (<FileReader>event.target).result;
      }
    
      reader.readAsDataURL(event.target.files[0]);
      this.form.value.image=event.target.files[0].name; 
      console.log(event.target.files[0].name);
    }
  }


  AddEmployee() {
    const token = localStorage.getItem('auth-token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  
    // Create a copy of the employee object and clean up the image path
    const employeeToSave = { ...this.employee };
    
    // Extract just the filename from the image path
    if (employeeToSave.image) {
      employeeToSave.image = employeeToSave.image.split('\\').pop()?.split('/').pop() || '';
    }
  
    this.employeeService.AddEmployee(employeeToSave, headers).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['/employees']);
        window.location.reload();
      },
      error => console.log(error)
    );
  }
  onSubmit(): void {
    this.submitted = true;
    this.cdr.detectChanges();
    
    if (this.form.invalid) {
      return;
    }
  
    // Map form values to employee model
    const formValues = { ...this.form.value };
    
    // Clean up image path before saving
    if (formValues.image) {
      formValues.image = formValues.image.split('\\').pop()?.split('/').pop() || '';
    }
    
    this.employee = { ...this.employee, ...formValues };
  
    if (this.isEditMode) {
      this.updateEmployee();
    } else {
      this.AddEmployee();
    }
    
    console.log(JSON.stringify(this.form.value, null, 2));
  }

  updateEmployee(): void {
    const token = localStorage.getItem('auth-token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
    this.employeeService.ModifierEmployee(this.employee.idEmployee, this.employee,headers).subscribe({
      next: () => {
        alert('Employee updated successfully!');
        this.router.navigate(['/employees']);
      },
      error: (err) => console.error('Error updating employee:', err),
    });
  }




  onReset(): void {
    this.submitted = false;
    this.form.reset();
    if (this.isEditMode) {
      this.form.patchValue(this.employee); // Refill the form with current data in edit mode
    }
  }

}
