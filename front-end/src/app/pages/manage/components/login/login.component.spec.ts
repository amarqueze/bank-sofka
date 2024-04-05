import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { BankService } from '../../../../services/bank/bank.service';
import { of, throwError } from 'rxjs';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let mockBankService: jest.Mocked<BankService>;

  beforeEach(async () => {
    mockBankService = {
      authenticate: jest.fn()
    } as unknown as jest.Mocked<BankService>;

    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      providers: [{ provide: BankService, useValue: mockBankService }]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should emit authentication success event', () => {
    const response = { dni: '123456789', name: 'John Doe' };
    mockBankService.authenticate.mockReturnValue(of(response));

    const emitSpy = jest.spyOn(component.authenticationSuccess, 'emit');
    component.auth.nickname = 'testuser';
    component.auth.password = 'password';
    component.authenticate();

    expect(mockBankService.authenticate).toHaveBeenCalledWith('testuser', 'password');
    expect(emitSpy).toHaveBeenCalledWith(response);
    expect(component.errMsg).toBe('');
  });

  it('should handle 401 error', () => {
    const error = { status: 401 };
    mockBankService.authenticate.mockReturnValue(throwError(error));

    component.authenticate();

    expect(mockBankService.authenticate).toHaveBeenCalled();
    expect(component.errMsg).toBe('Usuario o contraseña son inválidos');
  });

  it('should handle generic error', () => {
    const error = { status: 500 };
    mockBankService.authenticate.mockReturnValue(throwError(error));

    component.authenticate();

    expect(mockBankService.authenticate).toHaveBeenCalled();
    expect(component.errMsg).toBe('Fallo la autenticación');
  });
});
