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
import { ManagerHomeComponent } from './components/manager-home/manager-home.component';
import { CoordinatorHomeComponent } from './components/coordinator-home/coordinator-home.component';
import { CookieService } from 'ngx-cookie-service';
import { EmployeeWeekViewComponent } from './components/views/employee-week-view/employee-week-view.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    EmployeeHomeComponent,
    RegistrationComponent,
    ManagerHomeComponent,
    CoordinatorHomeComponent,
    EmployeeWeekViewComponent
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
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
