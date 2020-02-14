import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Electrics} from '../../list-electrics/list-electrics.component';

@Injectable({
  providedIn: 'root'
})
export class ElectronicsDataService {

  constructor(private http: HttpClient) {
  }

  retrieveElectricsList() {
    return this.http.get<Electrics[]>('http://localhost:8081/electronics/list');
  }

  getElectric(id) {
    return this.http.get<Electrics>(`http://localhost:8081/electronics/get/${id}`);
  }

  deleteElectrics(id) {
    return this.http.delete<Electrics[]>(`http://localhost:8081/electronics/delete/${id}`);
  }

  reportElectrics(id, electrics) {
    return this.http.put(`http://localhost:8081/electronics/report/${id}`, electrics);
  }


}
