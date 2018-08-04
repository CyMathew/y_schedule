import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
<<<<<<< HEAD
import { CoordinatorHomeComponent } from './components/coordinator-home/coordinator-home.component';
=======
import { CoordinatorHomeComponent } from './components/coordinator/coordinator-home/coordinator-home.component';
import { CookieService } from 'ngx-cookie-service';
import { EmployeeWeekViewComponent } from './components/views/employee-week-view/employee-week-view.component';
import { ManagerHomePanelComponent } from './components/manager/manager-home-panel/manager-home-panel.component';
>>>>>>> coordinator
import { UserSidebarComponent } from './components/user-sidebar/user-sidebar.component';
import { EditTsWeekComponent } from './components/manager/edit-ts-week/edit-ts-week.component';
import { EditTsGraphComponent } from './components/manager/edit-ts-graph/edit-ts-graph.component';
import { EditTsSidebarComponent } from './components/manager/edit-ts-sidebar/edit-ts-sidebar.component';
import { EditTimeSheetComponent } from './components/manager/edit-time-sheet/edit-time-sheet.component';
import { HighlightColumnDirective } from './directives/highlight-column.directive';
import { EmployeeWeekViewComponent } from './components/views/employee-week-view/employee-week-view.component';
import { ManagerDayViewComponent } from './components/views/manager-day-view/manager-day-view.component';
import { MessageListComponent } from './components/messages/message-list/message-list.component';
import { ConversationComponent } from './components/messages/conversation/conversation.component';
import { MessagesMainComponent } from './components/messages/messages-main/messages-main.component';
import { StartConversationComponent } from './components/messages/start-conversation/start-conversation.component';
import { MessageBoxComponent } from './components/messages/message-box/message-box.component';
import { MessageColorDirective } from './directives/message-color.directive';
<<<<<<< HEAD
import { LoadbarComponent } from './components/loadbar/loadbar.component';
import { UserIconComponent } from './components/user-icon/user-icon.component';
=======
import { CoordinatorRequestViewComponent } from './components/coordinator/coordinator-request-view/coordinator-request-view.component';
>>>>>>> coordinator

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
    UserSidebarComponent,
    EditTimeSheetComponent,
    EditTsWeekComponent,
    EditTsGraphComponent,
    EditTsSidebarComponent,
    HighlightColumnDirective,
    EmployeeAvailComponent,
    ManagerDayViewComponent,
    MessageListComponent,
    ConversationComponent,
    MessagesMainComponent,
    StartConversationComponent,
    MessageBoxComponent,
    MessageColorDirective,
<<<<<<< HEAD
    LoadbarComponent,
    UserIconComponent,
=======
    CoordinatorRequestViewComponent,
>>>>>>> coordinator
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
    ReactiveFormsModule,
  ],
  providers: [CookieService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
