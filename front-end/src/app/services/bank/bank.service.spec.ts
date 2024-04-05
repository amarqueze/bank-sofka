import { TestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { BankService } from './bank.service';
import { Client } from '../../models/client.model';
import { Deposit } from '../../models/deposit.model';
import { Transfer } from '../../models/transfer.model';

describe('BankService', () => {
  let service: BankService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [BankService]
    });
    service = TestBed.inject(BankService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get client by DNI', () => {
    const clientMock: Client = {
      dni: "123456789",
      name: "John",
      lastName: "Doe",
      address: "XXXXXX",
      phone: "3205221078",
      accounts: []
    };

    service.getClientByDni('12345678A').subscribe(client => {
      expect(client).toEqual(clientMock);
    });

    const request = httpMock.expectOne('http://localhost:8080/api/client/12345678A');
    expect(request.request.method).toBe('GET');
    request.flush(clientMock);
  });

  it('should deposit', () => {
    const depositMock: Deposit = {
      destinationAccount: '123',
      amount: 100
    };

    service.toDeposit(depositMock).subscribe(response => {
      expect(response).toBeTruthy();
    });

    const request = httpMock.expectOne('http://localhost:8080/api/transaction/deposit');
    expect(request.request.method).toBe('POST');
    expect(request.request.body).toEqual(depositMock);
    request.flush({});
  });

  it('should transfer', () => {
    const transferMock: Transfer = {
      origin: "123",
      destination: "456",
      amount: 100
    };

    service.toTransfer(transferMock).subscribe(response => {
      expect(response).toBeTruthy();
    });

    const request = httpMock.expectOne('http://localhost:8080/api/transaction/transfer');
    expect(request.request.method).toBe('POST');
    expect(request.request.body).toEqual(transferMock);
    request.flush({});
  });
});