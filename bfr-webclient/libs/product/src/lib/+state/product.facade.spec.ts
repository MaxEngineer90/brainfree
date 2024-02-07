import { NgModule } from '@angular/core';
import { TestBed } from '@angular/core/testing';
import { EffectsModule } from '@ngrx/effects';
import { Store, StoreModule } from '@ngrx/store';
import { readFirst } from '@nx/angular/testing';

import * as ProductActions from './product.actions';
import { ProductEffects } from './product.effects';
import { ProductFacade } from './product.facade';
import { ProductDto } from './productDto';
import {
  PRODUCT_FEATURE_KEY,
  productReducer,
  ProductState,
} from './product.reducer';

interface TestSchema {
  product: ProductState;
}

describe('ProductFacade', () => {
  let facade: ProductFacade;
  let store: Store<TestSchema>;
  const createProductEntity = (id: string, name = ''): ProductDto => ({
    id,
    name: name || `name-${id}`,
  });

  describe('used in NgModule', () => {
    beforeEach(() => {
      @NgModule({
        imports: [
          StoreModule.forFeature(PRODUCT_FEATURE_KEY, productReducer),
          EffectsModule.forFeature([ProductEffects]),
        ],
        providers: [ProductFacade],
      })
      class CustomFeatureModule {}

      @NgModule({
        imports: [
          StoreModule.forRoot({}),
          EffectsModule.forRoot([]),
          CustomFeatureModule,
        ],
      })
      class RootModule {}

      TestBed.configureTestingModule({ imports: [RootModule] });

      store = TestBed.inject(Store);
      facade = TestBed.inject(ProductFacade);
    });

    /**
     * The initially generated facade::loadAll() returns empty array
     */
    it('loadAll() should return empty list with loaded == true', async () => {
      let list = await readFirst(facade.selectAllProducts$);
      let isLoaded = await readFirst(facade.loaded$);

      expect(list.length).toBe(0);
      expect(isLoaded).toBe(false);

      facade.init();

      list = await readFirst(facade.selectAllProducts$);
      isLoaded = await readFirst(facade.loaded$);

      expect(list.length).toBe(0);
      expect(isLoaded).toBe(true);
    });

    /**
     * Use `loadProductSuccess` to manually update list
     */
    it('allProduct$ should return the loaded list; and loaded flag == true', async () => {
      let list = await readFirst(facade.selectAllProducts$);
      let isLoaded = await readFirst(facade.loaded$);

      expect(list.length).toBe(0);
      expect(isLoaded).toBe(false);

      store.dispatch(
        ProductActions.loadProductsSuccess({
          product: [createProductEntity('AAA'), createProductEntity('BBB')],
        }),
      );

      list = await readFirst(facade.selectAllProducts$);
      isLoaded = await readFirst(facade.loaded$);

      expect(list.length).toBe(2);
      expect(isLoaded).toBe(true);
    });
  });
});
