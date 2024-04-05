import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; 
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BankService } from './services/bank/bank.service';
import { AdminComponent } from './pages/admin/admin.component';
import { ManageComponent } from './pages/manage/manage.component';
import { LocalStorageService } from './services/storage/local-storage.service';

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    ManageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [BankService, LocalStorageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
