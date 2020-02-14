import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ListElectricsComponent} from './list-electrics.component';

describe('ListElectricsComponent', () => {
  let component: ListElectricsComponent;
  let fixture: ComponentFixture<ListElectricsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ListElectricsComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListElectricsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
