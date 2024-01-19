import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../../services/interface/services.test';
import { ProviderService } from '../../services/interface/provider.services';

@Component({
  selector: 'app-provider',
  templateUrl: './provider.component.html',
  styles: []
})
export class ProviderComponent implements OnInit {

  constructor( public providerService: ProviderService) {
  }

  displayDialog: boolean;

    provider: any = {};
    selectedProvider: any;
    newUser: boolean;
    listProviders: any[];
    cols: any[];

// tslint:disable-next-line:typedef
ngOnInit() {

  this.displayDialog = false;
  this.cols = [
      { field: 'idprovider', header: 'Código' },
      { field: 'name', header: 'Nombre' }
  ];
  this.getProviders();
}

  // tslint:disable-next-line:typedef
  showDialogToAdd() {
    this.newUser = true;
    this.provider = {};
    this.displayDialog = true;
  }

  // tslint:disable-next-line:typedef
  save() {
   const listUsers = [...this.listProviders];

   if (this.newUser) {
    //listUsers.push(this.provider);
    this.providerService.saveProviders(this.provider).subscribe(response => {
      this.getProviders();
      //this.listUsers = response;
      this.provider = null;
      this.displayDialog = false;
     });
   }
    else {
      listUsers[this.listProviders.indexOf(this.selectedProvider)] = this.provider;
      this.providerService.updateProviders(this.provider).subscribe(response => {
        this.getProviders();
        //this.listUsers = response;
        this.provider = null;
        this.displayDialog = false;
       });
      /* this.providerService.updateUser(this.user).subscribe(response => {
        this.getUsers();
        //this.listUsers = response;
        this.user = null;
        this.displayDialog = false;
       }); */
    }
  }

  // tslint:disable-next-line:typedef
  delete(provider: any) {
  this.providerService.deleteProviders(provider.idprovider).subscribe(response => {
     this.getProviders();
     this.provider = null;
     this.displayDialog = false;
   });
  }

  // tslint:disable-next-line:typedef
  onRowSelect(event) {
    this.newUser = false;
    this.provider = this.cloneCar(event);
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
  getProviders() {
    this.providerService.getAllProviders().subscribe(response => {
        this.listProviders = response;
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


