import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorComponent } from './error/error.component';
import { PagesComponent } from './pages/pages.component';
import { PagesRoutingModule } from './pages/pages.routing';

const routes: Routes = [
  { path: '', redirectTo: '/principal', pathMatch: 'full' },
  { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    PagesRoutingModule
  ],
  exports: [RouterModule]
})

export class AppRoutingModule { }
