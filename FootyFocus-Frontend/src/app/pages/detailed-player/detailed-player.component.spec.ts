import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailedPlayerComponent } from './detailed-player.component';

describe('DetailedPlayerComponent', () => {
  let component: DetailedPlayerComponent;
  let fixture: ComponentFixture<DetailedPlayerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetailedPlayerComponent]
    });
    fixture = TestBed.createComponent(DetailedPlayerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
