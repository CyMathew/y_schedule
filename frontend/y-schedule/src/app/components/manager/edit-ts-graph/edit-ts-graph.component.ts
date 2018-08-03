import { Component, OnInit, Input, SimpleChanges } from '@angular/core';
import { DateTimeService } from '../../../services/date-time.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-edit-ts-graph',
  templateUrl: './edit-ts-graph.component.html',
  styleUrls: ['./edit-ts-graph.component.css']
})
export class EditTsGraphComponent implements OnInit {

  @Input() receiveSelectedEmp;
  storeHours = {
    startHour: 6,
    endHour: 21
  };

  empArray = [];

  constructor(private authService: AuthService, private dateTimeService: DateTimeService) { }

  @Input('currentDay') currentDay: number;
  @Input() currentWeek: any;
  @Input('scheduleData') scheduleData: Object;

  ngOnInit() 
  {
    

    // this.setEmpArray();
    // console.log('Current Day: ', this.currentDay, 'Current Week: ', this.currentWeek);
    this.refreshScheduledEmps();
  }
  
  ngOnChanges(changes: SimpleChanges)
  {
    let emp = changes["receiveSelectedEmp"].currentValue
    if(typeof emp != "undefined")
    {
      this.refreshScheduledEmps();
    }
  }
  
  setEmpArray(employees)
  {
    // this.empArray = [{
      //   empName: 'Emp 1',
      //   startTime: 6,
      //   endTime: 11 
      // },
      // {
        //   empName: 'Emp 2',
        //   startTime: 9,
        //   endTime: 15
        //  }, 
        //  {
          //    empName: 'Emp 3',
          //    startTime: 12,
          //    endTime: 19
          //  }];

          console.log('Going through employee array');
          
          for(let employee of employees)
          {
            console.log(employee);
            let empName = employee["name"];
            let shifts = employee["shifts"];
            for(let shift in shifts)
            {
              // console.log(shift);
              // console.log("Emp: ", empName, "Shift: ", shifts[shift]);
              if(shifts[shift]["day"] == this.currentDay)
              {
                let eventJSON = {
                  empName: empName,
                  startTime: shifts[shift]["start"],
                  endTime: shifts[shift]["end"]
                };
                
                // console.log(eventJSON);
                this.empArray = [].concat(this.empArray, eventJSON);
              }
            }
    }

  }
  
  //emp["shifts"][i]["start"]
  //emp["shifts"][i]["end"]
  
  refreshScheduledEmps()
  {
    // this.authService.send("/manager.do", {action: "getAvailDetails"})
    // .subscribe(
    //   data => { this.setEmpArray(data)},
    //   Error => { console.log('Error'); this.setEmpArray(undefined)}
    // );
    
    this.authService.send("/manager.do", {action: "viewSchedule", week: ""+this.currentWeek}).subscribe(
      data => this.setEmpArray(data["employees"]), 
      err => this.authService.checkSession(err)
    );
  }
  
  currentDayNameAndDate(){
    return this.dateTimeService.getDayOfWeekNameCapitalized(this.currentDay) + " " + this.scheduleData["dates"][""+this.currentDay];
  }
  
}
