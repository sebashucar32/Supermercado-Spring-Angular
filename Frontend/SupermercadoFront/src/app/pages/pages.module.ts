import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

// Modulos
import { SharedModule } from '../shared/shared.module';
import { AppRoutingModule } from '../app-routing.module';
import { PagesComponent } from './pages.component';

@NgModule({
  declarations: [
    PagesComponent
  ],
  exports: [
    PagesComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    AppRoutingModule
  ]
})

export class PagesModule { }
