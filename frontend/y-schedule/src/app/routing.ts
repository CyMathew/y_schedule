import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { EmployeeHomeComponent } from './components/employee-home/employee-home.component';
import { RegistrationComponent } from './components/registration/registration.component';


export const approutes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'home', component: EmployeeHomeComponent },
    { path: 'register', component: RegistrationComponent }


];