import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ContentComponent } from "../shared/content/content.component";
import { PagesComponent } from "./pages.component";

const routes: Routes = [
  {
    path: 'principal',
    component: PagesComponent,
    children: [
      { path: '', component:ContentComponent }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class PagesRoutingModule {}
