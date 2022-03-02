import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JeuConcoursComponent } from './jeu-concours.component';

describe('JeuConcoursComponent', () => {
  let component: JeuConcoursComponent;
  let fixture: ComponentFixture<JeuConcoursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JeuConcoursComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JeuConcoursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
