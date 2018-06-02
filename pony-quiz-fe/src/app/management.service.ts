import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ManagementService {
  private childRouteValue: string;
  public childRouteValueChange: Subject<any> = new Subject<any>();
  public actionValueChange: Subject<String> = new Subject<String>();

  constructor() {
    this.childRouteValue = '';
    this.actionValueChange.next('');
  }

  setNewValue(value: any) {
    this.childRouteValueChange.next(value);
  }

  setNewAction(value: String) {
    this.actionValueChange.next(value);
  }
}
