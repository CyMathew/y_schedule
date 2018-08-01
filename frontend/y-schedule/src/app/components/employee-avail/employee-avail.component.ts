import { DateTimeService } from './../../services/date-time.service';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-employee-avail',
  templateUrl: './employee-avail.component.html',
  styleUrls: ['./employee-avail.component.css']
})
export class EmployeeAvailComponent implements OnInit 
{
  storeHours = {
    startHour: 6,
    endHour: 21
  }
  
  events = [];

  inputSlot = {
    day: "",
    startTime: "",
    endTime: ""
  };

  payload = {};

  days = [
    {value: 'sunday', viewValue: 'Sunday'},
    {value: 'monday', viewValue: 'Monday'},
    {value: 'tuesday', viewValue: 'Tuesday'},
    {value: 'wednesday', viewValue: 'Wednesday'},
    {value: 'thursday', viewValue: 'Thursday'},
    {value: 'friday', viewValue: 'Friday'},
    {value: 'saturday', viewValue: 'Saturday'}
  ];

  inputNotValid = {
    dayInvalid: false,
    timeInvalid: false,
    startTimeEmpty: false,
    endTimeEmpty: false,
    timeNotInStoreHours: false,
  };


  constructor(private authService: AuthService, private dateTimeService: DateTimeService) { }

  ngOnInit() 
  {
    this.authService.send("/employee.do", {action: "getAvailDetails"})
    .subscribe(
      data => { this.setEvents(data)},
      Error => { console.log('Error'); this.setEvents(undefined)}
    );
  }

  setEvents(data)
  {
    console.log(data);
    if(typeof data == "undefined")
    {
      console.log('Creating new array');
      this.events = [];
    }
    else
    {
      console.log('FOund array');
      this.events = data["weekDetails"];
    }
    
  }

  editAvailability()
  {
    this.resetWarnings();

    let startTime = this.dateTimeService.getTimeAsInt(this.inputSlot.startTime);
    let endTime = this.dateTimeService.getTimeAsInt(this.inputSlot.endTime);


    if (this.inputSlot.day == "")
    {
      this.inputNotValid.dayInvalid = true;
    }
    else if (isNaN(startTime))
    {
      this.inputNotValid.startTimeEmpty = true;
    }
    else if (isNaN(endTime))
    {
      this.inputNotValid.endTimeEmpty = true;
    }
    else if(startTime >= endTime)
    {
      this.inputNotValid.timeInvalid = true;
    }
    else if(startTime < this.storeHours.startHour || endTime > this.storeHours.endHour)
    {
      this.inputNotValid.timeNotInStoreHours = true;
    }
    else
    {
      //ACTUALLY SET THE DATA HERE
      this.setDaySlot();
    }


  }

  resetWarnings()
  {
    for(let item in this.inputNotValid)
    {
      this.inputNotValid[item] = false;
    }
  }

  setDaySlot()
  {
    let found = false;

    this.payload = {
      day: this.inputSlot.day,
      startTime: this.dateTimeService.getTimeAsInt(this.inputSlot.startTime),
      endTime: this.dateTimeService.getTimeAsInt(this.inputSlot.endTime)
    }

    for(let item in this.events)
    {
      if(this.isOverlapping(item))
      {
        // console.log('FOUND');
        this.events[item] = Object.assign({}, this.payload);
        found = true;
      }
    }
    
    if(!found)
    {
      // console.log('Day not found in events');
      let tempSlot = Object.assign({}, this.payload);
      this.events = [].concat(this.events, tempSlot);
    }
    // console.log(this.events); 

  }

  isOverlapping(item): boolean
  {
    let tempDay = this.events[item]["day"];
    let sTimePrevious = this.events[item]["startTime"];
    let eTimePrevious = this.events[item]["endTime"];

    let sTimeNow = this.payload["startTime"];
    let eTimeNow = this.payload["endTime"];

    if(tempDay == this.payload["day"]) 
    {
      if(((sTimeNow >= sTimePrevious) && (sTimeNow <= eTimePrevious)) ||
       ((eTimeNow >= sTimePrevious) && (eTimeNow <= eTimePrevious))
      )
      {
        return true;
      }
    }
    return false;
  }

  requestApproval()
  {
    if(this.events != null)
    {
      let payload = {
        action: 'editAvailDetails',
        availDetails: this.events
      };

      this.authService.send("/employee.do", payload)
        .subscribe(
          data => this.sendStatus(data),
          Error => this.authService.checkSession(Error)
        );
    }
    else
    {
      console.log('Events object is empty. Approval not sent');
    }
  }

  sendStatus(data)
  {
    console.log('Status: ', data);
  }

}