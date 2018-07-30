import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { DateTimeService } from '../../../services/date-time.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-ts-week',
  templateUrl: './edit-ts-week.component.html',
  styleUrls: ['./edit-ts-week.component.css']
})
export class EditTsWeekComponent implements OnInit {

  @Input('scheduleData') scheduleData: Object;
  @Output() daySelected = new EventEmitter<number>();

  week: number;

  constructor(private dateTime: DateTimeService, private route: ActivatedRoute) { }


  ngOnInit() {
    this.route.queryParams
      .subscribe(params => {
        this.week = parseInt(params["week"]);
      });
  }

  selectDay(day) {
    console.log("emit day: " + day);
    this.daySelected.emit(day);


  }

  getWeek(n: number){
    return this.week + n;
  }

}
