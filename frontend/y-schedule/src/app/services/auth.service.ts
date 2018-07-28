import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GetUrlService } from './get-url.service';
import { CookieService } from 'ngx-cookie-service';


@Injectable({
  providedIn: 'root'

})
export class AuthService {

  constructor(private http: HttpClient, private url: GetUrlService, private cookie: CookieService) { }

  login(username: string, password: string) {
    let param = {
      action: "login",
      username: username,
      password: password
    };

    let url: string = this.url.get() + "/y_schedule/login.do";

    return this.http.post(url, param);

  }

  setSession(userid: number){
    this.cookie.set("userid", "" + userid, 120);
  }

  send(url: string, param: object){
    let body = {
      userid: this.cookie.get('userid'),
      param: param
    }
    return this.http.post(this.url.get() + url, body);
  }


}
