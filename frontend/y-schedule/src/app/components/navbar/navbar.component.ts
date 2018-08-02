import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private cookie: CookieService, private route: ActivatedRoute, private authService: AuthService) { }

  role: String;

  ngOnInit() {
    this.resetCookie();
  }

  ngDoCheck(){
    this.role = this.cookie.get('userrole');
  }

  resetCookie()
  {
    this.role = this.cookie.get('userrole');
    console.log('role is ', typeof this.role);
  }

}
