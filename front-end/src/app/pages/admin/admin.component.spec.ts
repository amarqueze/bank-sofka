import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AdminComponent } from './admin.component';
import { BankService } from '../../services/bank/bank.service';
import { Client } from '../../models/client.model';
import { Deposit } from '../../models/deposit.model';
import { of, throwError } from 'rxjs';

describe('AdminComponent', () => {
  let component: AdminComponent;
  let fixture: ComponentFixture<AdminComponent>;
  let mockBankService: jest.Mocked<BankService>;

  beforeEach(async () => {
    mockBankService = {
      getClientByDni: jest.fn(),
      toDeposit: jest.fn()
    } as unknown as jest.Mocked<BankService>;

    await TestBed.configureTestingModule({
      declarations: [AdminComponent],
      providers: [{ provide: BankService, useValue: mockBankService }]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call bank service to get client by DNI', () => {
    const clientMock: Client = {
      dni: "123456789",
      name: "John",
      lastName: "Doe",
      address: "XXXXXX",
      phone: "3205221078",
      accounts: []
    };
    mockBankService.getClientByDni.mockReturnValue(of(clientMock));

    component.dni = '123456789';
    component.findClient();

    expect(mockBankService.getClientByDni).toHaveBeenCalledWith('123456789');
    expect(component.clientSelected).toEqual(clientMock);
  });

  it('should handle error when getting client by DNI', () => {
    mockBankService.getClientByDni.mockReturnValue(throwError('Error'));

    component.dni = '123456789';
    component.findClient();

    expect(mockBankService.getClientByDni).toHaveBeenCalledWith('123456789');
    expect(component.clientSelected).toEqual({
      dni: '',
      name: '',
      lastName: '',
      address: '',
      phone: '',
      accounts: []
    });
  });

  it('should handle deposit transaction', () => {
    const depositMock: Deposit = {
      destinationAccount: '123',
      amount: 100
    };
    mockBankService.toDeposit.mockReturnValue(of(null));

    component.deposit = depositMock;
    component.toDeposit();

    expect(mockBankService.toDeposit).toHaveBeenCalledWith(depositMock);
    expect(component.showModal).toBeFalsy();
    expect(component.errMsg).toBe('');
    expect(component.deposit).toEqual({ destinationAccount: '', amount: 0 });
  });

  it('should handle error when depositing', () => {
    mockBankService.toDeposit.mockReturnValue(throwError('Error'));

    component.toDeposit();

    expect(component.deposit).toEqual({ destinationAccount: '', amount: 0 });
  });
});
