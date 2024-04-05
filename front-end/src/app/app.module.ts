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
import { LoginComponent } from './pages/manage/components/login/login.component';
import { LandingComponent } from './pages/manage/components/landing/landing.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    ManageComponent,
    LoginComponent,
    LandingComponent
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
