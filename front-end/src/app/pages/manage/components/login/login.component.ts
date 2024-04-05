import { Component, EventEmitter, Output } from '@angular/core';
import { User } from '../../../../models/user.model';
import { BankService } from '../../../../services/bank/bank.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  @Output() authenticationSuccess: EventEmitter<User> = new EventEmitter<User>();

  auth = {
    nickname: "",
    password: ""
  }

  errMsg: string = "";

  constructor(private bankService: BankService) { }

  authenticate() {
    this.bankService.authenticate(this.auth.nickname, this.auth.password)
      .subscribe({
        next: (response) => {
          this.errMsg = "";
          this.authenticationSuccess.emit({ dni: response.dni, name: response.name });
        },
        error: (err) => { 
          if (err.status === 401) {
            this.errMsg = "Usuario o contraseña son inválidos";
          } else {
            this.errMsg = "Fallo la autenticación";
          }
        }
      })
  }
}
