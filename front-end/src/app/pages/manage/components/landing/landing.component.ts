import { Component, Input } from '@angular/core';
import { User } from '../../../../models/user.model';
import { BankService } from '../../../../services/bank/bank.service';
import { Client } from '../../../../models/client.model';
import { Transfer } from '../../../../models/Transfer.model';
import { LocalStorageService } from '../../../../services/storage/local-storage.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrl: './landing.component.scss'
})
export class LandingComponent {
  @Input() userActive!: User;

  client!: Client;

  accountDetail = {
    accountNumber: "",
    balance: 0,
    status: "ACTIVE",
    transactions: [] as any[]
  }

  transfer: Transfer = {
    origin: "",
    destination: "",
    amount: 0
  }

  errMsg = "";

  showModalTransfer = false;
  showModalAccount = false

  constructor(private bankService: BankService, 
    private localStorageService: LocalStorageService) { }

  ngOnInit(): void {
    this.bankService.getClientByDni(this.userActive.dni)
      .subscribe(response => this.client = response);
  }

  openModalAccount(accountNumber: string) {
    this.bankService.getAccount(accountNumber)
      .subscribe(response => {
        this.showModalAccount = true;
        this.accountDetail = response;
      })
  }

  openModalTransfer(accountNumber: string) {
    this.showModalTransfer = true;
    this.transfer.origin = accountNumber;
  }

  logout() {
    this.localStorageService.removeItem("activeUser");
    window.location.reload();
  }

  toTransfer() {
    if(!this.transfer.destination) {
      this.errMsg = "Cuenta Inválida";
      return;
    }

    if(!this.transfer.amount || this.transfer.amount <= 0) {
      this.errMsg = "Monto Inválido";
      return;
    }


    this.bankService.toTransfer(this.transfer).subscribe(
      {
        next: () => { 
          this.showModalTransfer = false;
          this.errMsg = "";
          this.transfer.destination = "";
          this.transfer.amount = 0;
        },
        error: (err) => {
          this.transfer.destination = "";
          this.transfer.amount = 0;
          this.errMsg = "No se puedo procesar la transacción, verifique la entrada";
        }
      }
    )
  }
}
