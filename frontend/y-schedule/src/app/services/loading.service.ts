import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

  constructor() { }

  isLoading: boolean = false;

  setLoading(loading){
    this.isLoading = loading;
  }

  getLoading(){
    this.isLoading = false;
  }
}
