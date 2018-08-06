import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { DateTimeService } from '../../../services/date-time.service';

@Component({
  selector: 'app-edit-ts-sidebar',
  templateUrl: './edit-ts-sidebar.component.html',
  styleUrls: ['./edit-ts-sidebar.component.css']
})
export class EditTsSidebarComponent implements OnInit {

  @Output() backToWeekView = new EventEmitter<number>();
  @Output() sendScheduleEmp = new EventEmitter<boolean>(); 
  @Input('currentDay') currentDay: number;
  @Input('scheduleData') scheduleData: object;
  availEmployees = [];
  selectedEmpID: number;
  selectedEmpName: string;
  startTime: string;
  endTime: string;
  availableTimes: object = null;

  scheduleFailure: boolean = false;
  scheduleFailMessage: string = "";

  inputNotValid = {
    empNotSelected: false,
    startTimeInvalid: false,
    endTimeInvalid: false,
    timesInvalid: false
  }

  constructor(private authService: AuthService, private dtService: DateTimeService) { }

  ngOnInit() {
    this.authService.send("/manager.do", { action: "getAvailEmployees", day: this.dtService.getDayOfWeekName(this.currentDay) }).subscribe(
      data => this.receiveAvailableEmployees(data)
    );
  }

  back() {
    this.backToWeekView.emit(0);
  }

  receiveAvailableEmployees(data: object) {
    this.availEmployees = data["availEmployees"];
  }

  onEmployeeChange(employee: object) {
    this.selectedEmpID = employee["userId"];
    this.selectedEmpName = employee["name"];
    this.availableTimes = null;
    this.authService.send("/manager.do", {action: "EmployeeAvailabilityByDay", userId: ""+employee["userId"], day: ""+this.currentDay}).subscribe(
      data => this.receiveAvailResult(data), 
      err => this.authService.checkSession(err)
    );
  }
  

  scheduleValid():boolean
  {
    if(this.isInputValid())
    {
      for(let employee of this.scheduleData["employees"])
      {
        if(this.selectedEmpName == employee.name)
        {
          for(let shift in employee["shifts"])
          {
            if(this.isOverlapping(employee["shifts"][shift]))
              return false;
          }
        }
      }
    }
    return true;
  }

  trySchedule() {
    if(this.scheduleValid())
    {
      this.scheduleFailure = false;
      
        let params = { 
        action: "scheduleEmployee", 
        userId: "" + this.selectedEmpID, 
        date: this.scheduleData["dates"]["" + this.currentDay], 
        startTime: this.startTime, 
        endTime: this.endTime 
      };
    
      
    
      this.authService.send("/manager.do", params)
      .subscribe(
        data => this.receiveScheduleResult(data), 
        err => this.authService.checkSession(err)
      );
    }

  }

  isInputValid(): boolean
  {
    this.resetWarnings();

    if(typeof this.selectedEmpID == "undefined")
    {
      this.inputNotValid.empNotSelected = true;
      return false;
    }
    else if (typeof this.startTime == "undefined")
    {
      this.inputNotValid.startTimeInvalid = true;
      return false;
    }
    else if (typeof this.endTime == "undefined")
    {
      this.inputNotValid.endTimeInvalid = true;
      return false;
    }
    else if (this.dtService.getTimeAsInt(this.startTime) >= this.dtService.getTimeAsInt(this.endTime))
    {
      this.inputNotValid.timesInvalid = true;
      return false;
    }

    return true;
  }

  isOverlapping(currentShift): boolean
  {
    let tempDay = currentShift["day"];
    let sTimePrevious = currentShift["start"];
    let eTimePrevious = currentShift["end"];
    let sTimeNow = this.dtService.getTimeAsInt(this.startTime)
    let eTimeNow = this.dtService.getTimeAsInt(this.endTime);
    
    if(tempDay == this.currentDay) 
    {
      if(((sTimeNow >= sTimePrevious) && (sTimeNow <= eTimePrevious)) ||
      ((eTimeNow >= sTimePrevious) && (eTimeNow <= eTimePrevious)))
      {
        return true;
      }
    }
    return false;
  }

  resetWarnings()
  {
    for(let item in this.inputNotValid)
    {
      this.inputNotValid[item] = false;
    }
  }

  receiveScheduleResult(data) {
    if(data["result"] == "failure"){
      this.scheduleFailure = true;
      this.scheduleFailMessage = data["message"];
    }
    else
    {
      this.sendScheduleEmp.emit(true);
    }

  }

  receiveAvailResult(data){
    this.availableTimes = data;
  }
}
