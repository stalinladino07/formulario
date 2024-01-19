import { CanActivate, Router } from '@angular/router';
import { LoginService } from '../interface/login.services';
import { Injectable } from '@angular/core';

@Injectable()
export class LoginGuardGuard implements CanActivate {

  constructor(
    public _loginService: LoginService,
    public router: Router
  ) {}

  canActivate() {
   if ( this._loginService.isActive()) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    } 
  }
}
