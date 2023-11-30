import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdersCardsComponent } from './orders-cards.component';

describe('OrdersCardsComponent', () => {
  let component: OrdersCardsComponent;
  let fixture: ComponentFixture<OrdersCardsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OrdersCardsComponent]
    });
    fixture = TestBed.createComponent(OrdersCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
