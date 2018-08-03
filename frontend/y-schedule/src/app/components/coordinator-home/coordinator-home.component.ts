import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-coordinator-home',
  templateUrl: './coordinator-home.component.html',
  styleUrls: ['./coordinator-home.component.css']
})
export class CoordinatorHomeComponent implements OnInit {

  constructor(private authService: AuthService) { }

  userData: Object;

  ngOnInit() {
    let param = { action: "getAllRequests" };
    this.authService.send("/coordinator.do", param).subscribe(
      data => this.fillCoordinatorData(data), 
      err => this.authService.checkSession(err)
    );
  }

  private fillCoordinatorData(data) {
    console.log("coordinator data:", data);
    this.userData = data;
  }
}
