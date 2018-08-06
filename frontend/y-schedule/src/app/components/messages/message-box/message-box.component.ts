import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-message-box',
  templateUrl: './message-box.component.html',
  styleUrls: ['./message-box.component.css']
})
export class MessageBoxComponent implements OnInit {

  @Input('otherId') otherId: number;
  @Input('conversationId') conversationId: number;
  @Output() messageUpdate = new EventEmitter<object>();
  
  messageText: string;
  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  sendMessage(){

    let params = {
      action: "createMessage",
      messageListId: "" + this.conversationId,
      message: this.messageText,
      otherId: "" + this.otherId
    }

    this.authService.send("/message.do", params).subscribe(
      data => this.receiveMessages(data),
      Error => this.authService.checkSession(Error)
    )

    this.messageText = "";
  }

  receiveMessages(data){
    this.messageUpdate.emit(data);
  }

}
