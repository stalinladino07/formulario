import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/interface/login.services';
import { User } from '../services/models/user.model';
import Swal from 'sweetalert2';

declare function init_plugins();

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {
  user: User = new User();

  constructor(public router: Router, public _loginService: LoginService) { }

  ngOnInit() {
    init_plugins();
    this._loginService.logout();
  }

  ingresar(user: User) {
    this._loginService.login(user).subscribe((response: any) => {
      if (response.data != null) {
          this.router.navigate(['/product']);
      } else {
        Swal.fire({
          icon: 'error',
          text: response.message
        });
      }
    }, (err) => {
      Swal.fire({
        icon: 'error',
        text: 'Invalid User'
      });
    });
  }

}
