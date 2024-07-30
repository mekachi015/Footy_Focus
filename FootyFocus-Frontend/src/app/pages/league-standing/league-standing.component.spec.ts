import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeagueStandingComponent } from './league-standing.component';

describe('LeagueStandingComponent', () => {
  let component: LeagueStandingComponent;
  let fixture: ComponentFixture<LeagueStandingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LeagueStandingComponent]
    });
    fixture = TestBed.createComponent(LeagueStandingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
