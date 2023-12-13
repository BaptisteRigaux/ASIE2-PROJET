import { TestBed } from '@angular/core/testing';

import { SericeAuthService } from './serice-auth.service';

describe('SericeAuthService', () => {
  let service: SericeAuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SericeAuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
