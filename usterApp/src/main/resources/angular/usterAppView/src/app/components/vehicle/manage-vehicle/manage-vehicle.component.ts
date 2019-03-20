import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../../../services/vehicle.service';
import { Vehicle } from '../../../shared/vehicle';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-manage-vehicle',
  templateUrl: './manage-vehicle.component.html',
  styleUrls: ['./manage-vehicle.component.css']
})
export class ManageVehicleComponent implements OnInit {

  vehicles: Vehicle[];
  constructor(private vehicleSrv: VehicleService, private snackBar: MatSnackBar, private router: Router) { }

  ngOnInit() {
    this.vehicleSrv.getVehicles().subscribe(
      result => {
        if (result === null) {
          this.openSnackBar('No se encontraron vehiculos', 'Cerrar');
        } else {
          this.vehicles = result;
        }
      }, (error) => {
        this.openSnackBar('Error inesperado, favor comuniquese con el administrador', 'Cerrar');
      }
    );
  }


  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

  deleteVehicle(vehicle) {
    this.vehicleSrv.deleteVehicle(vehicle.idVehicle).subscribe((data) => {
      this.vehicles.splice(this.vehicles.indexOf(vehicle), 1);
      this.openSnackBar('Eliminación exitosa', 'Cerrar');
    }, (error) => {
      this.openSnackBar(error.error.message, 'Cerrar');
    });
  }

  updateVehicle(vehicle) {
    this.vehicleSrv.setter(vehicle);
    this.router.navigate(['/vcreate']);
  }
  createVehicle() {
    const vehicle = new Vehicle();
    this.vehicleSrv.setter(vehicle);
    this.router.navigate(['/vcreate']);

  }

}
