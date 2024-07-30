import { TestBed } from '@angular/core/testing';

import { LeagueStandingsService } from './league-standings.service';

describe('LeagueStandingsService', () => {
  let service: LeagueStandingsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LeagueStandingsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
