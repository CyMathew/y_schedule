import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-user-sidebar',
  templateUrl: './user-sidebar.component.html',
  styleUrls: ['./user-sidebar.component.css']
})
export class UserSidebarComponent implements OnInit {

  @Input('userData') userData: Object;

  constructor() { }

  ngOnInit() {
    
  }

}
