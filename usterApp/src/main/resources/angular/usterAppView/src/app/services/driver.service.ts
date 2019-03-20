import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Driver } from '../shared/driver';
import { AppConstants } from '../Util/AppConstants';

@Injectable({
  providedIn: 'root'
})
export class DriverService {
  private baseUrl = AppConstants.baseURL;
  private driver = new Driver();

  constructor(private http: HttpClient) { }

  getDrivers(): Observable<any> {

    return this.http.get(this.baseUrl + 'driver/getdrivers');
  }

  getDriver(idDriver: number): Observable<any> {
    return this.http.get(this.baseUrl + 'driver/getdriver/' + idDriver);
  }

  deleteDriver(idDriver: number) {

    return this.http.delete(this.baseUrl + 'driver/deldriver/' + idDriver);
  }


  createDriver(driver: Driver) {

    return this.http.post(this.baseUrl + 'driver/crdriver', driver);
  }

  updateDriver(driver: Driver) {

    return this.http.put(this.baseUrl + 'driver/updriver', driver);
  }


  setter(driver: Driver) {
    this.driver = driver;
  }

  getter() {
    return this.driver;
  }
}
