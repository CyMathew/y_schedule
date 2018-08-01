import { Component, OnInit, Input } from '@angular/core';
import { DateTimeService } from '../../../services/date-time.service';

@Component({
  selector: 'app-edit-ts-graph',
  templateUrl: './edit-ts-graph.component.html',
  styleUrls: ['./edit-ts-graph.component.css']
})
export class EditTsGraphComponent implements OnInit {

  constructor(private dateTimeService: DateTimeService) { }

  @Input('currentDay') currentDay: number;
  @Input('scheduleData') scheduleData: Object;

  ngOnInit() {
  }

  currentDayNameAndDate(){
    return this.dateTimeService.getDayOfWeekNameCapitalized(this.currentDay) + " " + this.scheduleData["dates"][""+this.currentDay];
  }
  
}
