import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { RouterModule} from '@angular/router';
import { DatePipe } from '@angular/common';
import { approutes } from './routing';

import { CookieService } from 'ngx-cookie-service';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { EmployeeHomeComponent } from './components/employee/employee-home/employee-home.component';
import { EmployeeAvailComponent } from './components/employee/employee-avail/employee-avail.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { ManagerHomeComponent } from './components/manager/manager-home/manager-home.component';
import { CoordinatorHomeComponent } from './components/coordinator-home/coordinator-home.component';
import { ManagerHomePanelComponent } from './components/manager/manager-home-panel/manager-home-panel.component';
import { UserSidebarComponent } from './components/user-sidebar/user-sidebar.component';
import { EditTsWeekComponent } from './components/manager/edit-ts-week/edit-ts-week.component';
import { EditTsGraphComponent } from './components/manager/edit-ts-graph/edit-ts-graph.component';
import { EditTsSidebarComponent } from './components/manager/edit-ts-sidebar/edit-ts-sidebar.component';
import { EditTimeSheetComponent } from './components/manager/edit-time-sheet/edit-time-sheet.component';
import { HighlightColumnDirective } from './directives/highlight-column.directive';
import { EmployeeWeekViewComponent } from './components/views/employee-week-view/employee-week-view.component';
import { ManagerDayViewComponent } from './components/views/manager-day-view/manager-day-view.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    EmployeeHomeComponent,
    RegistrationComponent,
    ManagerHomeComponent,
    CoordinatorHomeComponent,
    EmployeeWeekViewComponent,
    ManagerHomePanelComponent,
    UserSidebarComponent,
    EditTimeSheetComponent,
    EditTsWeekComponent,
    EditTsGraphComponent,
    EditTsSidebarComponent,
    HighlightColumnDirective,
    EmployeeAvailComponent,
    ManagerDayViewComponent,
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
  providers: [CookieService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
