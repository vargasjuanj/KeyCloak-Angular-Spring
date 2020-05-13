import { TestBed } from '@angular/core/testing';

import { IsUserGuard } from './is-user.guard';

describe('IsUserGuard', () => {
  let guard: IsUserGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(IsUserGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
