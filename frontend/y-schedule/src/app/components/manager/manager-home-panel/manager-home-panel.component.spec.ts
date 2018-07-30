import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerHomePanelComponent } from './manager-home-panel.component';

describe('ManagerHomePanelComponent', () => {
  let component: ManagerHomePanelComponent;
  let fixture: ComponentFixture<ManagerHomePanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagerHomePanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerHomePanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
