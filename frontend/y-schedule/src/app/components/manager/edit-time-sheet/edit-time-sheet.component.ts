import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-edit-time-sheet',
  templateUrl: './edit-time-sheet.component.html',
  styleUrls: ['./edit-time-sheet.component.css']
})
export class EditTimeSheetComponent implements OnInit {

  constructor(private authService: AuthService) { }

  showWeek: boolean = true;
  currentDay: number;
  scheduleData: Object = {employees:null};

  ngOnInit() {
    this.authService.send("/y_schedule/manager.do", {action: "viewSchedule"}).subscribe(
      data => this.receiveSchedule(data)
    );

  }

  receiveSchedule(data){
    this.scheduleData = data;
    console.log(data);
  }

  onDaySelected(day){
    console.log("parent got: " + day);
    this.currentDay = day;
    this.showWeek = false;
  }

}
