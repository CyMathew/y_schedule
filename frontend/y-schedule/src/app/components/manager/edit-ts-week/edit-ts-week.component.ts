import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { DateTimeService } from '../../../services/date-time.service';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-edit-ts-week',
  templateUrl: './edit-ts-week.component.html',
  styleUrls: ['./edit-ts-week.component.css']
})
export class EditTsWeekComponent implements OnInit {

  @Input('scheduleData') scheduleData: Object;
  @Output() daySelected = new EventEmitter<number>();
  @Output() weekSelected = new EventEmitter<number>();

  week: number;

  constructor(private dateTime: DateTimeService, private route: ActivatedRoute, private authService: AuthService) { }


  ngOnInit() {
    this.route.queryParams
      .subscribe(params => {
        this.updateWeek(parseInt(params["week"]))
      });
  }

  selectDay(day) {
    console.log("emit day: " + day);
    this.daySelected.emit(day);
  }

  getWeek(n: number){
    return this.week + n;
  }

  updateWeek(week: number){
    this.week = week;
    this.weekSelected.emit(week);
  }

}
