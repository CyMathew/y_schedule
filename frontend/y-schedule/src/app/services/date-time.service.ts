import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DateTimeService {

  constructor() { }

  getDayOfWeekName(day: number): string {
    switch(day) {
      case 0: return "sunday";
      case 1: return "monday";
      case 2: return "tuesday";
      case 3: return "wednesday";
      case 4: return "thursday";
      case 5: return "friday";
      default: return "saturday";
      }
  }

  getDayOfWeekNameCapitalized(day: number): string {
    switch(day) {
      case 0: return "Sunday";
      case 1: return "Monday";
      case 2: return "Tuesday";
      case 3: return "Wednesday";
      case 4: return "Thursday";
      case 5: return "Friday";
      default: return "Saturday";
      }
  }

  getDayInt(dayOfWeek: string)
  {
    switch(dayOfWeek)
    {
      case "sunday": return 0;
      case "monday": return 1;
      case "tuesday": return 2;
      case "wednesday": return 3;
      case "thursday": return 4;
      // case "thrusday": return 4;
      case "friday": return 5;
      case "saturday": return 6;
    }
  }

  getTimeString(time: number): string {
    let hour = ((time + 11) % 12) + 1;
    let minute = "" + (time % 1) * 60;
    if(minute.length == 1)
      minute = "0"+minute;
    let ending = "am";
    if (time >= 12)
      ending = "pm"

    return hour + ":" + minute + " " + ending;
  }

  getTimeAsInt(time: string)
  {
    let timeArray = time.split(":");
    let hour = parseInt(timeArray[0]);
    let minute = parseInt(timeArray[1])/60;

    return hour;
  }
}
