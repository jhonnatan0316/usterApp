import { Component, OnInit } from '@angular/core';
import { TripService } from '../../../services/trip.service';
import { Trip } from '../../../shared/trip';
import { Vehicle } from '../../../shared/vehicle';
import { Driver } from '../../../shared/driver';
import { Router } from '@angular/router';
import { FormControl } from '@angular/forms';
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
    this.vehicles = [];
    this.drivers = [];
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
    this.getAvailableDrivers(this.vehicle.licenseRequired);
  }

  onChangeDrivers(value: Driver) {
    this.driver = value;
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
        this.openSnackBar('Error inesperado, favor comuniquese con el administrador', 'Cerrar');
      });
    } else {
      this.tripSrv.updateTrip(this.trip).subscribe((trip) => {
        this.router.navigate(['/trip']);
        this.openSnackBar('Actualizacón exitosa', 'Cerrar');
      }, (error) => {
        this.openSnackBar('Error inesperado, favor comuniquese con el administrador', 'Cerrar');
      });
    }
  }

  getAvailableVehicles(tripDate: Date) {
    this.vehicles = [];
    this.dateTrip = tripDate;
    const finalDate = this.datepipe.transform(tripDate, 'dd/MM/yyyy');
    this.tripSrv.getAvailableVehicles(finalDate).subscribe(
      result => {
        if (result === null || result.length === 0) {
          this.openSnackBar('No se encontraron vehiculos disponibles', 'Cerrar');
        } else {
          this.vehicles = result;
        }
      },
      (error) => {
        this.openSnackBar('Error inesperado, favor comuniquese con el administrador', 'Cerrar');
      }
    );

  }

  getAvailableDrivers(licenceType: string) {
    this.drivers = [];
    const finalDate = this.datepipe.transform(this.tripDate.value, 'dd/MM/yyyy');
    this.tripSrv.getAvailableDrivers(finalDate, licenceType).subscribe(
      result => {
        console.log('dsad' + result.length)
        if (result === null || result.length === 0) {
          this.openSnackBar('No se encontraron conductores disponibles', 'Cerrar');
        } else {
          this.drivers = result;
        }
      },
      (error) => {
        this.openSnackBar('Error inesperado, favor comuniquese con el administrador', 'Cerrar');
      }
    );

  }

}
