import { Component, OnInit } from '@angular/core';
import { GetUrlService } from '../../services/get-url.service';
import { AuthService } from '../../services/auth.service';


@Component({
  selector: 'app-manager-home',
  templateUrl: './manager-home.component.html',
  styleUrls: ['./manager-home.component.css']
})
export class ManagerHomeComponent implements OnInit {

  constructor(private url: GetUrlService, private authService: AuthService) { }

  ngOnInit() {
    let param = {action: "viewHome"};
    this.authService.send("/y_schedule/manager.do", param).subscribe(data => this.fillManagerData(data));
  }

  ngAfterViewInit(){
    
  }

  private fillManagerData(data){
    console.log(data);
  }

}
