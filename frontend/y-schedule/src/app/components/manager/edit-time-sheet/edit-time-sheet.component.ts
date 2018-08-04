import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { ActivatedRoute } from '@angular/router';
import { DateTimeService } from '../../../services/date-time.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-edit-time-sheet',
  templateUrl: './edit-time-sheet.component.html',
  styleUrls: ['./edit-time-sheet.component.css']
})
export class EditTimeSheetComponent implements OnInit {

  constructor(private authService: AuthService, private route: ActivatedRoute, private date: DatePipe) { }

  showWeek: boolean = true;
  week: number;
  currentDay: number;
  UTCDates: number[];
  scheduleData: Object = {employees:null, dates:null};

  selectedEmp: object;

  ngOnInit() {
    this.route.queryParams
      .subscribe(params => {
        this.week = parseInt(params["week"]);
        this.fetchSchedule();
      });
  }

  fetchSchedule(){
    console.log("fetching week: " + this.week);
    this.authService.send("/manager.do", {action: "viewSchedule", week: ""+this.week}).subscribe(
      data => this.receiveSchedule(data), 
      err => this.authService.checkSession(err)
    );
  }

  receiveSchedule(data){
    this.scheduleData = data;
    console.log(data);
  }

  onDaySelected(day){
    //console.log("parent got: " + day);
    this.currentDay = day;
    this.showWeek = false;
  }

  onWeekSelected(week){
    
    this.week = week;
  }

  onBackToWeekView(){
    this.showWeek = true;
  }

  sendScheduleEmp(event)
  {
    this.fetchSchedule();
    this.selectedEmp = event;
  }

  

}
