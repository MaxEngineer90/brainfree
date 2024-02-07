/**
 * Interface for the 'Product' data
 */
export interface ProductDto {
  name: string;
  readonly articleNumber: string;
  category: string;
  description: string;
  priceAccording: string;
  price: number;
}

export const selectId = (entity: ProductDto) => entity.articleNumber;
