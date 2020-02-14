import {Component, OnInit} from '@angular/core';
import {ElectronicsDataService} from '../service/data/electronics-data.service';
import {Router} from '@angular/router';

export class Electrics {
  constructor(
    public id: number,
    public tag: string,
    public category: string,
    public broken: boolean,
    public comment: string,
    public details: string
  ) {
  }
}

@Component({
  selector: 'app-list-electrics',
  templateUrl: './list-electrics.component.html',
  styleUrls: ['./list-electrics.component.css']
})
export class ListElectricsComponent implements OnInit {
  electricsList: Electrics[];
  deletedMessage: string;

  constructor(
    private electricsListService: ElectronicsDataService,
    private router: Router) {
  }

  ngOnInit() {
    this.loadList();
  }

  loadList() {
    this.electricsListService.retrieveElectricsList().subscribe(
      response => {
        this.electricsList = response;
      }
    );
  }

  deleteElectrics(electrics) {
    this.electricsListService.deleteElectrics(electrics.id).subscribe(
      response => {
        this.deletedMessage = `Item: ${electrics.tag}. Succesfully deleted!`;
        this.loadList();
      }
    );
  }

  editElectrics(electrics) {
    this.router.navigate(['electrics', electrics.id]);
  }
}

