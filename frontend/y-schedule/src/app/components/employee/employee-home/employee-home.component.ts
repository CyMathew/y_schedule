import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-employee-home',
  templateUrl: './employee-home.component.html',
  styleUrls: ['./employee-home.component.css']
})
export class EmployeeHomeComponent implements OnInit 
{
    storeHours = {
      startHour: 6,
      endHour: 21
    }

    displayDates = {
      beginDate: "",
      endDate: ""
    };
    
    weekNeeded = 0;
    events: any;
    userData: object;


  constructor(private authService: AuthService) { }

  ngOnInit() 
  {
    this.requestWeek(+this.weekNeeded);
    this.requestUserData();
  }

  requestWeek(weekOffset: number)
  {
    let payload = {
      action: "getWeekSchedule",
      weekOffset: weekOffset
    };

    this.authService.send("/employee.do", payload).subscribe(
      data => { this.setEvents(data)},
      Error => console.log('Error')
    );
  }

  setEvents(data)
  {
    this.displayDates.beginDate = data["dates"][0];
    this.displayDates.endDate = data["dates"][6];
    this.events = data["employee"]["shifts"];
  }

  getPrevious()
  {
    this.requestWeek(--this.weekNeeded);
  }

  getNext()
  {
    this.requestWeek(++this.weekNeeded);
  }

  requestUserData(){
    let param = { action: "viewHome" };
    this.authService.send("/usermain.do", param).subscribe(
      data => this.fillUserData(data), 
      err => this.authService.checkSession(err)
    );
  }

  private fillUserData(data) {
    this.userData = data;
  }

}
