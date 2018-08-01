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

  slot = {
    day: "",
    startTime: "",
    endTime: ""
  };

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
    this.authService.send("/y_schedule/employee.do", {action: "getAvailDetails"})
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
      this.events = data;
    }
    
  }

  editAvailability()
  {
    this.resetWarnings();

    let startTime = this.dateTimeService.getTimeAsInt(this.slot.startTime);
    let endTime = this.dateTimeService.getTimeAsInt(this.slot.endTime);


    if (this.slot.day == "")
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

    for(let item in this.events)
    {
      if(this.isOverlapping(item))
      {
        // console.log('FOUND');
        this.events[item] = Object.assign({}, this.slot);
        found = true;
      }
    }
    
    if(!found)
    {
      // console.log('Day not found in events');
      let tempSlot = Object.assign({}, this.slot);
      this.events = [].concat(this.events, tempSlot);
    }
    // console.log(this.events); 

  }

  isOverlapping(item): boolean
  {
    let tempDay = this.events[item]["day"];
    let tempStartTime = this.events[item]["startTime"];
    let tempEndTime = this.events[item]["endTime"];
    let sTimePrevious = this.dateTimeService.getTimeAsInt(tempStartTime);
    let eTimePrevious = this.dateTimeService.getTimeAsInt(tempEndTime);

    let sTimeNow = this.dateTimeService.getTimeAsInt(this.slot.startTime);
    let eTimeNow = this.dateTimeService.getTimeAsInt(this.slot.endTime);

    if(tempDay == this.slot.day) 
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

      this.authService.send("y_schedule/employee.do", payload)
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




}