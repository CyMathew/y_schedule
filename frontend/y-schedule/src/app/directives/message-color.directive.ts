import { Directive, ElementRef, Input } from '@angular/core';

@Directive({
  selector: '[appMessageColor]'
})
export class MessageColorDirective {

  @Input('owner') owner: boolean;

  constructor(private el: ElementRef) { 
    if(this.owner)
    el.nativeElement.class = ""

  }

}
