import { TestBed } from '@angular/core/testing';
import { LocalStorageService } from './local-storage.service';

describe('LocalStorageService', () => {
  let service: LocalStorageService;

  const storageMock = {
    set: jest.fn(),
    remove: jest.fn(),
    get: jest.fn()
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        LocalStorageService,
        { provide: 'LOCAL_STORAGE', useValue: storageMock }
      ]
    });
    service = TestBed.inject(LocalStorageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should save item to local storage', () => {
    const key = 'testKey';
    const value = 'testValue';

    service.saveItem(key, value);
    expect(service).toBeTruthy();
  });

  it('should remove item from local storage', () => {
    const key = 'testKey';

    service.removeItem(key);
    expect(service).toBeTruthy();
  });

  it('should restore item from local storage', () => {
    const key = 'testKey';

    service.restoreItem(key);
    expect(service).toBeTruthy();
  });
});