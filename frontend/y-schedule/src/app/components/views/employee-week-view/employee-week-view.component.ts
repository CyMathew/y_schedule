import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'employee-week-view',
  templateUrl: './employee-week-view.component.html',
  styleUrls: ['./employee-week-view.component.css']
})
export class EmployeeWeekViewComponent implements OnInit 
{
  @Input() storeHours;
  @Input() events;
  
  startHour: number;
  endHour: number;

  storeHoursArray = new Array<string>();

  constructor() {
    
  }
  
  ngOnInit() 
  { 
    //Generate the collection of store hours to display
    this.startHour = this.storeHours.startHour;
    this.endHour = this.storeHours.endHour;
    for(let i = this.startHour; i <= this.endHour; i++)
    {
      this.storeHoursArray.push(this.getTimeString(i));
    }

    console.log(this.startHour, this.endHour);
  }

  setHourStyle(i: number)
  {
    let tempVal = i+2;
    return {
      'grid-row': tempVal + "/" + tempVal 
    };
  }

  setViewStyle()
  {
    return {
      'display': 'grid',
      'box-shadow': '0 1px 5px rgba(0,0,0.45)',
      'padding': '5px 15px 20px 0px',
      'border-radius': '5px',
      'margin': '10px',
      'grid-template-rows': '50px repeat(' + this.storeHoursArray.length + ', 1fr)',
      'grid-template-columns': '1fr repeat(7, 1fr)'
    }
  }

  setEventStyle(event)
  {
    let dayOfWeek = event.dayOfWeek + 2;
    let startHour = event.startTime - this.startHour + 2;
    let endHour =  event.endTime - this.startHour +2;
    console.log(dayOfWeek, startHour, endHour);
    return {
      'grid-column': dayOfWeek + "/" + dayOfWeek;
      'grid-row': startHour + "/" + endHour;

    }
  }

  getTimeString(time: number): string
  {
    if(time > 12)
        return (time-12 + " PM")
    else if(time == 12)
      return (time + " PM");
    else
      return (time + ' AM');
  }
  

}
