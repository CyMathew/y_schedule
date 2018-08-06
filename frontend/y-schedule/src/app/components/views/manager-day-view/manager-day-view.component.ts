import { DateTimeService } from './../../../services/date-time.service';
import { Component, OnInit, SimpleChanges, Input } from '@angular/core';

@Component({
  selector: 'manager-day-view',
  templateUrl: './manager-day-view.component.html',
  styleUrls: ['./manager-day-view.component.css']
})
export class ManagerDayViewComponent implements OnInit 
{
  @Input() storeHours;
  @Input() empJSON;

  startHour: number;
  endHour: number;

  storeHoursArray = new Array<string>();

  empArray = [];

  constructor(private dateTimeService: DateTimeService) { }

  ngOnInit() 
  {
    //Generate the collection of store hours to display

    this.startHour = this.storeHours.startHour;
    this.endHour = this.storeHours.endHour;
    for(let i = this.startHour; i <= this.endHour; i++)
    {
      this.storeHoursArray.push(this.dateTimeService.getHourString(i));
    }   
  }

  ngOnChanges(changes: SimpleChanges)
  {
    // console.log(changes);
    let empChanges = changes["empJSON"].currentValue;

    if(empChanges.length > 0)
    {
      this.empArray = empChanges;
      console.log("In DAY VIEW", this.empArray);
    }
    else
    {
      this.empArray = [];
    }
  }

  setViewStyle()
  {
    return {
      'display': 'grid',
      'box-shadow': '0 1px 5px rgba(0, 0, 0, .45)',
      'padding': '5px 15px 10px 10px',
      'margin': '10px',
      'border-radius': '5px',
      'grid-template-columns': '100px repeat(' + this.storeHoursArray.length + ', 1fr)',
      'grid-template-rows': '50px repeat(' + this.empArray.length + ', 1fr)',
      'grid-row-gap': '5px',
    }
  }

  setEventStyle(employee, index)
  {

    //Start hour + 2 here is used as an offset to align it properly in CSS Grid lines
    // let dayOfWeek = this.datetimeService.getDayInt(event["day"]) + 2;
    let empRow = index +2;
    let startHour = employee["startTime"] - this.storeHours.startHour + 2;
    let endHour = employee["endTime"] - this.storeHours.startHour + 2;

    return {
      'grid-column': startHour + "/" + endHour,
      'grid-row': empRow + "/" + empRow,
    };
  }
  
  formatHour(time: number): string
  {
    return this.dateTimeService.getHourString(time);
  }
 

}
