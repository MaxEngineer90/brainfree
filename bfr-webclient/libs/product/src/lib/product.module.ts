import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { productRoutes } from './lib.routes';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import * as fromProduct from './+state/product.reducer';
import { ProductEffects } from './+state/product.effects';
import { ProductFacade } from './+state/product.facade';
import { LoggerModule, NgxLoggerLevel } from 'ngx-logger';

@NgModule({
  imports: [
    CommonModule,
    LoggerModule.forRoot({
      serverLoggingUrl: '/api/logs',
      level: NgxLoggerLevel.DEBUG,
      serverLogLevel: NgxLoggerLevel.ERROR,
    }),
    RouterModule.forChild(productRoutes),
    StoreModule.forFeature(
      fromProduct.PRODUCT_FEATURE_KEY,
      fromProduct.productReducer,
    ),
    EffectsModule.forFeature([ProductEffects]),
  ],
  providers: [ProductFacade],
})
export class ProductModule {}
