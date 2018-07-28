import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-edit-time-sheet',
  templateUrl: './edit-time-sheet.component.html',
  styleUrls: ['./edit-time-sheet.component.css']
})
export class EditTimeSheetComponent implements OnInit {

  constructor(private authService: AuthService) { }

  showWeek: boolean = true;

  ngOnInit() {
    //this.authService.send()
  }



}
