import { Component, OnInit, Input } from '@angular/core';
import { DateTimeService } from '../../../services/date-time.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-edit-ts-graph',
  templateUrl: './edit-ts-graph.component.html',
  styleUrls: ['./edit-ts-graph.component.css']
})
export class EditTsGraphComponent implements OnInit {

  storeHours = {
    startHour: 6,
    endHour: 21
  };

  empArray = [];

  constructor(private authService: AuthService, private dateTimeService: DateTimeService) { }

  @Input('currentDay') currentDay: number;
  @Input('scheduleData') scheduleData: Object;

  ngOnInit() 
  {
    // this.authService.send("/manager.do", {action: "getAvailDetails"})
    // .subscribe(
    //   data => { this.setEvents(data)},
    //   Error => { console.log('Error'); this.setEvents(undefined)}
    // );

    this.setEmpArray();
  }

  setEmpArray()
  {
    this.empArray = [{
      empName: 'Emp 1',
      startTime: 6,
      endTime: 11 
    },
    {
      empName: 'Emp 2',
      startTime: 9,
      endTime: 15
     }, 
     {
       empName: 'Emp 3',
       startTime: 12,
       endTime: 19
     }];
  }

  currentDayNameAndDate(){
    return this.dateTimeService.getDayOfWeekNameCapitalized(this.currentDay) + " " + this.scheduleData["dates"][""+this.currentDay];
  }
  
}
