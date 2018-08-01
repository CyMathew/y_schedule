import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GetUrlService } from './get-url.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'

})
export class AuthService {

  constructor(private http: HttpClient, private url: GetUrlService, private cookie: CookieService, private router: Router) { }

  login(username: string, password: string) {
    let param = {
      action: "login",
      username: username,
      password: password
    };

    let url: string = this.url.get() + "/y_schedule/login.do";

    return this.http.post(url, param);

  }

  setSession(data: object, username: string) {
    this.cookie.set("userid", "" + data["userid"], 120);
    this.cookie.set("userrole", "" + data["role"], 120);
    this.cookie.set("username", "" + username, 120);
    this.cookie.set("storeid", "" + data["storeid"], 120);
  }

  send(url: string, param: object) {
    let body = {
      sessionData: {
        userid: this.cookie.get('userid'),
        username: this.cookie.get('username'),
        userrole: this.cookie.get('userrole'),
        storeid: this.cookie.get('storeid')
      },
      param: param
    }
    let sub = this.http.post(this.url.get() + url, body);
    sub.subscribe(data => {}, Error => {
      console.log(Error);
      // this.router.navigate([""]);
    })

    return sub;
  }


}
