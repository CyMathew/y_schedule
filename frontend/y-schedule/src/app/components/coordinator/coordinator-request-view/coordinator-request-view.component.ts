import { AuthService } from 'src/app/services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-coordinator-request-view',
  templateUrl: './coordinator-request-view.component.html',
  styleUrls: ['./coordinator-request-view.component.css']
})
export class CoordinatorRequestViewComponent implements OnInit 
{

  storeHours = {
    startHour: 6,
    endHour: 21
  };

  events: object;

  empID: string;
  empName: string;

  constructor(private route: ActivatedRoute, private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.route.queryParams
      .subscribe(params => {
        this.empID = params["empID"];
        this.empName = params["empName"];
        this.getRequest();
      });
  }

  getRequest()
  {
    console.log(this.empID, this.empName);
    let payload = {
      action: "getEmpAvail",
      empID: this.empID,
      empName: this.empName
    };

    this.authService.send("/coordinator.do", payload)
      .subscribe(
        data => this.events = data["weekDetails"],
        err => console.log(err)
      );
  }

  sendRequestStatus(requestStatus)
  {

    let payload = {
      action: "requestAction",
      empID: this.empID,
      requestStatus: requestStatus
    };

    this.authService.send("/coordinator.do", payload)
      .subscribe(
        data => { 
          console.log(data);
          if(data["result"] == "success")
            this.router.navigate(["coordinate"]);
        },
        err => console.log(err)
      );
  }
}
