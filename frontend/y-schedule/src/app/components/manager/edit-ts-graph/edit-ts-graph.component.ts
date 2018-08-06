import { Component, OnInit, Input, SimpleChanges } from '@angular/core';
import { DateTimeService } from '../../../services/date-time.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-edit-ts-graph',
  templateUrl: './edit-ts-graph.component.html',
  styleUrls: ['./edit-ts-graph.component.css']
})
export class EditTsGraphComponent implements OnInit {

  @Input() receiveSelectedEmp;
  storeHours = {
    startHour: 6,
    endHour: 21
  };

  empArray = [];

  constructor(private authService: AuthService, private dateTimeService: DateTimeService) { }

  @Input('currentDay') currentDay: number;
  @Input() currentWeek: any;
  @Input('scheduleData') scheduleData: Object;

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    this.setEmpArray(this.scheduleData["employees"]);
  }

  setEmpArray(employees) {
    this.empArray = [];
    for (let employee of employees) {
      // console.log(employee);
      let empName = employee["name"];
      let shifts = employee["shifts"];
      for (let shift in shifts) {
        // console.log(shift);
        if (shifts[shift]["day"] == this.currentDay) {
          console.log("Emp: ", empName, "Shift: ", shifts[shift]);
          let eventJSON = {
            empName: empName,
            startTime: shifts[shift]["start"],
            endTime: shifts[shift]["end"]
          };

          // console.log(eventJSON);
          this.empArray = [].concat(this.empArray, eventJSON);
        }
      }
    }

  }

  currentDayNameAndDate() {
    return this.dateTimeService.getDayOfWeekNameCapitalized(this.currentDay) + " " + this.scheduleData["dates"]["" + this.currentDay];
  }

}
