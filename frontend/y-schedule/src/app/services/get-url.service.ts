import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GetUrlService {

  

  constructor() { }

  public get(): string{
    return environment.url;
  }
  
}
