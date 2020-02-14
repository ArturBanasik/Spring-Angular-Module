import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ElectricsComponent} from './electrics.component';

describe('ElectricsComponent', () => {
  let component: ElectricsComponent;
  let fixture: ComponentFixture<ElectricsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ElectricsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ElectricsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
