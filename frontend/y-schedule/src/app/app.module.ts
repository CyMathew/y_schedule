import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { RouterModule} from '@angular/router';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { approutes } from './routing';
import { LoginComponent } from './components/login/login.component';
import { EmployeeHomeComponent } from './components/employee-home/employee-home.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { ManagerHomeComponent } from './components/manager/manager-home/manager-home.component';
import { CoordinatorHomeComponent } from './components/coordinator-home/coordinator-home.component';
import { CookieService } from 'ngx-cookie-service';
import { ManagerHomePanelComponent } from './components/manager/manager-home-panel/manager-home-panel.component';
import { Globals } from './globals';
import { UserSidebarComponent } from './components/user-sidebar/user-sidebar.component';
import { EditTsWeekComponent } from './components/manager/edit-ts-week/edit-ts-week.component';
import { EditTsGraphComponent } from './components/manager/edit-ts-graph/edit-ts-graph.component';
import { EditTsSidebarComponent } from './components/manager/edit-ts-sidebar/edit-ts-sidebar.component';
import { EditTimeSheetComponent } from './components/manager/edit-time-sheet/edit-time-sheet.component';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    EmployeeHomeComponent,
    RegistrationComponent,
    ManagerHomeComponent,
    CoordinatorHomeComponent,
    ManagerHomePanelComponent,
    UserSidebarComponent,
    EditTimeSheetComponent,
    EditTsWeekComponent,
    EditTsGraphComponent,
    EditTsSidebarComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'My-Xsrf-Cookie',
      headerName: 'My-Xsrf-header'
    }),
    RouterModule.forRoot(approutes),
  ],
  providers: [CookieService, Globals],
  bootstrap: [AppComponent]
})
export class AppModule { }
