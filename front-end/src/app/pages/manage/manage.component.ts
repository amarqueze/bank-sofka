import { Component } from '@angular/core';
import { User } from '../../models/user.model';
import { LocalStorageService } from '../../services/storage/local-storage.service';

@Component({
  selector: 'app-manage',
  templateUrl: './manage.component.html',
  styleUrl: './manage.component.scss'
})
export class ManageComponent {
  title = 'Bank App';
  userActive!: User;
  hasSession = false;

  constructor(private localStorageService: LocalStorageService) { }

  ngOnInit(): void {
    this.userActive = this.localStorageService.restoreItem("activeUser");
    this.hasSession = !!this.userActive;
  }

  changeToDashboard(userActive: User) {
    this.localStorageService.saveItem("activeUser", userActive);
    this.userActive = userActive;
    this.hasSession = true;
  }
}
