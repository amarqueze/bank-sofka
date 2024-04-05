import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from '../../models/client.model';
import { Observable } from 'rxjs';
import { Deposit } from '../../models/deposit.model';
import { Transfer } from '../../models/transfer.model';

@Injectable({
  providedIn: 'root'
})
export class BankService {

  constructor(private httpClient: HttpClient) { }

  public getClientByDni(dni: string): Observable<Client> {
    return this.httpClient.get<Client>(`http://localhost:8080/api/client/${dni}`);
  }

  public getAccount(accountNumber: string): Observable<any> {
    return this.httpClient.get<any>(`http://localhost:8080/api/account/${accountNumber}`);
  }

  public authenticate(nickname: string, password: string): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8080/api/auth', {nickname, password});
  }

  public toDeposit(deposit: Deposit): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8080/api/transaction/deposit', deposit);
  }

  public toTransfer(transfer: Transfer): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8080/api/transaction/transfer', transfer);
  }
  
}
