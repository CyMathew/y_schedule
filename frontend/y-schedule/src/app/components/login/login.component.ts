import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GetUrlService } from '../../services/get-url.service';
import { AuthService } from '../../services/auth.service';
import { CookieService } from 'ngx-cookie-service';
import { LoadingService } from '../../services/loading.service';




@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService, private url: GetUrlService, private cookie: CookieService, private loadingService: LoadingService) { }

  username: string;
  password: string;

  invalidLogin: boolean = false;

  ngOnInit() { 
    this.authService.navigateToRoleHome();
  }

  ngAfterViewInit() {
    console.log(this.url.get());
  }

  public tryLogin() {
    this.invalidLogin = false;
    this.loadingService.setLoading(true);
    this.authService.login(this.username, this.password).subscribe(
      data => this.doLogin(data), Error => this.doLogin(Error)
    );

  }

  public doLogin(data: Object) {
    this.loadingService.setLoading(false);
    
    console.log(data);

    if (data["result"] == "success") {
      this.authService.setSession(data, this.username);
      this.authService.navigateToRoleHome();
    } else{
      this.invalidLogin = true;
    }
  }

  

}
