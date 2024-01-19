import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { of, Observable } from 'rxjs';
import { GenericService } from './generic.services';
import { URL } from '../../common/url.services';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';


@Injectable()
export class LoginService {

  constructor(public router: Router,private http: HttpClient, private _genericService: GenericService) { }

  url = URL;

  public login(obj: any): Observable<any> {
    return this._genericService.genericCallServices('post', `${this.url.CONTEXT}${this.url.CONTEXT_USER}${this.url.USERS.LOGIN}`, obj, null).pipe(map(
      (resp: any) => {
        this.guardarStorage(resp);
        return resp;
      }
    ));
   }

  guardarStorage(user:any) {

    if(user.data != null){
      localStorage.setItem('acceso', 'true');
    } else {
      localStorage.setItem('acceso', 'false');
    }
  }

  isActive() {
    return localStorage.getItem('acceso') === 'true' ? true : false;
  }

  logout() {
    localStorage.removeItem('user');
    localStorage.removeItem('acceso');
    this.router.navigate(['/login']);
  }

}
