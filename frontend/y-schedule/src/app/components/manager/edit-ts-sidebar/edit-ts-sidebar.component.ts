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
  @Input('currentDay') currentDay: number;
  @Input('scheduleData') scheduleData: object;
  availEmployees: object;
  selectedEmp: number;
  startTime: string;
  endTime: string;

  constructor(private authService: AuthService, private dtService: DateTimeService) { }

  ngOnInit() {
    this.authService.send("/y_schedule/manager.do", { action: "getAvailEmployees", day: this.dtService.getDayOfWeekName(this.currentDay) }).subscribe(
      data => this.receiveAvailableEmployees(data)
    );
  }

  back() {
    this.backToWeekView.emit(0);
  }

  receiveAvailableEmployees(data: object) {
    console.log("availEmployees", data);
    this.availEmployees = data["availEmployees"];
  }

  onEmployeeChange(empId: number) {
    this.selectedEmp = empId;
  }

  trySchedule() {

    let params = { action: "scheduleEmployee", userId: "" + this.selectedEmp, date: this.scheduleData["dates"]["" + this.currentDay], startTime: this.startTime, endTime: this.endTime }
    console.log("try Schedule:", this.selectedEmp, this.startTime, this.endTime, this.scheduleData["dates"]["" + this.currentDay]);

    this.authService.send("/y_schedule/manager.do", params).subscribe(
      data => this.receiveScheduleResult(data)
    );
  }

  receiveScheduleResult(data) {
    console.log("schedule result", data);
  }
}
