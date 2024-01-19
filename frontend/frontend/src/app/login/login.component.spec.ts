import { TestBed, async, ComponentFixture } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { CUSTOM_ELEMENTS_SCHEMA, DebugElement } from '@angular/core';
import { LoginComponent } from './login.component';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../services/interface/k2a.services.login';
import { HttpClientModule } from '@angular/common/http';
import { GenericService } from '../services/interface/generic.services';
import { By } from '@angular/platform-browser';


describe('LoginComponent', () => {

  let comp: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let de: DebugElement;
  let el: HTMLElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        FormsModule,
        HttpClientModule
      ],
      declarations: [
        LoginComponent
      ],
      providers: [
         LoginService,
         GenericService
      ],
      schemas: [
        CUSTOM_ELEMENTS_SCHEMA
    ],
    }).compileComponents().then(() => {
       fixture = TestBed.createComponent(LoginComponent);
       comp = fixture.componentInstance;
       de = fixture.debugElement.query(By.css('form'));
       el = de.nativeElement;
    });
  }));

  it(`Init username`, async(() => {
    expect(comp.user.username).toEqual(null);
  }));

});
