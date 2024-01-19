import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { of, Observable } from 'rxjs';
import { GenericService } from './generic.services';
import { URL } from '../../common/url.services';

@Injectable()
export class StockService {

  constructor(private http: HttpClient, private _genericService: GenericService) { }

  url = URL;

  public getAllStock(): Observable<any> {
    return  this._genericService.genericCallServices('get', `${this.url.CONTEXT}${this.url.CONTEXT_STOCK}${this.url.STOCK.GET_LIST_ALL}`, null, null);
  }

  public getProductById(id: any): Observable<any> {
    return  this._genericService.genericCallServices('get', `${this.url.CONTEXT}${this.url.CONTEXT_STOCK}${this.url.STOCK.GET_BY_ID}/${id}`, null, null);
  }

  public getByStore(id: any): Observable<any> {
    return  this._genericService.genericCallServices('get', `${this.url.CONTEXT}${this.url.CONTEXT_STOCK}${this.url.STOCK.GET_BY_STORE}/${id}`, null, null);
  }

  public saveStock(obj: any): Observable<any> {
    return  this._genericService.genericCallServices('post', `${this.url.CONTEXT}${this.url.CONTEXT_STOCK}${this.url.STOCK.SAVE}`, obj, null);
  }

  public deleteStock(id: any): Observable<any> {
    return  this._genericService.genericCallServices('delete', `${this.url.CONTEXT}${this.url.CONTEXT_STOCK}${this.url.STOCK.DELETE}/${id}`, null , null);
  }

  public updateStock(obj: any): Observable<any> {
    return  this._genericService.genericCallServices('put', `${this.url.CONTEXT}${this.url.CONTEXT_STOCK}${this.url.STOCK.UPDATE}/${obj.idbills}`, obj , null);
  }

  public report(obj: any): Observable<any> {
    return  this._genericService.genericCallServices('post', `${this.url.CONTEXT}${this.url.CONTEXT_REPORT}`, obj, null);
  }
}
