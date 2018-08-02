import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-message-list',
  templateUrl: './message-list.component.html',
  styleUrls: ['./message-list.component.css']
})
export class MessageListComponent implements OnInit {

  @Input('messageData') messageData: Object;
  @Output() convoSelected = new EventEmitter<number>();

  constructor() { }

  ngOnInit() {
  }

  selectConversation(convo: number){
    console.log("emit convoSelected", convo)
    this.convoSelected.emit(convo);
  }

}
