import { Component, OnInit } from '@angular/core';
import { TripService } from '../../../services/trip.service';
import { Trip } from '../../../shared/trip';
import { Vehicle } from '../../../shared/vehicle';
import { Driver } from '../../../shared/driver';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs';
import { tap } from 'rxjs/operators';
import { DatePipe } from '@angular/common';
import { MatSnackBar } from '@angular/material';

export interface DataRequest {
  tripDate: string;
  licenceType: string;
}

@Component({
  selector: 'app-create-trip',
  templateUrl: './create-trip.component.html',
  styleUrls: ['./create-trip.component.css']
})


export class CreateTripComponent implements OnInit {

  trip: Trip;
  driver: Driver;
  vehicle: Vehicle;
  vehicles: Vehicle[];
  drivers: Driver[];
  tripDate = new FormControl('');
  dateTrip: Date;


  tripObservable: Subscription;
  vehicleObservable: Subscription;

  constructor(private snackBar: MatSnackBar, private tripSrv: TripService, private router: Router, public datepipe: DatePipe) { }

  ngOnInit() {

    this.trip = this.tripSrv.getter();
    this.tripObservable = this.tripDate.valueChanges.pipe(
      tap(date => {
        this.getAvailableVehicles(date);
      }
      )).subscribe();

  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

  onChangeVehicles(value: Vehicle) {
    this.vehicle = value;
    console.log('Infovehiculo' + this.vehicle.brand);
    this.getAvailableDrivers(this.vehicle.licenseRequired);
  }

  onChangeDrivers(value: Driver) {
    this.driver = value;
    console.log('info driver' + this.driver.surname);
  }

  handleForm() {
    if (this.trip.idTrip === undefined || this.trip.idTrip === null) {
      this.trip.idVehicle = this.vehicle;
      this.trip.tripDate = this.dateTrip;
      this.trip.idDriver = this.driver;
      this.tripSrv.createTrip(this.trip).subscribe((trip) => {
        this.router.navigate(['/trip']);
        this.openSnackBar('Creación exitosa', 'Cerar');
      }, (error) => {
        this.openSnackBar('Error inesperado, favor comuniquese con el administrador', 'Cerar');
      });
    } else {
      this.tripSrv.updateTrip(this.trip).subscribe((trip) => {
        this.router.navigate(['/trip']);
        this.openSnackBar('Actualizacón exitosa', 'Cerar');
      }, (error) => {
        this.openSnackBar('Error inesperado, favor comuniquese con el administrador', 'Cerar');
      });
    }
  }

  getAvailableVehicles(tripDate: Date) {
    this.dateTrip = tripDate;
    console.log('almaceno la fecha: ' + this.dateTrip);
    const finalDate = this.datepipe.transform(tripDate, 'dd/MM/yyyy');
    this.tripSrv.getAvailableVehicles(finalDate).subscribe(
      result => {
        if (result === null) {
          console.log('No seencontro info');
        } else {
          this.vehicles = result;
        }
      },
      (error) => {
        console.log(error as any);
      }
    );

  }

  getAvailableDrivers(licenceType: string) {
    const finalDate = this.datepipe.transform(this.tripDate.value, 'dd/MM/yyyy');
    console.log('la fecha es' + finalDate + 'lic: ' + licenceType);
    this.tripSrv.getAvailableDrivers(finalDate, licenceType).subscribe(
      result => {
        if (result === null) {
          console.log('No seencontro info');
        } else {
          this.drivers = result;
        }
      },
      (error) => {
        console.log(error as any);
      }
    );

  }

}
