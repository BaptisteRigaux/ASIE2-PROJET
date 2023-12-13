import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommandpageComponent } from './commandpage.component';

describe('CommandpageComponent', () => {
  let component: CommandpageComponent;
  let fixture: ComponentFixture<CommandpageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CommandpageComponent]
    });
    fixture = TestBed.createComponent(CommandpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
