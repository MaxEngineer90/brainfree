import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { ProductDto } from '../../+state/productDto';

@Injectable({
  providedIn: 'root',
})
export class ProductBackendService {
  private readonly base_url = 'http://localhost:8087/brainfree/api/v1/products';

  constructor(private readonly http: HttpClient) {}

  getProducts = (): Observable<Array<ProductDto>> => {
    return this.http
      .get<Array<ProductDto>>(this.base_url)
      .pipe(
        map((products) =>
          products.sort((p1, p2) => p1.name.localeCompare(p2.name)),
        ),
      );
  };
}
