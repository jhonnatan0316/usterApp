import { Component, OnInit } from '@angular/core';
import { DriverService } from '../../../services/driver.service';
import { Driver } from '../../../shared/driver';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-manage-driver',
  templateUrl: './manage-driver.component.html',
  styleUrls: ['./manage-driver.component.css']
})
export class ManageDriverComponent implements OnInit {
  drivers: Driver[];
  cols: any[];

  constructor(
    private snackBar: MatSnackBar,
    private driverSrv: DriverService,
    private router: Router) { }

  ngOnInit() {


    this.driverSrv.getDrivers().subscribe(
      result => {
        if (result === null) {
          this.openSnackBar('No se encontraron conductores', 'Cerrar');
        } else {
          this.drivers = result;
          this.cols = [
            { field: 'idDriver', header: 'Identificador' },
            { field: 'nombre', header: 'Nombre' },
            { field: 'surname', header: 'Apellido' },
            { field: 'license', header: 'Licencia' }
          ];
          console.log(this.drivers);
        }
      },
      (error) => {
        this.openSnackBar('Error inesperado, favor cominiquese con el administrador', 'Cerrar');
      }
    );
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

  deleteDriver(driver) {
    this.driverSrv.deleteDriver(driver.idDriver).subscribe((data) => {
      this.drivers.splice(this.drivers.indexOf(driver), 1);
      this.openSnackBar('Eliminación exitosa', 'Cerrar');
    }, (error) => {
      this.openSnackBar(error.error.message, 'Cerrar');
    });
  }

  updateDriver(driver) {
    this.driverSrv.setter(driver);
    this.router.navigate(['/dcreate']);
  }

  createDriver() {
    const driver = new Driver();
    this.driverSrv.setter(driver);
    this.router.navigate(['/dcreate']);

  }

}
