import { Component, OnInit, Input } from '@angular/core';
import { GetUrlService } from '../../../services/get-url.service';
import { AuthService } from '../../../services/auth.service';


@Component({
  selector: 'app-manager-home',
  templateUrl: './manager-home.component.html',
  styleUrls: ['./manager-home.component.css']
})
export class ManagerHomeComponent implements OnInit {

  constructor(private url: GetUrlService, private authService: AuthService) { }

  loaded: number = 0;
  scheduleData: object = null;
  userData: object;

  ngOnInit() {
    this.requestUserData();
    this.fetchSchedule();
  }

  fetchSchedule(){
    this.authService.send("/manager.do", {action: "viewSchedule", week: "0"}).subscribe(
      data => this.receiveSchedule(data), 
      err => this.authService.checkSession(err)
    );
  }

  receiveSchedule(data){
    this.loaded++;
    this.scheduleData = data;
    console.log("received???", data);
  }

  requestUserData(){
    let param = { action: "viewHome" };
    this.authService.send("/usermain.do", param).subscribe(
      data => this.fillUserData(data), 
      err => this.authService.checkSession(err)
    );
  }

  private fillUserData(data) {
    this.userData = data;
  }

}
