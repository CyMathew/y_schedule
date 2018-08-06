import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-start-conversation',
  templateUrl: './start-conversation.component.html',
  styleUrls: ['./start-conversation.component.css']
})
export class StartConversationComponent implements OnInit {

  @Output() convoSelected = new EventEmitter<number>();
  constructor(private authService: AuthService, private fb: FormBuilder) { }
  coworkerData: object = { coworkers: null }

  coworkerForm: FormGroup;

  ngOnInit() {
    this.fetchCoworkers();
    this.coworkerForm = this.fb.group({
      coworkerId: null
    });
  }

  fetchCoworkers() {

    let params = {
      "action": "getCoworkers",
    }
    this.authService.send("/message.do", params).subscribe(
      data => {
        this.coworkerData = data
        this.coworkerForm = this.fb.group({
          coworkerId: [data[1]]
        });
      },
      Error => this.authService.checkSession(Error)
    )
  }

  tryAddConvo() {
    let params = {
      action: "createMessageList",
      otherId: "" + this.coworkerForm.value['coworkerId']
    }

    this.authService.send("/message.do", params).subscribe(
      data => this.onConvoCreated(data),
      Error => this.authService.checkSession(Error)
    )
  }

  onConvoCreated(data) {
    this.convoSelected.emit(data["messageListId"]);
  }

}
