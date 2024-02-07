import { KeycloakOptions, KeycloakService } from 'keycloak-angular';
import { KeycloakConfig, KeycloakInitOptions } from 'keycloak-js';

const keycloakConfig: KeycloakConfig = {
  url: 'http://localhost:8080',
  realm: 'brainfree',
  clientId: 'brainfree-webclient',
};

const keycloakInitOptions: KeycloakInitOptions = {
  onLoad: 'login-required',
  checkLoginIframe: false,
};

const keycloakOptions: KeycloakOptions = {
  config: keycloakConfig,
  initOptions: keycloakInitOptions,
  enableBearerInterceptor: true,
};

export const environment = {
  production: false,
  keycloakOptions,
};

export function initializeKeycloak(
  keycloak: KeycloakService
): () => Promise<any> {
  return (): Promise<any> => {
    return new Promise(async (resolve, reject) => {
      try {
        await keycloak.init(environment.keycloakOptions);
        console.log('Keycloak is initialized');
        // @ts-ignore
        resolve();
      } catch (error) {
        console.log('Error thrown in init ' + error);
        reject(error);
      }
    });
  };
}
