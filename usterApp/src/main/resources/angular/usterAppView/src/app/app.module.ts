import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {MessagesModule} from 'primeng/primeng';
import {ConfirmationService} from 'primeng/api';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSelectModule} from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';
import {MatTableModule} from '@angular/material/table';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatButtonModule} from '@angular/material/button';
import { DatePipe } from '@angular/common';

import { ManageVehicleComponent } from './components/vehicle/manage-vehicle/manage-vehicle.component';
import { CreateVehicleComponent } from './components/vehicle/create-vehicle/create-vehicle.component';
import { ManageDriverComponent } from './components/driver/manage-driver/manage-driver.component';
import { CreateDriverComponent } from './components/driver/create-driver/create-driver.component';
import { ManageTripComponent } from './components/trip/manage-trip/manage-trip.component';
import { CreateTripComponent } from './components/trip/create-trip/create-trip.component';
import { InitialMenuComponent } from './components/initial-menu/initial-menu.component';
import { VehicleService } from './services/vehicle.service';
import { DriverService } from './services/driver.service';
import { TripService } from './services/trip.service';


@NgModule({
  declarations: [
    AppComponent,
    ManageVehicleComponent,
    ManageDriverComponent,
    ManageTripComponent,
    CreateVehicleComponent,
    CreateDriverComponent,
    CreateTripComponent,
    InitialMenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ConfirmDialogModule,
    MessagesModule,
    FormsModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatSnackBarModule,
    MatSelectModule,
    MatTableModule,
    MatButtonModule,
    HttpClientModule
  ],
  providers: [ VehicleService, DriverService, TripService, ConfirmationService, DatePipe ],
  bootstrap: [AppComponent]
})
export class AppModule { }
