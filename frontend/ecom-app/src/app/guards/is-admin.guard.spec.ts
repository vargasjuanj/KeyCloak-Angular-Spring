import { TestBed } from '@angular/core/testing';

import { IsAdminGuard } from './is-admin.guard';

describe('IsAdminGuard', () => {
  let guard: IsAdminGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(IsAdminGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
