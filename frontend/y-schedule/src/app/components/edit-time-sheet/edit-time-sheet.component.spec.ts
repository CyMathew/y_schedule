import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTimeSheetComponent } from './edit-time-sheet.component';

describe('EditTimeSheetComponent', () => {
  let component: EditTimeSheetComponent;
  let fixture: ComponentFixture<EditTimeSheetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditTimeSheetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditTimeSheetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
