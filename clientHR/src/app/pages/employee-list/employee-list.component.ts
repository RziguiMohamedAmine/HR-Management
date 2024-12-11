import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SettingsComponent } from '../../components/settings/settings.component';
import { RouterModule } from '@angular/router';
import { Router } from 'express';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [FormsModule, CommonModule, SettingsComponent, RouterModule],
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css',
  //host: {ngSkipHydration: 'true'},
})
export class EmployeeListComponent {
 

employeeId = '';   
employeeName = '';
  
employee = {
  firstName: '',
  lastName: '',
  holidaysPermission: false,
  leavesPermission: false
};

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
onSubmit() {
  console.log('Employee Data:', this.employee);
  // You can handle form submission here, e.g., send the data to an API
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
