import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeAvailComponent } from './employee-avail.component';

describe('EmployeeAvailComponent', () => {
  let component: EmployeeAvailComponent;
  let fixture: ComponentFixture<EmployeeAvailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeAvailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeAvailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
