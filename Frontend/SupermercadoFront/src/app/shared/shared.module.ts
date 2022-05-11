import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { ContentComponent } from './content/content.component';
import { FooterComponent } from './footer/footer.component';


@NgModule({
  declarations: [
    HeaderComponent,
    SidebarComponent,
    ContentComponent,
    FooterComponent
  ],
  exports: [
    HeaderComponent,
    SidebarComponent,
    ContentComponent,
    FooterComponent
  ],
  imports: [
    CommonModule
  ]
})

export class SharedModule { }
