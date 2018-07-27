import { Component, OnInit } from '@angular/core';
import { GetUrlService } from '../../services/get-url.service';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-manager-home',
  templateUrl: './manager-home.component.html',
  styleUrls: ['./manager-home.component.css']
})
export class ManagerHomeComponent implements OnInit {

  constructor(private url: GetUrlService, private http: HttpClient) { }

  ngOnInit() {
    let param = {action: "viewHome"};

    let url: string = this.url.get() + "/y_schedule/manager.do";
    this.http.post(url, param).subscribe(data => this.fillManagerData(data));
  }

  ngAfterViewInit(){
    
  }

  private fillManagerData(data){
    console.log(data);
  }

}
