import { RouterModule, Routes } from '@angular/router';
import { PagesComponent } from './pages/pages.component';
import { LoginComponent } from './login/login.component';

//LoginComponent
const appRoutes: Routes = [
    { path: '**', component: LoginComponent },
    //{ path: 'pages', component: PagesComponent }
];


export const APP_ROUTES = RouterModule.forRoot( appRoutes, { useHash: true } );
