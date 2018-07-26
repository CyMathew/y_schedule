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
    let url: string = "http://18.191.179.128:8085/y_schedule/login";
    this.http.get<Object>(url).subscribe(data => this.doLogin(data), Error => this.router.navigate(["home"]));

  }

  public doLogin(data: Object) {
    this.router.navigate(["home"])
  }

}
