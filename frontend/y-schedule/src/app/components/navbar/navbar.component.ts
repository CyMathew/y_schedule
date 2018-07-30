import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private cookie: CookieService, private route: ActivatedRoute) { }

  role: String;

  ngOnInit() {  

    this.route.fragment
      .subscribe(data => {
        console.log("update nav")
        this.role = this.cookie.get('userrole');
      });
  }


}
