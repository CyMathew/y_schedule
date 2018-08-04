import { Component, OnInit } from '@angular/core';
import { LoadingService } from '../../services/loading.service';

@Component({
  selector: 'app-loadbar',
  templateUrl: './loadbar.component.html',
  styleUrls: ['./loadbar.component.css']
})
export class LoadbarComponent implements OnInit {

  constructor(private loadingService: LoadingService) { }

  ngOnInit() {
  }

}
