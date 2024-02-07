import { createAction, props } from '@ngrx/store';
import { ProductDto } from './productDto';
import { HttpErrorResponse } from '@angular/common/http';

export const loadProducts = createAction('[Product Page] Init');

export const loadProductsSuccess = createAction(
  '[Product/API] Load Product Success',
  props<{ products: Array<ProductDto> }>(),
);

export const loadProductsFailure = createAction(
  '[Product/API] Load Product Failure',
  props<{ error: HttpErrorResponse }>(),
);
