import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayerWatchlistComponent } from './player-watchlist.component';

describe('PlayerWatchlistComponent', () => {
  let component: PlayerWatchlistComponent;
  let fixture: ComponentFixture<PlayerWatchlistComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PlayerWatchlistComponent]
    });
    fixture = TestBed.createComponent(PlayerWatchlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
