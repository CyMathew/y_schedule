import { Component, OnInit, Input, Output, EventEmitter, SimpleChanges } from '@angular/core';
import { DateTimeService } from '../../../services/date-time.service';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-edit-ts-week',
  templateUrl: './edit-ts-week.component.html',
  styleUrls: ['./edit-ts-week.component.css']
})
export class EditTsWeekComponent implements OnInit {
  @Input('allowNav') allowNav: boolean = true;
  @Input('scheduleData') scheduleData: object;
  @Output() daySelected = new EventEmitter<number>();
  @Output() weekSelected = new EventEmitter<number>();

  week: number = 0;
  hoverColumn: number = -1;

  constructor(private dateTime: DateTimeService, private route: ActivatedRoute, private authService: AuthService) { }



  ngOnInit() {
    this.route.queryParams
      .subscribe(params => {
        this.updateWeek(parseInt(params["week"])),
          err => this.authService.checkSession(err)
      });
  }

  ngOnChanges(changes: SimpleChanges) {
    this.updateWeek(this.week);
  }

  selectDay(day) {
    if (this.week == 1 || this.week == 2) {
      this.daySelected.emit(day);
    }
  }

  getWeek(n: number) {
    return this.week + n;
  }

  getWeekHeader() {
    if (this.scheduleData == null)
      return "...";
    else {
      return this.scheduleData["dates"]["0"] + " - " + this.scheduleData["dates"]["6"];
    }
  }

  updateWeek(week: number) {

    this.hoverColumn = -1;
    this.week = week;
    this.weekSelected.emit(week);

  }

  onHoverColumn(col) {
    if (this.week == 1 || this.week == 2)
      this.hoverColumn = col;
  }
}
