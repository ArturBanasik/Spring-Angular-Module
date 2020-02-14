import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {BasicAuthenticationService} from '../service/basic-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username = '';
  password = '';
  invalidLogin = false;

  constructor(private router: Router, private basicAuthenticationService: BasicAuthenticationService) {
  }

  ngOnInit() {
  }

  handleLogin() {
    this.basicAuthenticationService.executeAuthService(this.username, this.password).subscribe(
      data => {
        this.router.navigate(['/welcome', this.username]);
        this.invalidLogin = false;
      },
      error => {
        this.invalidLogin = true;
      }
    );

  }
}
