import { Component, OnInit } from '@angular/core';
import { TripService } from '../../../services/trip.service';
import { Trip } from '../../../shared/trip';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-manage-trip',
  templateUrl: './manage-trip.component.html',
  styleUrls: ['./manage-trip.component.css']
})
export class ManageTripComponent implements OnInit {

  trips: Trip[];
  cols: any[];

  constructor(private snackBar: MatSnackBar, private tripSrv: TripService, private router: Router) { }

  ngOnInit() {


    this.tripSrv.getTrips().subscribe(
      result => {
        if (result === null) {
          this.openSnackBar('No se encontraron viajes', 'Cerrar');
        } else {
          this.trips = result;

          this.cols = [
            { field: 'idTrip', header: 'Identificador Viaje' },
            { field: 'tripDate', header: 'Marca' },
            { field: 'trip.driver', header: 'Modelo' },
            { field: 'trip.vehicle', header: 'Placa' }];
          console.log(this.trips);
        }
      },
      (error) => {
        this.openSnackBar('Error inesperado, favor comuniquese con el administrador', 'Cerrar');
      }
    );
  }


  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

  deleteTrip(trip) {
    this.tripSrv.deleteTrip(trip.idTrip).subscribe((data) => {
      this.trips.splice(this.trips.indexOf(trip), 1);
      this.openSnackBar('Eliminación exitosa', 'Cerrar');
    }, (error) => {
      console.log(error as any);
    });
  }

  updateTrip(trip) {
    this.tripSrv.setter(trip);
    this.router.navigate(['/tcreate']);
  }
  createTrip() {
    const trip = new Trip();
    this.tripSrv.setter(trip);
    this.router.navigate(['/tcreate']);

  }

}
