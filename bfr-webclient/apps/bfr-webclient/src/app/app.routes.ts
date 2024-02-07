import { Route } from '@angular/router';
import { ProductListViewComponent } from './component/product-list-view/product-list-view.component';

export const appRoutes: Route[] = [
  {
    path: '',
    redirectTo: '/products',
    pathMatch: 'full',
  },

  {
    path: 'products',
    component: ProductListViewComponent,
    loadChildren: () => import('@bfr/product').then((m) => m.ProductModule),
  },
];
