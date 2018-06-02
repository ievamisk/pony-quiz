import { Component, OnInit, Input, ChangeDetectorRef, ChangeDetectionStrategy, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import { ManagementService } from '../management.service';
import { Location } from '@angular/common';
import { ISubscription, Subscription } from 'rxjs/Subscription';
import { PoniesService } from '../ponies.service';
import { ManagementFormComponent } from '../management-form/management-form.component';

@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  selector: 'app-management-card',
  templateUrl: './management-card.component.html',
  styleUrls: ['./management-card.component.less']
})
export class ManagementCardComponent implements OnInit, OnDestroy {
  private routeName: string;
  private buttonsIsActive = false;
  private subscription: ISubscription;

  constructor(
    private route: ActivatedRoute,
    private service: ManagementService,
    private changeDetectorRef: ChangeDetectorRef,
    private location: Location,
    private router: Router) {

    this.route.params.subscribe( params => {
      this.routeName = params['route'];
    });

    this.subscription = this.service.childRouteValueChange.subscribe(value => {
      // tslint:disable-next-line:radix
      if (value === 'create' || parseInt(value)) {
        this.buttonsIsActive = true;
      } else {
        this.buttonsIsActive = false;
      }
      this.changeDetectorRef.detectChanges();
    });
  }

  ngOnInit() {
  }

  // goes back to previous window
  cancel() {
    if (this.routeName === 'ponies') {
      this.router.navigate(['/management/ponies']);
    } else {
      this.router.navigate(['/management/questions']);
    }
  }

  back() {
    this.router.navigate(['/management']);
  }

  save() {
    this.service.actionValueChange.next('save');
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
