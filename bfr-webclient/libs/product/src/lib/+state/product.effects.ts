import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, concatMap, map, of } from 'rxjs';
import * as ProductActions from './product.actions';
import { NGXLogger } from 'ngx-logger';
import { ProductBackendService } from '../services/backend/product-backend.service';

@Injectable()
export class ProductEffects {
  constructor(
    private readonly actions$: Actions,
    private readonly productService: ProductBackendService,
    private readonly logger: NGXLogger,
  ) {}

  init$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ProductActions.loadProducts),
      concatMap(() =>
        this.productService.getProducts().pipe(
          map((products) =>
            ProductActions.loadProductsSuccess({ products: products }),
          ),
          catchError((error) => {
            this.logger.error(error.message);
            return of(ProductActions.loadProductsFailure({ error }));
          }),
        ),
      ),
    ),
  );
}
