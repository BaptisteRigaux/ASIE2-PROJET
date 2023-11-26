import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalInfosComponentComponent } from './personal-infos-component.component';

describe('PersonalInfosComponentComponent', () => {
  let component: PersonalInfosComponentComponent;
  let fixture: ComponentFixture<PersonalInfosComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PersonalInfosComponentComponent]
    });
    fixture = TestBed.createComponent(PersonalInfosComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
