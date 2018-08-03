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


  constructor(private authService: AuthService) { }

  ngOnInit() 
  {
    this.authService.send("/employee.do", {action: "getAvailDetails"}).subscribe(
      data => { this.setEvents(data)},
      Error => console.log('Error')
    );
  }

  setEvents(data)
  {
    this.events = data;
  }

}
