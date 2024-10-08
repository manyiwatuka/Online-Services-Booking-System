import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { ClientComponent } from './client.component';
import { ClientDashboardComponent } from './pages/client-dashboard/client-dashboard.component';
import { DemoNgZorroAntdModule } from '../DemoNgZorroAntdModules';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdDetailsComponent } from './pages/ad-details/ad-details.component';
import { MyBookingsComponent } from './pages/my-bookings/my-bookings.component';
import { ReviewComponent } from './pages/review/review.component';


@NgModule({
  declarations: [
    ClientComponent,
    ClientDashboardComponent,
    AdDetailsComponent,
    MyBookingsComponent,
    ReviewComponent
  ],
  imports: [
    CommonModule,
    ClientRoutingModule,
    DemoNgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ClientModule { }
