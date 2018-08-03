
import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { EmployeeHomeComponent } from './components/employee/employee-home/employee-home.component';
import { EmployeeAvailComponent } from './components/employee/employee-avail/employee-avail.component';
import { ManagerHomeComponent } from './components/manager/manager-home/manager-home.component';
import { CoordinatorHomeComponent } from './components/coordinator-home/coordinator-home.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { EditTimeSheetComponent } from './components/manager/edit-time-sheet/edit-time-sheet.component';

export const approutes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'home', component: EmployeeHomeComponent },
    { path: 'employee/avail', component: EmployeeAvailComponent},
    { path: 'manage/register', component: RegistrationComponent },
    { path: 'manage/timesheet', component: EditTimeSheetComponent },
    { path: 'manage', component: ManagerHomeComponent},
    { path: 'coordinate', component: CoordinatorHomeComponent},
];
