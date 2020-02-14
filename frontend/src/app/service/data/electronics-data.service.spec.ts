import {TestBed} from '@angular/core/testing';

import {ElectronicsDataService} from './electronics-data.service';

describe('ElectronicsDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ElectronicsDataService = TestBed.get(ElectronicsDataService);
    expect(service).toBeTruthy();
  });
});
