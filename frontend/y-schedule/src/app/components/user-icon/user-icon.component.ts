import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-user-icon',
  templateUrl: './user-icon.component.html',
  styleUrls: ['./user-icon.component.css']
})
export class UserIconComponent implements OnInit {

  constructor() { }

  @Input('small') small: boolean = false;
  @Input('userId') userId: number = -1;
  color: string = "red";
  icon: string = "perm_identity";

  ngOnInit() {
    this.setColor();
    this.setIcon();
  }

  setColor() {
    if (this.userId)
      this.color = "#78909c";

    switch (this.userId % 10) {
      case 0: this.color = "#2196f3"; break;
      case 1: this.color = "#03a9f4"; break;
      case 2: this.color = "#00bcd4"; break;
      case 3: this.color = "#ab47bc"; break;
      case 4: this.color = "#00bcd4"; break;
      case 5: this.color = "#009688"; break;
      case 6: this.color = "#1de9b6"; break;
      case 7: this.color = "#c6ff00"; break;
      case 8: this.color = "#ffc400"; break;
      case 9: this.color = "#2196f3"; break;
    }
  }

  setIcon() {
    if (this.userId)
      this.icon = "perm_identity";

    switch (this.userId % 21) {
      case 0: this.icon = "person"; break;
      case 1: this.icon = "sentiment_very_satisfied"; break;
      case 2: this.icon = "whatshot"; break;
      case 3: this.icon = "school"; break;
      case 4: this.icon = "location_city"; break;
      case 5: this.icon = "domain"; break;
      case 6: this.icon = "cake"; break;
      case 7: this.icon = "star"; break;
      case 8: this.icon = "star_border"; break;
      case 9: this.icon = "pool"; break;
      case 10: this.icon = "child_care"; break;
      case 11: this.icon = "spa"; break;
      case 12: this.icon = "beach_access"; break;
      case 13: this.icon = "ac_unit"; break;
      case 14: this.icon = "casino"; break;
      case 15: this.icon = "drive_eta"; break;
      case 16: this.icon = "directions_bike"; break;
      case 17: this.icon = "local_cafe"; break;
      case 18: this.icon = "local_florist"; break;
      case 19: this.icon = "local_library"; break;
      case 20: this.icon = "cake"; break;
    }
  }

}
