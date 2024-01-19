import { RouterModule, Routes } from '@angular/router';
import { PagesComponent } from './pages.component';
import { ProductsComponent } from './products/products.component';
import { StockComponent } from './stock/stock.component';
import { InventoryComponent } from './inventory/inventory.component';
import { LoginGuardGuard } from '../services/guards/login-guard.guard';
import { ProviderComponent } from './provider/provider.component';
import { UserComponent } from './user/user.component';

const pagesRoutes: Routes = [
    {
        path: '',
        component: PagesComponent,
        canActivate: [ LoginGuardGuard ],
        children: [
            { path: 'stock', component: StockComponent},
            { path: 'product', component: ProductsComponent},
            { path: 'users', component: UserComponent},
            { path: 'provider', component: ProviderComponent},
            { path: '', redirectTo: '/login', pathMatch: 'full' }
        ]
    }
];

export const PAGES_ROUTES = RouterModule.forChild( pagesRoutes );
