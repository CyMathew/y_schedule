import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerDayViewComponent } from './manager-day-view.component';

describe('ManagerDayViewComponent', () => {
  let component: ManagerDayViewComponent;
  let fixture: ComponentFixture<ManagerDayViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagerDayViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerDayViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
