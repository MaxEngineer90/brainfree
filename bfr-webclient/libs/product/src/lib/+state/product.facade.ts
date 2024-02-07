import { inject, Injectable } from '@angular/core';
import { select, Store } from '@ngrx/store';

import * as ProductActions from './product.actions';
import * as ProductSelectors from './product.selectors';

@Injectable()
export class ProductFacade {
  private readonly store = inject(Store);

  /**
   * Combine pieces of state using createSelector,
   * and expose them as observables through the facade.
   */
  loaded$ = this.store.pipe(select(ProductSelectors.selectProductLoaded));
  selectAllProducts$ = this.store.pipe(
    select(ProductSelectors.selectAllProduct),
  );
  selectedProduct$ = this.store.pipe(select(ProductSelectors.selectEntity));

  /**
   * Use the initialization action to perform one
   * or more tasks in your Effects.
   */
  init() {
    this.store.dispatch(ProductActions.loadProducts());
  }
}
