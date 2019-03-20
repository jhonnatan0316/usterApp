import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InitialMenuComponent } from './components/initial-menu/initial-menu.component';
import { ManageVehicleComponent } from './components/vehicle/manage-vehicle/manage-vehicle.component';
import { CreateVehicleComponent } from './components/vehicle/create-vehicle/create-vehicle.component';
import { ManageDriverComponent } from './components/driver/manage-driver/manage-driver.component';
import { CreateDriverComponent } from './components/driver/create-driver/create-driver.component';
import { ManageTripComponent } from './components/trip/manage-trip/manage-trip.component';
import { CreateTripComponent } from './components/trip/create-trip/create-trip.component';

const routes: Routes = [{ path: '', component: InitialMenuComponent },
{ path: 'vehicle', component: ManageVehicleComponent },
{ path: 'vcreate', component: CreateVehicleComponent },
{ path: 'driver', component: ManageDriverComponent },
{ path: 'dcreate', component: CreateDriverComponent },
{ path: 'trip', component: ManageTripComponent },
{ path: 'tcreate', component: CreateTripComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
