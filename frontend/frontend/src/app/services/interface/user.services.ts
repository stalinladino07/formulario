import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { of, Observable } from 'rxjs';
import { GenericService } from './generic.services';
import { URL } from '../../common/url.services';

@Injectable()
export class UserService {

  constructor(private http: HttpClient, private _genericService: GenericService) { }

  url = URL;

  public getAllUsers(): Observable<any> {
    return  this._genericService.genericCallServices('get', `${this.url.CONTEXT}${this.url.CONTEXT_USER}${this.url.USERS.GET_LIST_ALL}`, null, null);
  }

  public getUserById(id: any): Observable<any> {
    return  this._genericService.genericCallServices('get', `${this.url.CONTEXT}${this.url.CONTEXT_USER}${this.url.USERS.GET_BY_ID}/${id}`, null, null);
  }

  public saveUsers(obj: any): Observable<any> {
    return  this._genericService.genericCallServices('post', `${this.url.CONTEXT}${this.url.CONTEXT_USER}${this.url.USERS.SAVE}`, obj, null);
  }

  public updateUsers(obj: any): Observable<any> {
    return  this._genericService.genericCallServices('put', `${this.url.CONTEXT}${this.url.CONTEXT_USER}${this.url.USERS.UPDATE}${obj.idUser}`, obj, null);
  }

  public deleteUsers(id: any): Observable<any> {
    return  this._genericService.genericCallServices('delete', `${this.url.CONTEXT}${this.url.CONTEXT_USER}${this.url.USERS.DELETE}/${id}`, null , null);
  }
}
