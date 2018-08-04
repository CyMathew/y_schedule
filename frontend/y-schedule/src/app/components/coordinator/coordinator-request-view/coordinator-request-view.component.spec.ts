import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoordinatorRequestViewComponent } from './coordinator-request-view.component';

describe('CoordinatorRequestViewComponent', () => {
  let component: CoordinatorRequestViewComponent;
  let fixture: ComponentFixture<CoordinatorRequestViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoordinatorRequestViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoordinatorRequestViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
