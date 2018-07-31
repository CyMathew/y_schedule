import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeWeekViewComponent } from './employee-week-view.component';

describe('EmployeeWeekViewComponent', () => {
  let component: EmployeeWeekViewComponent;
  let fixture: ComponentFixture<EmployeeWeekViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeWeekViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeWeekViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
