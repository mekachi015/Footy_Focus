import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatchdayComponent } from './matchday.component';

describe('MatchdayComponent', () => {
  let component: MatchdayComponent;
  let fixture: ComponentFixture<MatchdayComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MatchdayComponent]
    });
    fixture = TestBed.createComponent(MatchdayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
