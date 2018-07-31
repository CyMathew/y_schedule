import { Component, OnInit, Input, ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'employee-week-view',
  templateUrl: './employee-week-view.component.html',
  styleUrls: ['./employee-week-view.component.css'],
  // changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EmployeeWeekViewComponent implements OnInit 
{
  @Input() storeHours;
  @Input() eventsJSON;
  
  startHour: number;
  endHour: number;

  storeHoursArray = new Array<string>();

  eventsArray: any;

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
  
  ngAfterViewInit() 
  {
    setTimeout(()=>{
    this.eventsArray = this.eventsJSON["weekDetails"];
    console.log("In WEEK VIEW", this.eventsArray);
    }, 5000);
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
      'box-shadow': '0 1px 5px rgba(0,0,0, .45)',
      'padding': '5px 15px 20px 0px',
      'border-radius': '5px',
      'margin': '10px',
      'grid-template-rows': '50px repeat(' + this.storeHoursArray.length + ', 1fr)',
      'grid-template-columns': '1fr repeat(7, 1fr)',
      'grid-column-gap': '3px',
    };
  }

  setEventStyle(event)
  {
    // let dayOfWeek = event.dayOfWeek + 2;
    // let startHour = event.startTime - this.startHour + 2;
    // let endHour =  event.endTime - this.startHour +2;
    // console.log(dayOfWeek, startHour, endHour);

    //Start hour + 2 here is used as an offset to align it properly in CSS Grid lines
    let dayOfWeek = this.getDayInt(event["day"]) + 2;
    console.log('Day: ' + event["day"] + " makes " + dayOfWeek );
    let startHour = this.getIntFromTime(event["startTime"]) - this.startHour + 2;
    let endHour = this.getIntFromTime(event["endTime"]) - this.startHour + 2;

    return {
      'grid-column': dayOfWeek + "/" + dayOfWeek,
      'grid-row': startHour + "/" + endHour,
      'box-shadow': '0 1px 5px rgba(0,0,0,.25)',

    };
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

  getDayInt(dayOfWeek: string)
  {
    console.log(dayOfWeek);
    switch(dayOfWeek)
    {
      case "sunday": return 0;
      case "monday": return 1;
      case "tuesday": return 2;
      case "wednesday": return 3;
      // case "thursday": return 4;
      case "thrusday": return 4;
      case "friday": return 5;
      case "saturday": return 6;
    }
  }

  getIntFromTime(time: string)
  {
    let timeArray = time.split(":");
    let hour = parseInt(timeArray[0]);
    let minute = parseInt(timeArray[1])/60;

    return hour;
  }
  

}
