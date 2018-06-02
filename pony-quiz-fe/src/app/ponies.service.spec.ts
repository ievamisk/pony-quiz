import { TestBed, inject } from '@angular/core/testing';

import { PoniesService } from './ponies.service';

describe('PoniesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PoniesService]
    });
  });

  it('should be created', inject([PoniesService], (service: PoniesService) => {
    expect(service).toBeTruthy();
  }));
});
