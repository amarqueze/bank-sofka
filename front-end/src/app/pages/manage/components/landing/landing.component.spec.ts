import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LandingComponent } from './landing.component';
import { BankService } from '../../../../services/bank/bank.service';
import { LocalStorageService } from '../../../../services/storage/local-storage.service';
import { of, throwError } from 'rxjs';
import { User } from '../../../../models/user.model';

describe('LandingComponent', () => {
  let component: LandingComponent;
  let fixture: ComponentFixture<LandingComponent>;
  let mockBankService: jest.Mocked<BankService>;
  let mockLocalStorageService: jest.Mocked<LocalStorageService>;

  beforeEach(async () => {
    mockBankService = {
      getClientByDni: jest.fn().mockReturnValue(of({})),
      getAccount: jest.fn(),
      toTransfer: jest.fn()
    } as unknown as jest.Mocked<BankService>;

    mockLocalStorageService = {
      removeItem: jest.fn()
    } as unknown as jest.Mocked<LocalStorageService>;

    await TestBed.configureTestingModule({
      declarations: [LandingComponent],
      providers: [
        { provide: BankService, useValue: mockBankService },
        { provide: LocalStorageService, useValue: mockLocalStorageService }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LandingComponent);
    component = fixture.componentInstance;
    component.userActive = { dni: '123456789', name: 'John Doe' } as User;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch client data on initialization', () => {
    const response = {
      dni: "123456789",
      name: "John",
      lastName: "Doe",
      address: "XXXXXX",
      phone: "3205221078",
      accounts: []
    };
    mockBankService.getClientByDni.mockReturnValue(of(response));

    component.ngOnInit();

    expect(mockBankService.getClientByDni).toHaveBeenCalledWith('123456789');
    expect(component.client).toEqual(response);
  });

  it('should open modal for account detail', () => {
    const accountNumber = '123456789';
    const response = { 
      accountNumber: "123456789",
      balance: 1050.00,
      status: "ACTIVE",
      transactions: [] 
    };
    mockBankService.getAccount.mockReturnValue(of(response));

    component.openModalAccount(accountNumber);

    expect(mockBankService.getAccount).toHaveBeenCalledWith(accountNumber);
    expect(component.showModalAccount).toBeTruthy();
    expect(component.accountDetail).toEqual(response);
  });

  it('should open modal for transfer', () => {
    const accountNumber = '123456789';

    component.openModalTransfer(accountNumber);

    expect(component.showModalTransfer).toBeTruthy();
    expect(component.transfer.origin).toBe(accountNumber);
  });

  it('should logout user', () => {
    component.logout();

    expect(mockLocalStorageService.removeItem).toHaveBeenCalled();
  });

  it('should handle transfer transaction', () => {
    const response = { status: "SUCCESS" };
    mockBankService.toTransfer.mockReturnValue(of(response));

    component.transfer.destination = '123456789'
    component.transfer.amount = 1200
    component.toTransfer();

    expect(component.showModalTransfer).toBeFalsy();
    expect(component.errMsg).toBe('');
    expect(component.transfer.destination).toBe('');
    expect(component.transfer.amount).toBe(0);
  });

  it('should handle error during transfer transaction', () => {
    mockBankService.toTransfer.mockReturnValue(throwError('Error'));
    component.transfer.destination = '123456789'
    component.transfer.amount = 1200
    component.toTransfer();
    expect(component.errMsg).toBe('No se pudo procesar la transacción, verifique la entrada');
  });
});
