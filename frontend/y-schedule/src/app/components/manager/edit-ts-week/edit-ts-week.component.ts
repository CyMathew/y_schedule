import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-edit-ts-week',
  templateUrl: './edit-ts-week.component.html',
  styleUrls: ['./edit-ts-week.component.css']
})
export class EditTsWeekComponent implements OnInit {

  @Input('scheduleData') scheduleData: Object;
  @Output() daySelected = new EventEmitter<number>();

  constructor() { }

  ngOnInit() {
  }

  selectDay(day){
    console.log("emit day: " + day);
    this.daySelected.emit(day);
  }

}
