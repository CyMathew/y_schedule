import { Component, OnInit, Input } from '@angular/core';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-message-box',
  templateUrl: './message-box.component.html',
  styleUrls: ['./message-box.component.css']
})
export class MessageBoxComponent implements OnInit {

  @Input('otherId') otherId: number;
  @Input('conversationId') conversationId: number;
  
  messageText: string;
  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  sendMessage(){

    let params = {
      action: "createMessage",
      messageListId: "" + this.conversationId,
      message: this.messageText
    }

    console.log(params);

    this.authService.send("/message.do", params).subscribe(
      data => this.receiveMessages(data),
      Error => this.authService.checkSession(Error)
    )

    this.messageText = "";
  }

  receiveMessages(data){
    console.log("receiveMessages", data)
  }

}
