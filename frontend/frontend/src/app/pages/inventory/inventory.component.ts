import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/interface/product.services';
import { InventoryService } from '../../services/interface/inventory.services';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styles: []
})
export class InventoryComponent implements OnInit {

  constructor( public inventoryService: InventoryService, private productService: ProductService) {
  }

  displayDialog: boolean;

    inventory: any = {};
    selectProduct: any = {};
    newInventory: boolean;
    listInventory: any[];
    cols: any[];
    listProducts: any[];
// tslint:disable-next-line:typedef
ngOnInit() {

  this.getCatalogoInventorys();
  this.displayDialog = false;
  this.cols = [
      { field: 'code', header: 'Código Producto' },
      { field: 'provider', header: 'Proveedor' },
      { field: 'quantity', header: 'Cantidad Comprada' },
      { field: 'totalPrice', header: 'Precio' }
  ];
  this.getInventory();
}

  // tslint:disable-next-line:typedef
  showDialogToAdd() {
    this.newInventory = true;
    this.inventory = {};
    this.displayDialog = true;
  }

  // tslint:disable-next-line:typedef
  save() {
   if (this.newInventory) {
    this.inventory.idProduct = this.selectProduct.idProduct;
    this.inventory.code = this.selectProduct.code;
    this.inventoryService.saveInventory(this.inventory).subscribe(response => {
      this.getInventory();
      this.inventory = null;
      this.displayDialog = false;
     });
   }
    else {
      this.inventory.idProduct = !this.selectProduct.idProduct? this.inventory.idProduct: this.selectProduct.idProduct;
      this.inventory.code = !this.selectProduct.code? this.inventory.code: this.selectProduct.code;
      this.inventoryService.saveInventory(this.inventory).subscribe(response => {
        this.getInventory();
        this.inventory = null;
        this.displayDialog = false;
       });
    }
  }

  // tslint:disable-next-line:typedef
  delete(inventory: any) {
  this.inventoryService.deleteInventory(inventory.idInventory).subscribe(response => {
     this.getInventory();
     this.inventory = null;
     this.displayDialog = false;
   });
  }

  // tslint:disable-next-line:typedef
  onRowSelect(event) {
    // this.getCatalogoInventorys();
    this.newInventory = false;
    this.inventory = this.cloneCar(event);
    this.displayDialog = true;
  }

  cloneCar(c: any): any {
    const user = {};
    // tslint:disable-next-line:forin
    for (const prop in c) {
        user[prop] = c[prop];
    }
    return user;
  }

  // tslint:disable-next-line:typedef
  getInventory() {
    this.inventoryService.getAllInventory().subscribe(response => {
        this.listInventory = response;
      });
  }

  // tslint:disable-next-line:typedef
  getCatalogoInventorys() {
    this.listProducts =[];
    this.productService.getCatalogProductsAll().subscribe(response => {
        response.forEach(element => {
          this.listProducts.push({label: element.name + ' - '+ element.code, value: element});
        });
    }); 
  }

}


