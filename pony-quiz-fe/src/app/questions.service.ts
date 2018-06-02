import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Subject} from 'rxjs/Subject';
import {Router} from '@angular/router';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class QuestionsService {

  public data: Subject<any> = new Subject<any>();

  constructor(
    private http: HttpClient,
    private router: Router) {
    this.data.next(null);
  }

  getQuestions() {
    this.http.get('http://localhost:8080/api/questions').subscribe(data => {
      if (data.hasOwnProperty('questions')) {
        this.data.next(data);
      } else {
        this.data.next(null);
      }
    });
  }

  // data for update form
  getQuestionInfo(id: number) {
    return this.http.get('http://localhost:8080/api/question/' + id );
  }

  // data for create form
  getPoniesForQuestion() {
    return this.http.get('http://localhost:8080/api/question/ponies');
  }


  getAllQuestionsIds() {
    return this.http.get('http://localhost:8080/api/questions/ids')
      .toPromise()
      .catch(error => {
        console.log(error);
      })
  }

  // submit answers
  submitAnswers(results: any) {
    console.log('questions service');
    console.log(results);
    return this.http.post('http://localhost:8080/api/submit', results, httpOptions)
      .toPromise()
      .catch(error => {
        console.log('post error');
        console.log(error);
      });
  }

  addQuestion(question: any) {
    this.http.post('http://localhost:8080/api/question', question).subscribe(data => {
      console.log('added', data);
      this.refreshData(data);
      this.router.navigate(['/management/questions']);

    });
  }

  // delete question
  deleteQuestion(id: number) {

    this.http.delete('http://localhost:8080/api/question/' + id).subscribe(data => {
    console.log('deleted', data);
    this.refreshData(data);

    });
  }

  updateQuestion(question: any) {
    this.http.put('http://localhost:8080/api/question', question).subscribe(data => {
      this.refreshData(data);
      console.log('edited', data);
      this.router.navigate(['/management/questions']);
    });
  }

  // refresh all question data
  refreshData(data: any) {
    if (data.hasOwnProperty('questions')) {
      this.data.next(data);
    } else {
      this.data.next(null);
    }
  }
}
