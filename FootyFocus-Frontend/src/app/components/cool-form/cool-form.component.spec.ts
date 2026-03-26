import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoolFormComponent } from './cool-form.component';

describe('CoolFormComponent', () => {
  let component: CoolFormComponent;
  let fixture: ComponentFixture<CoolFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CoolFormComponent]
    });
    fixture = TestBed.createComponent(CoolFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
