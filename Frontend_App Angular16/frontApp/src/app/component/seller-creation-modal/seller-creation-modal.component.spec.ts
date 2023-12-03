import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerCreationModalComponent } from './seller-creation-modal.component';

describe('SellerCreationModalComponent', () => {
  let component: SellerCreationModalComponent;
  let fixture: ComponentFixture<SellerCreationModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SellerCreationModalComponent]
    });
    fixture = TestBed.createComponent(SellerCreationModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
