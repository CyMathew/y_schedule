import { TestBed, inject } from '@angular/core/testing';

import { GetUrlService } from './get-url.service';

describe('GetUrlService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GetUrlService]
    });
  });

  it('should be created', inject([GetUrlService], (service: GetUrlService) => {
    expect(service).toBeTruthy();
  }));
});
