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
      case 4: return "thrusday";
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
      case 4: return "Thrusday";
      case 5: return "Friday";
      default: return "Saturday";
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
}
