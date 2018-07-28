import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTsGraphComponent } from './edit-ts-graph.component';

describe('EditTsGraphComponent', () => {
  let component: EditTsGraphComponent;
  let fixture: ComponentFixture<EditTsGraphComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditTsGraphComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditTsGraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
