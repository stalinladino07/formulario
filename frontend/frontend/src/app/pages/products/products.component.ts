import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../../services/interface/services.test';
import { ProductService } from '../../services/interface/product.services';

@Component({
  selector: 'app-product',
  templateUrl: './products.component.html',
  styles: []
})
export class ProductsComponent implements OnInit {

  constructor( public productService: ProductService) {
  }

  displayDialog: boolean;

    product: any = {};
    selectedProduct: any;
    newUser: boolean;
    listProducts: any[];
    cols: any[];

// tslint:disable-next-line:typedef
ngOnInit() {

  this.displayDialog = false;
  this.cols = [
      { field: 'idProduct', header: 'Código' },
      { field: 'name', header: 'Nombre' },
      { field: 'price', header: 'Precio Unitario' }
  ];
  this.getProducts();
}

  // tslint:disable-next-line:typedef
  showDialogToAdd() {
    this.newUser = true;
    this.product = {};
    this.displayDialog = true;
  }

  // tslint:disable-next-line:typedef
  save() {
   const listUsers = [...this.listProducts];

   if (this.newUser) {
    //listUsers.push(this.product);
    this.productService.saveProducts(this.product).subscribe(response => {
      this.getProducts();
      //this.listUsers = response;
      this.product = null;
      this.displayDialog = false;
     });
   }
    else {
      listUsers[this.listProducts.indexOf(this.selectedProduct)] = this.product;
      this.productService.updateProducts(this.product).subscribe(response => {
        this.getProducts();
        //this.listUsers = response;
        this.product = null;
        this.displayDialog = false;
       });
      /* this.productService.updateUser(this.user).subscribe(response => {
        this.getUsers();
        //this.listUsers = response;
        this.user = null;
        this.displayDialog = false;
       }); */
    }
  }

  // tslint:disable-next-line:typedef
  delete(product: any) {
  this.productService.deleteProducts(product.idProduct).subscribe(response => {
     this.getProducts();
     this.product = null;
     this.displayDialog = false;
   });
  }

  // tslint:disable-next-line:typedef
  onRowSelect(event) {
    this.newUser = false;
    this.product = this.cloneCar(event);
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
  getProducts() {
    this.productService.getAllProducts().subscribe(response => {
        this.listProducts = response;
      });
  }

  // tslint:disable-next-line:typedef
  getCatalogoIndustria() {
   /*  this.industria = [
      {label: 'Seleccione', value: null}
    ];
    let data: Catalogo[] = [];
    this.configService.getCatalogoIndustria().subscribe(response => {
        data = response.data;
        data.forEach(element => {
          this.industria.push({label: element.text, value: element});
        });
    }); */
  }
}


