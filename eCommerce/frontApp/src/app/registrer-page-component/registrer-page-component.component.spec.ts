import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrerPageComponentComponent } from './registrer-page-component.component';

describe('RegistrerPageComponentComponent', () => {
  let component: RegistrerPageComponentComponent;
  let fixture: ComponentFixture<RegistrerPageComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegistrerPageComponentComponent]
    });
    fixture = TestBed.createComponent(RegistrerPageComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
