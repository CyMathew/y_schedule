import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private cookie: CookieService) { }

  role: String;

  ngOnInit() { this.role = this.cookie.get('userrole'); }

  ngAfterViewInit() { this.role = this.cookie.get('userrole'); }


}
