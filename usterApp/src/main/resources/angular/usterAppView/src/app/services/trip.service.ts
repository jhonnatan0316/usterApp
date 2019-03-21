import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Trip } from '../shared/trip';
import { AppConstants } from '../Util/AppConstants';
import { DataRequest } from '../components/trip/create-trip/create-trip.component';



@Injectable({
  providedIn: 'root'
})
export class TripService {

  private baseUrl = AppConstants.baseURL;
  private trip = new Trip();

  constructor(private http: HttpClient) { }

  getTrips(): Observable<any> {

    return this.http.get(this.baseUrl + 'trip/gettrips');
  }

  getTrip(idTrip: number): Observable<any> {
    return this.http.get(this.baseUrl + 'trip/gettrip' + idTrip);
  }

  deleteTrip(idTrip: number) {

    return this.http.delete(this.baseUrl + 'trip/deltrip' + idTrip);
  }


  createTrip(trip: Trip) {

    return this.http.post(this.baseUrl + 'trip/crtrip', trip);
  }

  updateTrip(trip: Trip) {

    return this.http.put(this.baseUrl + 'trip/uptrip', trip);
  }

  getAvailableVehicles(tripDate: string): Observable<any> {
    return this.http.post(this.baseUrl + 'trip/' + 'getvehicles', tripDate);
  }

  getAvailableDrivers(tripDate: string, licenceType: string): Observable<any> {
    const  parameters: DataRequest = {
      tripDate,
      licenceType
    };

    return this.http.post(this.baseUrl + 'trip/' + 'getdrivers', parameters);
  }
  setter(trip: Trip) {
    this.trip = trip;
  }

  getter() {
    return this.trip;
  }
}
