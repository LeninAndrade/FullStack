import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {TabViewModule} from 'primeng/tabview';

import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    TabViewModule,
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
