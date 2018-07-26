import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule} from '@angular/router';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { approutes } from './routing';
import { LoginComponent } from './login/login.component';
import { EmployeeHomeComponent } from './employee-home/employee-home.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    EmployeeHomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(approutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
