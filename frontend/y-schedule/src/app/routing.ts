import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { EmployeeHomeComponent } from './employee-home/employee-home.component';


export const approutes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'home', component: EmployeeHomeComponent }


];