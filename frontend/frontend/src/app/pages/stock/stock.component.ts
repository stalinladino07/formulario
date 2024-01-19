import { Component, OnInit } from '@angular/core';
import { StockService } from 'src/app/services/interface/stock.services';
import { ProductService } from 'src/app/services/interface/product.services';
import { Message,MessageService } from 'primeng/api';
import { StockModel } from 'src/app/services/models/stock.model';
import { UserService } from 'src/app/services/interface/user.services';
import { ProviderService } from 'src/app/services/interface/provider.services';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styles: []
})
export class StockComponent implements OnInit {

  constructor( public stockService: StockService, private productService: ProductService,
    private messageService: MessageService, private usersService: UserService, private providerService: ProviderService) {
  }

  displayDialog: boolean;

    stock:any = {};
    base64String: string = '';
   
    selectedProduct: any;
    newStock: boolean;
    listStock: any[];
    cols: any[];
    listProducts: any[] = [];
    selectProduct: any = [];
    msgs: Message[];

    listUsers: any[] = [];
    selectUsers: any = {};

    listProviders: any[] = [];
    selectProviders: any = {};


// tslint:disable-next-line:typedef
ngOnInit() {

  this.getCatalogoProducts();
  this.displayDialog = false;
  this.cols = [
      { field: 'idbills', header: 'Numero de Factura' },
      { field: 'cedula', header: 'Cliente' },
      { field: 'subtotal', header: 'Subtotal' },
      { field: 'iva', header: 'IVA' },
      { field: 'total', header: 'Total' }
  ];
  this.getStock();
  this.getAllUser();
  this.getAllProvider();
}

  // tslint:disable-next-line:typedef
  showDialogToAdd() {
    this.newStock = true;
    this.stock = new StockModel();
    this.displayDialog = true;
  }

  // tslint:disable-next-line:typedef
  save() {

   if (this.newStock) {
    this.stock.products = this.selectProduct;
    this.stock.user = this.selectUsers;
    this.stock.providers = this.selectProviders;
    this.stockService.saveStock(this.stock).subscribe(response => {
      this.getStock();
      this.stock = null;
      this.displayDialog = false;
     });
   }
    else {
      this.stock.products = this.selectProduct;
      this.stock.user = this.selectUsers;
      this.stock.providers = this.selectProviders;

      this.stockService.updateStock(this.stock).subscribe(response => {
        this.getStock();
        this.stock = null;
        this.displayDialog = false;
       });
    }
  }

  // tslint:disable-next-line:typedef
  delete(stock: any) {
  this.stockService.deleteStock(stock.idbills).subscribe(response => {
     this.getStock();
     this.stock = null;
     this.displayDialog = false;
   });
  }

  // tslint:disable-next-line:typedef
  onRowSelect(event) {
    this.newStock = false;
    let stockSelect = this.cloneCar(event);
    this.stockService.getByStore(stockSelect.idbills).subscribe(response => {
      this.stock = response;
      this.stock.idbills = stockSelect.idbills;
      this.selectProduct = response.products;
      this.selectProviders = response.providers;
      this.selectUsers = {cedula:response.user.cedula}
      this.displayDialog = true;
    });
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
  getStock() {
    this.stockService.getAllStock().subscribe(response => {
        this.listStock = response;
      });
  }

  // tslint:disable-next-line:typedef
  getCatalogoProducts() {
    this.listProducts = [];
    this.productService.getAllProducts().subscribe(response => {
        response.forEach(element => {
          this.listProducts.push({label: element.name, value: element});
        }); 
    }); 
  }


  getAllUser() {
    this.listUsers = [];
     this.usersService.getAllUsers().subscribe(response => {
        response.forEach(element => {
           this.listUsers.push( {label: element.name, value:{cedula:element.cedula}});
         }); 
     }); 
   }

   getAllProvider() {
    this.listProviders = [];
     this.providerService.getAllProviders().subscribe(response => {
        response.forEach(element => {
           this.listProviders.push( {label: element.name, value: element});
         }); 
     }); 
   }

     // tslint:disable-next-line:typedef
  onChangeMulti() {
    this.stock.iva = 12;
    const sum = this.selectProduct.reduce((acc, item) => acc + item.price, 0);
    this.stock.subtotal = sum;
    this.stock.total = Math.round(sum * 1.12);
  }

  generateReport() {
    this.stock.AUTOR = this.stock.user.cedula
  this.stockService.report(this.stock).subscribe(response => {
    this.download(response.data,'pdf')
   /* this.getStock();
    this.stock = null;
    this.displayDialog = false;*/
   });
  }

  
  download(base64:any, ext:string){


    this.base64String = base64;

    const byteCharacters = atob(this.base64String);
    const byteArrays = [];

    for (let offset = 0; offset < byteCharacters.length; offset += 512) {
      const slice = byteCharacters.slice(offset, offset + 512);
      const byteNumbers = new Array(slice.length);
      for (let i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
      }
      const byteArray = new Uint8Array(byteNumbers);
      byteArrays.push(byteArray);
    }

    const blob = new Blob(byteArrays, { type: 'application/pdf' });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = 'file.'+ext;
    link.click();
    window.URL.revokeObjectURL(url);

  }
  
}


