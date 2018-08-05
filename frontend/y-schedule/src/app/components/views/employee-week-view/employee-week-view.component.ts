import { DateTimeService } from './../../../services/date-time.service';
import { Component, OnInit, Input, SimpleChanges } from '@angular/core';

@Component({
  selector: 'employee-week-view',
  templateUrl: './employee-week-view.component.html',
  styleUrls: ['./employee-week-view.component.css'],
})
export class EmployeeWeekViewComponent implements OnInit 
{
  @Input() storeHours;
  @Input() eventsJSON;
  
  startHour: number;
  endHour: number;

  storeHoursArray = new Array<string>();

  eventsArray = [];

  constructor(private datetimeService: DateTimeService ) 
  {
    
  }
  
  ngOnInit() 
  { 
    //Generate the collection of store hours to display
    this.startHour = this.storeHours.startHour;
    this.endHour = this.storeHours.endHour;
    for(let i = this.startHour; i <= this.endHour; i++)
    {
      this.storeHoursArray.push(this.getHourString(i));
    }   
  }
  
  ngOnChanges(changes: SimpleChanges)
  {
    // console.log(changes);
    let eventsChanges = changes["eventsJSON"].currentValue;
    // console.log('EVENT CHANGES: ', eventsChanges);
    if(eventsChanges.length > 0)
    {
      this.eventsArray = eventsChanges;
      console.log("In WEEK VIEW", this.eventsArray);
    }
    else
    {
      this.eventsArray = [];
    }
  }

  setHourStyle(i: number)
  {
    let tempVal = i+3;
    return {
      'grid-row': tempVal + "/" + tempVal 
    };
  }

  setViewStyle()
  {
    return {
      'display': 'grid',
      'box-shadow': '0 1px 5px rgba(0,0,0, .45)',
      'padding': '5px 15px 20px 10px',
      'border-radius': '5px',
      'margin': '20px 0px',
      'grid-template-rows': '50px 50px repeat(' + this.storeHoursArray.length + ', 1fr)',
      'grid-template-columns': '1fr repeat(7, 1fr)',
      'grid-column-gap': '5px',
    };
  }

  setEventStyle(event)
  {

    //Start hour + 2 here is used as an offset to align it properly in CSS Grid lines
    let dayOfWeek = this.datetimeService.getDayInt(event["day"]) + 2;
    // console.log('Day: ' + event["day"] + " makes " + dayOfWeek );
    // let startHour = this.datetimeService.getTimeAsInt(event["startTime"]) - this.startHour + 2;
    let startHour = event["startTime"] - this.startHour + 3;
    // let endHour = this.datetimeService.getTimeAsInt(event["endTime"]) - this.startHour + 2;
    let endHour = event["endTime"] - this.startHour + 3;

    return {
      'grid-column': dayOfWeek + "/" + dayOfWeek,
      'grid-row': startHour + "/" + endHour,

    };
  }

  getHourString(time: number): string
  {
    if(time > 12)
        return (time-12 + " PM")
    else if(time == 12)
      return (time + " PM");
    else
      return (time + ' AM');
  }

  getTimeString(timeString: string): any
  {
    let time = this.datetimeService.getTimeAsInt(timeString);
    if(time > 12)
        return (time-12 + " PM")
    else if(time == 12)
      return (time + " PM");
    else
      return (time + ' AM');
  }  

}
