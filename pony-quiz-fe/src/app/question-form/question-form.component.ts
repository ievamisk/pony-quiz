import {Component, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {QuestionsService} from "../questions.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Globals} from "../../globals";
import {DomSanitizer} from "@angular/platform-browser";


@Component({
  selector: 'app-question-form',
  templateUrl: './question-form.component.html',
  styleUrls: ['./question-form.component.less'],
  providers: [QuestionsService]
})
export class QuestionFormComponent implements OnInit {

  constructor(private location: Location, private service: QuestionsService,
              private router: ActivatedRoute, private route: Router,
              private global: Globals, private sanitizer: DomSanitizer) {
    this.defaultImgUrl = 'http://www.tiptoncommunications.com/components/com_easyblog/themes/wireframe/images/placeholder-image.png';
  }
  quizForm : FormGroup;
  questions;
  defaultImgUrl;
  private questionID = null;

  ngOnInit() {
    this.quizForm = new FormGroup({
      'answer': new FormControl('',Validators.required)
    });
    this.quizForm.get('answer').markAsUntouched();
    this.router.params.subscribe(params =>{
      this.service.getQuestionInfo(this.global.questions.questionsIds[parseInt(params['id']) - 1]._id).subscribe(data => {
        this.questionID = params['id'];
        this.questions = data;
      });
    });
  }

  back() {
    this.location.back();
  }
  next() {
    this.global.answers.results[parseInt(this.questionID) - 1] = {answerId: this.quizForm.get('answer').value};
    console.log(this.global.answers);
    if (parseInt(this.questionID) != this.global.questionsCount) {
    this.route.navigate(['quiz/questions', parseInt(this.questionID) + 1]);
    } else {
      this.route.navigate(['quiz/result']);
    }
  }

  sanitizedImage(image?: string) {
    return this.sanitizer.bypassSecurityTrustResourceUrl((image) ? image : this.defaultImgUrl);
  }

  onValueChange() {
  }

}
