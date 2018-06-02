import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ManagementService } from '../management.service';
import { PoniesService } from '../ponies.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule
  ],
  declarations: [],
  providers: [ManagementService, PoniesService]
})
export class ManagementFormModule { }
