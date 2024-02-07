import { Component, Input } from '@angular/core';
import { ProductDto } from '@bfr/product';

@Component({
  selector: 'bfr-product-list-item',
  templateUrl: './product-list-item.component.html',
  styleUrls: ['./product-list-item.component.scss'],
})
export class ProductListItemComponent {
  @Input() product!: ProductDto;

  openDescription() {}

  //TODO: AS PIPE
  getFormattedPrice(price: number) {
    return `${price} €`;
  }

  addProductToCart() {}
}
