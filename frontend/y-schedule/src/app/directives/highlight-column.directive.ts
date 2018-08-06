import { Directive, Input, ElementRef, SimpleChanges } from '@angular/core';

@Directive({
  selector: '[appHighlightColumn]'
})
export class HighlightColumnDirective {

  constructor(private el: ElementRef) { }

  @Input() appHighlightColumn: object = {col: 0, active: 0};

  ngOnInit() {
    
  }

  ngOnChanges(changes: SimpleChanges){
    if(this.appHighlightColumn["col"] == this.appHighlightColumn["active"]){
      this.el.nativeElement.style.backgroundColor = "#333d46";
      this.el.nativeElement.style.cursor = "pointer";
    }else
      this.el.nativeElement.style.backgroundColor = "";
  }
}
