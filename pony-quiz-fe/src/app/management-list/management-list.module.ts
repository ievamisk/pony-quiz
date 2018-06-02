import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManagementService } from '../management.service';
import { PoniesService } from '../ponies.service';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [],
  providers: [ManagementService, PoniesService]
})
export class ManagementListModule { }
