import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-messages-main',
  templateUrl: './messages-main.component.html',
  styleUrls: ['./messages-main.component.css']
})
export class MessagesMainComponent implements OnInit {

  constructor(private authService: AuthService) { }

  showConvo: boolean = false;
  conversationId: number;
  messageData: object = {
    messageList: []
    // [
    //   { messageListID: 1, userId: 1, user: "user", message: '' },
    //   { messageListID: 2, userId: 2, user: "person", message: 'blarg blarg blarg blarg blarg blarg blarg blarg ' },
    //   { messageListID: 3, userId: 3, user: "gai", message: '' },
    //   { messageListID: 4, userId: 4, user: "other", message: '' },
    // ]
  };

  

  ngOnInit() {
    this.fetchConversation();
  }

  ngOnChange(){
    this.fetchConversation();
  }

  fetchConversation() {

    let params = {
      "action": "getMessageListsByID",
    }
    this.authService.send("/message.do", params).subscribe(
      data => this.receiveConversations(data),
      Error => this.authService.checkSession(Error)
    )
  }

  

  receiveConversations(data) {
    this.messageData = data;
  }

  onConvoSelected(convo: number) {
    this.showConvo = true;
    this.conversationId = convo;
  }

  onBack(){
    this.showConvo = false;
  }


}
