import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTsSidebarComponent } from './edit-ts-sidebar.component';

describe('EditTsSidebarComponent', () => {
  let component: EditTsSidebarComponent;
  let fixture: ComponentFixture<EditTsSidebarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditTsSidebarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditTsSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
