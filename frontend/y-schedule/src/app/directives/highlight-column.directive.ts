import { Directive, Input } from '@angular/core';

@Directive({
  selector: '[appHighlightColumn]'
})
export class HighlightColumnDirective {

  constructor() { }

  @Input('column') column: string;

}
