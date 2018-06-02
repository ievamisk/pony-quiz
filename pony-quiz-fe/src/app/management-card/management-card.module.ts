import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManagementService } from '../management.service';
import { PoniesService } from '../ponies.service';
import { ManagementListComponent } from '../management-list/management-list.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    ManagementListComponent
  ],
  providers: [ManagementService, PoniesService]
})
export class ManagementCardModule { }
