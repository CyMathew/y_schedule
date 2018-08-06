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
  selectedEmp: number;
  startTime: string;
  endTime: string;
  availableTimes: object = null;

  scheduleFailure: boolean = false;
  scheduleFailMessage: string = "";

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

  onEmployeeChange(empId: number) {
    this.selectedEmp = empId;
    this.availableTimes = null;
    this.authService.send("/manager.do", {action: "EmployeeAvailabilityByDay", userId: ""+empId, day: ""+this.currentDay}).subscribe(
      data => this.receiveAvailResult(data), 
      err => this.authService.checkSession(err)
    );
  }

  trySchedule() {

    this.scheduleFailure = false;

    let params = { 
      action: "scheduleEmployee", 
      userId: "" + this.selectedEmp, 
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

  receiveScheduleResult(data) {
    if(data["result"] == "failure"){
      this.scheduleFailure = true;
      this.scheduleFailMessage = data["message"];
    }

    this.sendScheduleEmp.emit(true);
  }

  receiveAvailResult(data){
    this.availableTimes = data;
  }
}
