import { Component, OnInit, Output, EventEmitter, ChangeDetectorRef, Pipe, PipeTransform, OnDestroy } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute} from '@angular/router';
import { ManagementService } from '../management.service';
import { PoniesService } from '../ponies.service';
import { QuestionsService } from '../questions.service';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-management-form',
  templateUrl: './management-form.component.html',
  styleUrls: ['./management-form.component.less'],
  providers: [PoniesService, QuestionsService]
})
export class ManagementFormComponent implements OnInit, OnDestroy {

  private questionsIsActive = false;
  private poniesIsActive = false;

  private pony = null;
  private question = null;

  private questions = null;
  private ponies = null;
  private answers = null;
  private defaultImg;
  private defaultImgUrl;

  constructor(
    private route: ActivatedRoute,
    private managementService: ManagementService,
    private poniesService: PoniesService,
    private questionsService: QuestionsService,
    private changeDetectorRef: ChangeDetectorRef,
    private sanitizer: DomSanitizer) {

    this.defaultImgUrl = 'http://www.tiptoncommunications.com/components/com_easyblog/themes/wireframe/images/placeholder-image.png';
  }

  ngOnInit() {
    this.managementService.actionValueChange.subscribe((value) => {
      if (value === 'save') {
        // new pony
        if (this.pony) {
          const newPony = {
            name: this.pony.name,
            description: this.pony.description,
            image: this.pony.image,
            answers: []
          };

          this.pony.questions.forEach((question, index) => {
            newPony.answers.push({
              questionId: question._id,
              answer: question.answer.answer
            });
          });

          // adding new pony
          if (!this.pony.hasOwnProperty('_id')) {
            if (this.poniesService && this.checkPony(newPony)) {
              this.poniesService.addPony(newPony);
            }

          } else if (this.checkPony(newPony)) {
            // updating existing pony
            newPony['_id'] = this.pony._id;
            this.pony.questions.forEach((question, index) => {
              newPony.answers[index]['_id'] = question.answer._id;
            });
            if (this.poniesService) {
              this.poniesService.updatePony(newPony);
            }
          }
        } else if (this.question) {        
          const newQuestion = {
            question: this.question.question,
            image: this.question.image,
            answers: []
          };
          this.question.answerAndPony.forEach((answer, index) => {
            newQuestion.answers.push({
              ponyId: answer._id, //ponyid
              answer: answer.answer
            });
          });
          if (!this.question.hasOwnProperty('_id') && this.checkQuestion(newQuestion)) {
            if (this.questionsService) {
              this.questionsService.addQuestion(newQuestion);
            }
          } else if (this.checkQuestion(newQuestion)) {
            newQuestion['_id'] = this.question._id;
            this.question.answerAndPony.forEach((answer, index) => {
              newQuestion.answers[index]['_id'] = answer._id;
              newQuestion.answers[index]['ponyId'] = answer.ponyId;
            });
            if (this.questionsService) {
              this.questionsService.updateQuestion(newQuestion);
            }
          }
        }
      }
    });

    this.route.parent.params.subscribe( params => {

      // checks which view is active
      if (params.route === 'questions') {
        this.questionsIsActive = true;

      } else {
        this.poniesIsActive = true;
      }
    });

    this.route.params.subscribe( params => {
      this.managementService.setNewValue(params['id']);
      if (this.poniesIsActive && params['id'] !== 'create') {

        // if id is passed then pony edit mode is on
        this.poniesService.getPonyInfo(params['id']).subscribe(data => {
          if (data) {
            this.pony = data;
          }

        this.changeDetectorRef.detectChanges();
        });

      } else if (this.questionsIsActive && params['id'] !== 'create') {
        this.questionsService.getQuestionInfo(params['id']).subscribe(data => {
          if (data) {
            this.question = data;
          }
          this.changeDetectorRef.detectChanges();
        });

      } else if (this.poniesIsActive && params['id'] === 'create' ) {

        // if id not passed then create mode is on
        // to do: create empty object which will represent pony
        this.poniesService.getQuestionsForPony().subscribe(data => {
          this.questions = data['questions'];
          this.pony = {
            name: '',
            description: '',
            image: 'http://www.tiptoncommunications.com/components/com_easyblog/themes/wireframe/images/placeholder-image.png',
            questions: this.questions,
          };

          this.pony.questions.forEach((question, index) => {
            this.pony.questions[index] = {
              _id: question._id,
              question: question.question,
              answer: {
                answer: ''
              }
            };
          });
          this.changeDetectorRef.detectChanges();
        });

      } else if (this.questionsIsActive && params['id'] === 'create') {

        // if id not passed then create mode is on
        // to do: create empty object which will represent question
        this.questionsService.getPoniesForQuestion().subscribe(data => {
          this.ponies = data['ponies'];
          this.question = {
            question: '',
            image: 'http://www.tiptoncommunications.com/components/com_easyblog/themes/wireframe/images/placeholder-image.png',
            answerAndPony: data['ponies']
          };
          this.question.answerAndPony.forEach((pony, index) => {
            this.question.answerAndPony[index] = {
              _id: pony._id,
              ponyName: pony.name,
              answer: ''
            };
          }),
          this.changeDetectorRef.detectChanges();
        });
      }
    });
  }

  sanitizedImage(image?: string) {
    return this.sanitizer.bypassSecurityTrustResourceUrl((image) ? image : this.defaultImgUrl);
  }

  checkPony(pony) {
    for (const property in pony) {
      if (pony.hasOwnProperty(property)) {
        if (pony[property] === ''  && typeof pony[property] !== 'undefined') {
          return false;
        }
      }
    }

    // tslint:disable-next-line:forin
    for (const answer in pony.answers) {
      // tslint:disable-next-line:no-shadowed-variable
      let answer: any;
      for (const answerProperty in answer) {
        if (answer.hasOwnProperty(answer)) {
          if (answer[answerProperty] === ''  && typeof answer[answerProperty] !== 'undefined') {
            return false;
          }
        }
      }
    }
    return true;
  }

  checkQuestion(question) {
    for (const property in question) {
      if (question.hasOwnProperty(property)) {
        if (question[property] === '' && typeof question[property] !== 'undefined') {
          return false;
        }
      }
    }

    // tslint:disable-next-line:forin
    for (const answerAndPony in question.answerAndPony) {
      let answerAndPony: any;
      for (const answerProperty in answerAndPony) {
        if (answerAndPony.hasOwnProperty(answerProperty)) {
          if (answerAndPony[answerProperty] === '' && typeof answerAndPony[answerProperty] !== 'undefined') {
            return false;
          }
         }
      }
    }
    return true;
  }

  ngOnDestroy() {
    this.poniesService = null;
    this.questionsService = null;
  }
}
