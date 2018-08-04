import { Directive, Input, ElementRef, SimpleChanges } from '@angular/core';

@Directive({
  selector: '[appHighlightColumn]'
})
export class HighlightColumnDirective {

  constructor(private el: ElementRef) { }

  @Input() appHighlightColumn: object = {col: 0, active: 0};

  ngOnInit() {
    this.el.nativeElement.style.cursor = "pointer";
  }

  ngOnChanges(changes: SimpleChanges){
    if(this.appHighlightColumn["col"] == this.appHighlightColumn["active"])
      this.el.nativeElement.style.backgroundColor = "#273038";
    else
      this.el.nativeElement.style.backgroundColor = "";
  }
}
