import { Component, OnInit } from '@angular/core';
import { Driver } from 'src/app/shared/driver';
import { Router } from '@angular/router';
import { DriverService } from '../../../services/driver.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-create-driver',
  templateUrl: './create-driver.component.html',
  styleUrls: ['./create-driver.component.css']
})
export class CreateDriverComponent implements OnInit {
  private driver: Driver;
  constructor(private driverSrv: DriverService, private router: Router, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.driver = this.driverSrv.getter();
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

  handleForm() {
    this.driver.license = this.driver.license.toUpperCase();
    if (this.driver.idDriver === undefined || this.driver.idDriver === null) {
      this.driverSrv.createDriver(this.driver).subscribe((driver) => {
        this.router.navigate(['/driver']);
        this.openSnackBar('Creación exitosa', 'Cerrar');
      }, (error) => {
        this.openSnackBar('Error inesperado, favor comuniquese con el administrador', 'Cerrar');
      });
    } else {
      this.driverSrv.updateDriver(this.driver).subscribe((driver) => {
        this.router.navigate(['/driver']);
        this.openSnackBar('Actualizacón exitosa', 'Cerrar');
      }, (error) => {
        this.openSnackBar('Error inesperado, favor comuniquese con el administrador', 'Cerrar');
      });
    }
  }

}
