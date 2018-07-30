import { Component, OnInit, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-edit-ts-sidebar',
  templateUrl: './edit-ts-sidebar.component.html',
  styleUrls: ['./edit-ts-sidebar.component.css']
})
export class EditTsSidebarComponent implements OnInit {

  @Output() backToWeekView = new EventEmitter<number>();

  constructor() { }

  ngOnInit() {
  }

  back(){
    this.backToWeekView.emit(0);
  }

  
}
