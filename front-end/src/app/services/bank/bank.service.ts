import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from '../../models/client.model';
import { Observable } from 'rxjs';
import { Deposit } from '../../models/deposit.model';

@Injectable({
  providedIn: 'root'
})
export class BankService {

  constructor(private httpClient: HttpClient) { }

  public getClientByDni(dni: string): Observable<Client> {
    return this.httpClient.get<Client>(`http://localhost:8080/api/client/${dni}`);
  }

  public toDeposit(deposit: Deposit): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8080/api/transaction/deposit', deposit);
  }
  
}
