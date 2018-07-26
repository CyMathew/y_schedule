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

  ngOnInit() {

  }

  public tryLogin() {

    let param = {
        action: 'login',
        username: 'Test',
        password: 'Test'
    }

    let url: string = "http://localhost:8081/y_schedule/login.do";
    this.http.post(url, param).subscribe(data => this.doLogin(data), Error => this.doLogin(Error));


  }

  public doLogin(data: Object) {
    //this.router.navigate(["home"])
    console.log(data);
  }

}
