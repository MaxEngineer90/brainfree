import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { User } from '../../model/user';

@Component({
  selector: 'bfr-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  firstLetterFromName!: string;

  constructor(private readonly keycloakService: KeycloakService) {}

  ngOnInit(): void {
    this.setFirstLetterFromToken();
  }

  get fullName(): string {
    const user = this.getUserFromToken();
    return `${user.given_name} ${user.family_name}`;
  }

  openAccount(): void {
    this.keycloakService.getKeycloakInstance().accountManagement().then();
  }

  logout(): void {
    this.keycloakService.logout('http://localhost:4200/logout').then();
  }

  private getUserFromToken(): User {
    return this.keycloakService.getKeycloakInstance().tokenParsed as User;
  }

  private setFirstLetterFromToken(): void {
    const user = this.getUserFromToken();
    this.firstLetterFromName = `${user.given_name.substring(
      0,
      1
    )}${user.family_name.substring(0, 1)}`;
  }
}
