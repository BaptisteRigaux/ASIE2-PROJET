import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAddressComponentComponent } from './add-address-component.component';

describe('AddAddressComponentComponent', () => {
  let component: AddAddressComponentComponent;
  let fixture: ComponentFixture<AddAddressComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddAddressComponentComponent]
    });
    fixture = TestBed.createComponent(AddAddressComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
