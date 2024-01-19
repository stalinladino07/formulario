import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { of, Observable } from 'rxjs';
import { GenericService } from './generic.services';

@Injectable()
export class ConfigService {
  // tslint:disable-next-line:variable-name
  constructor(private http: HttpClient, private _genericService: GenericService) { }

  url = 'http://localhost:8080/api';

  public getUser(): Observable<any> {
    return  this._genericService.genericCallServices('get', `${this.url}/users`, null, null);
  }

  public saveUser(obj: any): Observable<any> {
    return  this._genericService.genericCallServices('post', `${this.url}/users`, obj, null);
  }

  public updateUser(obj: any): Observable<any> {
    return  this._genericService.genericCallServices('put', `${this.url}/users/${obj.iduser}`, obj, null);
  }

  public deleteUser(obj: any): Observable<any> {
    return  this._genericService.genericCallServices('delete', `${this.url}/users/${obj.iduser}`, null , null);
  }
}
