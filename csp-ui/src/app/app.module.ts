import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReportListComponent } from './report-list/report-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatListModule} from "@angular/material/list";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatTableModule} from "@angular/material/table";
import {ReportsService} from "./services/reports.service";
import {HttpClientModule} from "@angular/common/http";
import {ReportResultPipePipe} from "./report-list/report-result.pipe";

@NgModule({
  declarations: [
    AppComponent,
    ReportListComponent,
    ReportResultPipePipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatListModule,
    MatToolbarModule,
    MatTableModule,
    HttpClientModule
  ],
  providers: [
      ReportsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
