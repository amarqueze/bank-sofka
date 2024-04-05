import { Component } from '@angular/core';
import { BankService } from '../../services/bank/bank.service';
import { Client } from '../../models/client.model';
import { Deposit } from '../../models/deposit.model';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.scss'
})
export class AdminComponent {
  dni: string = "";
  clientSelected: Client = {
    dni: "",
    name: "",
    lastName: "",
    address: "",
    phone: "",
    accounts: []
  };

  deposit: Deposit = {
    destinationAccount: "",
    amount: 0
  }

  showModal = false;
  errMsg = "";

  constructor(private bankService: BankService) {}

  ngOnInit(): void {   
  }

  findClient(): void {
    this.bankService.getClientByDni(this.dni)
      .subscribe({
        next: (response) => this.clientSelected = response,
        error: (err) => this.clearFields()
      }); 
  }

  toDeposit(): void {
    if(!this.deposit.destinationAccount) {
      this.errMsg = "Cuenta Inválida";
      return;
    }

    if(!this.deposit.amount || this.deposit.amount <= 0) {
      this.errMsg = "Monto Inválido";
      return;
    }

    this.bankService.toDeposit(this.deposit).subscribe(
      {
        next: () => { 
          this.showModal = false;
          this.errMsg = "";
          this.deposit.destinationAccount = "";
          this.deposit.amount = 0;
        },
        error: (err) => {
          this.deposit.destinationAccount = "";
          this.deposit.amount = 0;
          this.errMsg = "No se puedo procesar la transacción, verifique la entrada";
        }
      }
    )
  }

  openModal() {
    this.showModal = true;
  }

  clearFields() {
    this.clientSelected = {
      dni: "",
      name: "",
      lastName: "",
      address: "",
      phone: "",
      accounts: []
    };
  }
}
