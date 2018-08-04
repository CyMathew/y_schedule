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
    let param = { action: "viewHome" };
    this.authService.send("/manager.do", param).subscribe(
      data => this.fillManagerData(data), 
      err => this.authService.checkSession(err)
    );
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

  private fillManagerData(data) {
    this.loaded++;
    console.log("manager data:");
    console.log(data);
    this.userData = data;
  }

}
