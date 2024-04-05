import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManageComponent } from './pages/manage/manage.component';
import { AdminComponent } from './pages/admin/admin.component';

const routes: Routes = [
  { path: '', component: ManageComponent },
  { path: 'admin', component: AdminComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
