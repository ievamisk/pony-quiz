import { Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import {  Router } from '@angular/router';



@Injectable()
export class PoniesService {
  public data: Subject<any> = new Subject<any>();
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(private http: HttpClient, private router: Router) {
    this.data.next(null);
  }
  // for ponies list
  getPonies() {
    this.http.get('http://localhost:8080/api/ponies').subscribe(data => {
      // checks if 'ponies' exists and updates it
      console.log('all ponies', data);
      if (data.hasOwnProperty('ponies')) {
        this.data.next(data['ponies']);
      } else {
        this.data.next(null);
      }
    });
  }

  // data for update form
  getPonyInfo(id: number) {
    return this.http.get('http://localhost:8080/api/question/pony/' + id );
  }

  // data for create form
  getQuestionsForPony() {
    return this.http.get('http://localhost:8080/api/pony/questions');
  }

  // adding new pony
  addPony(pony: any) {
    this.http.post('http://localhost:8080/api/pony', pony).subscribe(data => {
      console.log('added new pony', data);
      this.refreshData(data);
      console.log(data);
      this.router.navigate(['/management/ponies']);
    });
  }

  // deletes pony
  deletePony(id: number) {
    this.http.delete('http://localhost:8080/api/pony/' + id).subscribe(data => {
      console.log ('deleted pony', data);
      this.refreshData(data);
    });
  }

  updatePony(pony: any) {
    this.http.put('http://localhost:8080/api/pony', pony).subscribe(data => {
      this.refreshData(data);
      console.log('edited pony', data);
      this.router.navigate(['/management/ponies']);
    });
  }

  // refresh all ponies data
  refreshData(data: any) {
    if (data.hasOwnProperty('ponies')) {
      this.data.next(data['ponies']);
    } else {
      this.data.next(null);
    }
  }
}
