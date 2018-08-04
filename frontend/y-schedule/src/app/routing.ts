
import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { EmployeeHomeComponent } from './components/employee-home/employee-home.component';
import { EmployeeAvailComponent } from './components/employee-avail/employee-avail.component';
import { ManagerHomeComponent } from './components/manager/manager-home/manager-home.component';
import { CoordinatorHomeComponent } from './components/coordinator/coordinator-home/coordinator-home.component';
import { CoordinatorRequestViewComponent } from './components/coordinator/coordinator-request-view/coordinator-request-view.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { EditTimeSheetComponent } from './components/manager/edit-time-sheet/edit-time-sheet.component';
import { MessagesMainComponent } from './components/messages/messages-main/messages-main.component';

export const approutes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'home', component: EmployeeHomeComponent },
    { path: 'employee/avail', component: EmployeeAvailComponent},
    { path: 'manage/register', component: RegistrationComponent },
    { path: 'manage/timesheet', component: EditTimeSheetComponent },
    { path: 'manage', component: ManagerHomeComponent},
    { path: 'coordinate', component: CoordinatorHomeComponent},
    { path: 'coordinate/request', component: CoordinatorRequestViewComponent},
    { path: 'messages', component: MessagesMainComponent},
];
