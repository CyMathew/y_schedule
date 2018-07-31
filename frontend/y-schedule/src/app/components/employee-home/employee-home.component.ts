import { AuthService } from './../../services/auth.service';
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

    events: any;

  //   events = {
  //     "weekDetails":
  //     [{
  //       day: 'sunday',
  //       startTime: '8:00',
  //       endTime: '11:00'
  //     },
  //     {
  //       day: 'wednesday',
  //       startTime: '10:00',
  //       endTime: '12:00'
  //     },
  //     {
  //       day: 'wednesday',
  //       startTime: '15:00',
  //       endTime: '19:00'
  //     },
  //     {
  //       day: 'thursday',
  //       startTime: '9:00',
  //       endTime: '16:00'
  //     }
  //   ]
  // };

  constructor(private authService: AuthService) { }

  ngOnInit() 
  {
    this.authService.send("/y_schedule/avail", {action: "getAvailDetails"}).subscribe(
      data => { this.setEvents(data)},
      Error => console.log('Error');
    );
  }

  setEvents(data)
  {
    console.log(data);
    this.events = data;
  }

  // ngAfterViewInit() 
  // {
    
  // }

}
