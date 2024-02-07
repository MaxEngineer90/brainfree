import { Component, OnDestroy, OnInit } from '@angular/core';
import { ProductDto, ProductFacade } from '@bfr/product';
import { Observable, Subscription } from 'rxjs';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'bfr-webclient-product-list-view',
  templateUrl: './product-list-view.component.html',
  styleUrls: ['./product-list-view.component.scss'],
})
export class ProductListViewComponent implements OnInit, OnDestroy {
  products: Array<ProductDto> = [];
  productsSlice!: Array<ProductDto>;
  pageIndex = 10;

  private products$!: Observable<Array<ProductDto>>;
  private productsSubscription!: Subscription;

  constructor(private readonly productFacade: ProductFacade) {}

  ngOnInit() {
    const zero = 0;
    this.setProducts();
    this.productsSubscription = this.products$.subscribe((data) => {
      this.products = data;
      this.productsSlice = this.products.slice(zero, this.pageIndex);
    });
  }

  ngOnDestroy(): void {
    if (this.productsSubscription) {
      this.productsSubscription.unsubscribe();
    }
  }

  OnPageChanged = (event: PageEvent): void => {
    const startIndex = event.pageIndex * event.pageSize;
    let endIndex = startIndex + event.pageSize;

    if (endIndex > this.products.length) {
      endIndex = this.products.length;
    }
    this.productsSlice = this.products.slice(startIndex, endIndex);
  };

  private setProducts(): void {
    this.productFacade.init();
    this.products$ = this.productFacade.selectAllProducts$;
  }
}
