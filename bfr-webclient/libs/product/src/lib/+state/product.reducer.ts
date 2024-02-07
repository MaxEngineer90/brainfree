import { createEntityAdapter, EntityAdapter, EntityState } from '@ngrx/entity';
import { Action, createReducer, on } from '@ngrx/store';

import * as ProductActions from './product.actions';
import { HttpErrorResponse } from '@angular/common/http';
import { ProductDto, selectId } from './productDto';

export const PRODUCT_FEATURE_KEY = 'product';

export interface ProductState extends EntityState<ProductDto> {
  selectedId?: string; // which Product record has been selected
  loaded: boolean; // has the Product list been loaded
  error?: HttpErrorResponse | null; // last known error (if any)
}

export interface ProductPartialState {
  readonly [PRODUCT_FEATURE_KEY]: ProductState;
}

export const productAdapter: EntityAdapter<ProductDto> =
  createEntityAdapter<ProductDto>({
    selectId: selectId,
  });

export const initialProductState: ProductState = productAdapter.getInitialState(
  {
    // set initial required properties
    loaded: false,
    selectId: undefined,
  },
);

const reducer = createReducer(
  initialProductState,
  on(ProductActions.loadProducts, (state) => ({
    ...state,
    loaded: false,
    error: null,
  })),
  on(ProductActions.loadProductsSuccess, (state, { products: products }) =>
    productAdapter.setAll(products, { ...state, loaded: true }),
  ),
  on(ProductActions.loadProductsFailure, (state, { error }) => ({
    ...state,
    error,
  })),
);

export function productReducer(
  state: ProductState | undefined,
  action: Action,
) {
  return reducer(state, action);
}
