import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { of, Observable } from 'rxjs';
import { GenericService } from './generic.services';
import { URL } from '../../common/url.services';

@Injectable()
export class ProviderService {

  constructor(private http: HttpClient, private _genericService: GenericService) { }

  url = URL;

  public getAllProviders(): Observable<any> {
    return  this._genericService.genericCallServices('get', `${this.url.CONTEXT}${this.url.CONTEXT_PROVIDER}${this.url.PROVIDERS.GET_LIST_ALL}`, null, null);
  }

  public getProviderById(id: any): Observable<any> {
    return  this._genericService.genericCallServices('get', `${this.url.CONTEXT}${this.url.CONTEXT_PROVIDER}${this.url.PROVIDERS.GET_BY_ID}/${id}`, null, null);
  }

  public saveProviders(obj: any): Observable<any> {
    return  this._genericService.genericCallServices('post', `${this.url.CONTEXT}${this.url.CONTEXT_PROVIDER}${this.url.PROVIDERS.SAVE}`, obj, null);
  }

  public updateProviders(obj: any): Observable<any> {
    return  this._genericService.genericCallServices('put', `${this.url.CONTEXT}${this.url.CONTEXT_PROVIDER}${this.url.PROVIDERS.UPDATE}${obj.idprovider}`, obj, null);
  }

  public deleteProviders(id: any): Observable<any> {
    return  this._genericService.genericCallServices('delete', `${this.url.CONTEXT}${this.url.CONTEXT_PROVIDER}${this.url.PROVIDERS.DELETE}/${id}`, null , null);
  }
}
