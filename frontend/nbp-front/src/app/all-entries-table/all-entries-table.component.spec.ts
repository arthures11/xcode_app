import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllEntriesTableComponent } from './all-entries-table.component';

describe('AllEntriesTableComponent', () => {
  let component: AllEntriesTableComponent;
  let fixture: ComponentFixture<AllEntriesTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AllEntriesTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllEntriesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
