import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vehicle } from '../shared/vehicle';
import { AppConstants } from '../Util/AppConstants';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  private baseUrl = AppConstants.baseURL;
  private vehicle = new Vehicle();

  constructor(private http: HttpClient) { }

  getVehicles(): Observable<any> {

    return this.http.get(this.baseUrl + 'vehicle/getvehicles');
  }

  getVehicl(idVehicle: number): Observable<any> {
    return this.http.get(this.baseUrl + 'vehicle/getvehicle/' + idVehicle);
  }

  deleteVehicle(idVehicle: number) {

    return this.http.delete(this.baseUrl + 'vehicle/delvehicle/' + idVehicle);
  }


  createVehicle(vehicle: Vehicle) {

    return this.http.post(this.baseUrl + 'vehicle/crvehicle', vehicle);
  }

  updateVehicle(vehicle: Vehicle) {

    return this.http.put(this.baseUrl + 'vehicle/upvehicle', vehicle);
  }


  setter(vehicle: Vehicle) {
    this.vehicle = vehicle;
  }

  getter() {
    return this.vehicle;
  }
}
