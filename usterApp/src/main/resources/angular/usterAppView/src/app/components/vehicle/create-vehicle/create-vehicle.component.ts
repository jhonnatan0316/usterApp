import { Component, OnInit } from '@angular/core';
import { Vehicle } from 'src/app/shared/vehicle';
import { Router } from '@angular/router';
import { VehicleService } from '../../../services/vehicle.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-create-vehicle',
  templateUrl: './create-vehicle.component.html',
  styleUrls: ['./create-vehicle.component.css']
})
export class CreateVehicleComponent implements OnInit {

  private vehicle: Vehicle;
  constructor(private vehicleSrv: VehicleService, private router: Router, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.vehicle = this.vehicleSrv.getter();
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

  handleForm() {
    this.vehicle.licenseRequired = this.vehicle.licenseRequired.toUpperCase();
    if (this.vehicle.idVehicle === undefined || this.vehicle.idVehicle === null) {
      this.vehicleSrv.createVehicle(this.vehicle).subscribe((vehicle) => {
        this.router.navigate(['/vehicle']);
        this.openSnackBar('Creación exitosa', 'Cerar');
      }, (error) => {
        this.openSnackBar('Error inesperado, favor comuniquese con el administrador', 'Cerrar');
      });
    } else {
      this.vehicleSrv.updateVehicle(this.vehicle).subscribe((vehicle) => {
        this.router.navigate(['/vehicle']);
        this.openSnackBar('Actualizacón exitosa', 'Cerar');
      }, (error) => {
        this.openSnackBar('Error inesperado, favor comuniquese con el administrador', 'Cerrar');
      });
    }
  }

}
