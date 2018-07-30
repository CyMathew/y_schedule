import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  storeHours = {
    startHour: 6,
    endHour: 21
  }

  events = [
    {
      dayOfWeek: 1,
      startTime: 8,
      endTime: 11
    },
    {
      dayOfWeek: 3,
      startTime: 10,
      endTime: 12
    },
    {
      dayOfWeek: 3,
      startTime: 15,
      endTime: 19
    },
    
  ]

}
