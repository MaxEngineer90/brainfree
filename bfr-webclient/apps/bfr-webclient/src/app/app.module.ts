import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { appRoutes } from './app.routes';
import { AuthModule } from '@bfr/auth';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavigationModule } from '@bfr/navigation';

import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../../../../libs/auth/src/lib/config/keycloak-conf';

import { MatCardModule } from '@angular/material/card';
import { MatPaginatorModule } from '@angular/material/paginator';
import { ProductFacade } from '@bfr/product';
import { MatDividerModule } from '@angular/material/divider';
import { ProductListViewComponent } from './component/product-list-view/product-list-view.component';
import { ProductListItemComponent } from './component/product-list-view/product-list-item/product-list-item.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [
    AppComponent,
    ProductListViewComponent,
    ProductListItemComponent,
  ],
  imports: [
    AuthModule,
    MatIconModule,
    BrowserModule,
    StoreModule.forRoot({}),
    EffectsModule.forRoot([]),
    RouterModule.forRoot(appRoutes, { initialNavigation: 'enabledBlocking' }),
    BrowserAnimationsModule,
    NavigationModule,
    StoreDevtoolsModule.instrument({ logOnly: environment.production }),
    MatCardModule,
    MatPaginatorModule,
    MatDividerModule,
    MatButtonModule,
  ],
  providers: [ProductFacade],
  bootstrap: [AppComponent],
})
export class AppModule {}
