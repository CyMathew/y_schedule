import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { GetUrlService } from '../../services/get-url.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private http: HttpClient, private url: GetUrlService) { }

  username: string;
  password: string;
  
  ngOnInit() {

  }

  ngAfterViewInit(){
    console.log(this.url.get());
  }

  public tryLogin() {

    if (this.username != null && this.password != null) {
      let param = {
        action: "login",
        username: this.username,
        password: this.password
      };
      
      let url: string = this.url.get() + "/y_schedule/login.do";
      this.http.post(url, param).subscribe(data => this.doLogin(data), Error => this.doLogin(Error));
    }

  }

  public doLogin(data: Object) {
    console.log(data);
    if (data["result"] == "success") {

      localStorage.setItem("")


      switch (data["role"]) {
        case "manager":
          this.router.navigate(["manage"]);
          break;
        case "coordinator":
          this.router.navigate(["coordinate"]);
          break;
        default:
          this.router.navigate(["home"]);
          break;
      }
    } else {
      //TODO fail message
    }
  }

}
