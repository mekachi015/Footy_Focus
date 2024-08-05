import { TestBed } from '@angular/core/testing';

import { PlayerWatchlistService } from './player-watchlist.service';

describe('PlayerWatchlistService', () => {
  let service: PlayerWatchlistService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlayerWatchlistService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
