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

  userData: Object;

  ngOnInit() {
    let param = { action: "viewHome" };
    this.authService.send("/y_schedule/manager.do", param).subscribe(
      data => this.fillManagerData(data), 
      err => this.authService.checkSession(err)
    );


  }

  private fillManagerData(data) {
    console.log("manager data:");
    console.log(data);
    this.userData = data;
  }

}
