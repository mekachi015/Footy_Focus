import { TestBed } from '@angular/core/testing';

import { DetailedPlayerInfoService } from './detailed-player-info.service';

describe('DetailedPlayerInfoService', () => {
  let service: DetailedPlayerInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DetailedPlayerInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
