import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserOrdersComponentComponent } from './user-orders-component.component';

describe('UserOrdersComponentComponent', () => {
  let component: UserOrdersComponentComponent;
  let fixture: ComponentFixture<UserOrdersComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserOrdersComponentComponent]
    });
    fixture = TestBed.createComponent(UserOrdersComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
