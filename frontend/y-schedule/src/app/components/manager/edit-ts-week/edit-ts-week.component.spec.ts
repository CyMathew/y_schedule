import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTsWeekComponent } from './edit-ts-week.component';

describe('EditTsWeekComponent', () => {
  let component: EditTsWeekComponent;
  let fixture: ComponentFixture<EditTsWeekComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditTsWeekComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditTsWeekComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
