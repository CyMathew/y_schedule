import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-time-sheet',
  templateUrl: './edit-time-sheet.component.html',
  styleUrls: ['./edit-time-sheet.component.css']
})
export class EditTimeSheetComponent implements OnInit {

  constructor(private authService: AuthService, private route: ActivatedRoute) { }

  showWeek: boolean = true;
  week: number;
  currentDay: number;
  scheduleData: Object = {employees:null};

  ngOnInit() {
    this.route.queryParams
      .subscribe(params => {
        this.week = parseInt(params["week"]);
        this.fetchSchedule();
      });
  }

  fetchSchedule(){
    this.authService.send("/y_schedule/manager.do", {action: "viewSchedule", week: this.week}).subscribe(
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

  onBackToWeekView(){
    this.showWeek = true;
  }

}
