import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

const httpOptions = { 
  header: new HttpHeaders({
    'Content-Type': 'application/json',
  }) 
};

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})

export class RegistrationComponent implements OnInit 
{
  user = {
    first_name: "",
    last_name: "",
    username: "",
    password: "",
    confirmPassword: ""
  };

  inputNotValid = {
    first_name: false,
    last_name: false,
    username: false,
    password: false,
    confirmPassword: false
  }
  
  userTaken = false;
  passNoMatch = false;
  
  // url: string = "http://18.191.179.128:8085/y_schedule/register";
  url = "http://localhost:8085/y_schedule/register";

  constructor(private router: Router, private http: HttpClient) {
    // this.first_name="Hello";
   }

  ngOnInit() {
  }

  public register() 
  {
    this.reset();

    if(this.checkIfEmpty(this.user))
    {
      
    }
    else if(this.user.password != this.user.confirmPassword)
    {
      console.log('Passwords do not match');
      this.passNoMatch = true;
    }
    else
    {
      this.http.post<Object>(this.url, this.user)
        .subscribe(
          (data) => this.afterRegister(data),
          err => {
            console.log("Error occurred");
            // this.router.navigate(["home"])
          }
        );
    }
  }

  private checkIfEmpty(user: Object)
  {
    for(let item in user)
    {
      if(user[item] == "")
      {
        this.inputNotValid[item] = true;
        return true;      
      }
    }

    return false;
  }

  public afterRegister(data: Object) {
    if(data["result"] == "success")
    {
      console.log('Result was success');
      this.router.navigate([""])
    }
    else
    {
      console.log('Username is taken');
      this.userTaken = true;
    }
    
  }

  private reset()
  {
    this.userTaken = false;
    this.passNoMatch = false;

    for(let item in this.inputNotValid)
    {
      this.inputNotValid[item] = false;
    }
  }

}
