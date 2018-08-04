import { Component, OnInit, Input, SimpleChanges, Output, EventEmitter } from '@angular/core';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-conversation',
  templateUrl: './conversation.component.html',
  styleUrls: ['./conversation.component.css']
})
export class ConversationComponent implements OnInit {

  @Input('conversationId') conversationId: Object;
  @Output() convoSelected = new EventEmitter<number>();
  @Output() backToMainView = new EventEmitter<number>();

  conversation: object = { messages: null, otherName: null };
  otherId: number;
  constructor(private authService: AuthService) { }
  intervalId: any;
  ngOnInit() {
    this.fetchMessages();
    this.intervalId = setInterval(() => {
      this.fetchMessages();
    }, 10000);
  }

  ngOnDestroy(){
    clearInterval(this.intervalId);
  }

  fetchMessages() {
    let params = {
      "action": "getMessagesByID",
      "messageListId": "" + this.conversationId
    }
    this.authService.send("/message.do", params).subscribe(
      data => this.receiveMessages(data),
      Error => this.authService.checkSession(Error)
    )
  }

  receiveMessages(data) {
    console.log("receive messages", data);
    this.conversation = data;
    this.otherId = data["otherId"];
  }

  back() {
    this.backToMainView.emit(1);
    this.conversation = null;

  }

}
