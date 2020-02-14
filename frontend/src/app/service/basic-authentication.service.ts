import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService {

  constructor(private http: HttpClient) {
  }

  isUserLoggedIn() {
    if (this.getAuthenticatedUser()) {
      return true;
    } else {
      return false;
    }
  }

  executeAuthService(username, password) {
    const authHeaderString = 'Basic ' + window.btoa(username + ':' + password);

    const headers = new HttpHeaders({
      Authorization: authHeaderString
    });

    return this.http.get<AuthenticationBean>('http://localhost:8081/authenticate',
      {headers}).pipe(
      map(
        data => {
          sessionStorage.setItem('authUser', username);
          sessionStorage.setItem('authToken', authHeaderString);
          return data;
        }
      )
    );
  }

  getAuthenticatedUser() {
    return sessionStorage.getItem('authUser');
  }

  getAuthenticatedToken() {
    if (this.getAuthenticatedUser()) {
      return sessionStorage.getItem('authToken');
    }
  }

  logout() {
    sessionStorage.removeItem('authUser');
    sessionStorage.removeItem('authToken');
  }
}

export class AuthenticationBean {
  constructor(public message: string) {
  }
}
