import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { DateTimeService } from '../../../services/date-time.service';

@Component({
  selector: 'app-edit-ts-week',
  templateUrl: './edit-ts-week.component.html',
  styleUrls: ['./edit-ts-week.component.css']
})
export class EditTsWeekComponent implements OnInit {

  @Input('scheduleData') scheduleData: Object;
  @Output() daySelected = new EventEmitter<number>();

  constructor(private dateTime: DateTimeService) { }

  ngOnInit() {
  }

  selectDay(day){
    console.log("emit day: " + day);
    this.daySelected.emit(day);
  }

}
