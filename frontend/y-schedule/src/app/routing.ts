import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { EmployeeHomeComponent } from './components/employee-home/employee-home.component';
import { ManagerHomeComponent } from './components/manager-home/manager-home.component';
import { CoordinatorHomeComponent } from './components/coordinator-home/coordinator-home.component';


export const approutes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'home', component: EmployeeHomeComponent },
    { path: 'manage', component: ManagerHomeComponent},
    { path: 'coordinate', component: CoordinatorHomeComponent},

];

@NgModule({
    imports: [RouterModule.forRoot(approutes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }