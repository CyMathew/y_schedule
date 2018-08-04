import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-coordinator-home',
  templateUrl: './coordinator-home.component.html',
  styleUrls: ['./coordinator-home.component.css']
})
export class CoordinatorHomeComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  userData = [];

  ngOnInit() {
    let param = { action: "getAllRequests" };
    this.authService.send("/coordinator.do", param).subscribe(
      data => this.fillCoordinatorData(data), 
      err => this.authService.checkSession(err)
    );
  }

  private fillCoordinatorData(data) {
    console.log("coordinator data:", data);
    this.userData = data["requestList"];
  }

  showRequest(index)
  {
    let {empID, empName} = this.userData[index];
    this.router.navigate(["coordinate/request"], {queryParams: { empID: empID, empName: empName  }});
   
  }
}
