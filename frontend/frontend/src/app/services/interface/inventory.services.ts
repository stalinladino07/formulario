import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { of, Observable } from 'rxjs';
import { GenericService } from './generic.services';
import { URL } from '../../common/url.services';

@Injectable()
export class InventoryService {

  constructor(private http: HttpClient, private _genericService: GenericService) { }

  url = URL;

  public getAllInventory(): Observable<any> {
    return  this._genericService.genericCallServices('get', `${this.url.CONTEXT}${this.url.CONTEXT_INVENTORY}${this.url.INVENTORY.GET_LIST_ALL}`, null, null);
  }

  public getProductById(id: any): Observable<any> {
    return  this._genericService.genericCallServices('get', `${this.url.CONTEXT}${this.url.CONTEXT_INVENTORY}${this.url.INVENTORY.GET_BY_ID}/${id}`, null, null);
  }

  public saveInventory(obj: any): Observable<any> {
    //obj.date = new Date().toLocaleString();
    return  this._genericService.genericCallServices('post', `${this.url.CONTEXT}${this.url.CONTEXT_INVENTORY}${this.url.INVENTORY.SAVE}`, obj, null);
  }

  public deleteInventory(id: any): Observable<any> {
    return  this._genericService.genericCallServices('delete', `${this.url.CONTEXT}${this.url.CONTEXT_INVENTORY}${this.url.INVENTORY.DELETE}/${id}`, null , null);
  }
  
}
