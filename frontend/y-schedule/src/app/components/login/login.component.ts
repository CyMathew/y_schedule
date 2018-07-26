import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private http: HttpClient) { }

  username: string;
  password: string;

  ngOnInit() {

  }

  public tryLogin() {

    if (this.username != null && this.password != null) {
      let param = {
        action: "login",
        username: this.username,
        password: this.password
      };
      let url: string = "http://localhost:8085/y_schedule/login.do";

      this.http.post(url, param).subscribe(data => this.doLogin(data), Error => this.doLogin(Error));
    }

  }

  public doLogin(data: Object) {
    if (data["result"] == "success") {
      switch (data["role"]) {
        case "employee":
          this.router.navigate(["home"]);
          break;
        case "manager":
          this.router.navigate(["manage"]);
          break;
        case "coordinator":
          this.router.navigate(["coordinate"]);
          break;
      }
    } else {
      //TODO fail message
    }
  }

}
