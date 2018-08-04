import { Directive, ElementRef, Input, HostListener, HostBinding } from '@angular/core';

@Directive({
  selector: '[appMessageColor]'
})
export class MessageColorDirective {

  @Input() appMessageColor: boolean;
  protected _elementClass: string[] = [];

  constructor(private el: ElementRef) {
    //el.nativeElement.style.backgroundColor = 'yellow';

  }

  @HostBinding('class')
  get elementClass(): string {
    return this._elementClass.join(' ');
  }

  ngOnInit() {
    console.log("onload", this.appMessageColor);
    if (this.appMessageColor)
      this._elementClass.push("message-section message-self");
    else
      this._elementClass.push("message-section message-other");
  }
}
