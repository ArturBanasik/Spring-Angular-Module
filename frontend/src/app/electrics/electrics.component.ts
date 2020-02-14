import {Component, OnInit} from '@angular/core';
import {ElectronicsDataService} from '../service/data/electronics-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Electrics} from '../list-electrics/list-electrics.component';

@Component({
  selector: 'app-electrics',
  templateUrl: './electrics.component.html',
  styleUrls: ['./electrics.component.css']
})
export class ElectricsComponent implements OnInit {
  id: number;
  successfulReport = false;
  electronics: Electrics;

  constructor(
    private electricsService: ElectronicsDataService,
    private route: ActivatedRoute,
    private router: Router,
  ) {
  }

  ngOnInit() {
    this.id = this.route.snapshot.params.id;
    this.electricsService.getElectric(this.id).subscribe(
      data => this.electronics = data);
  }

  reportElectrics() {
    this.electricsService.reportElectrics(this.id, this.electronics).subscribe(
      data => this.successfulReport = true);
    this.router.navigate(['/list']);
  }
}
