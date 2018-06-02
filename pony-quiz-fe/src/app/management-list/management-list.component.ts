import { Component, OnInit, Output, EventEmitter, OnDestroy, ChangeDetectionStrategy, ViewRef } from '@angular/core';
import { Input, OnChanges, ChangeDetectorRef } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { ManagementService } from '../management.service';
import { PoniesService } from '../ponies.service';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';
import { QuestionsService } from '../questions.service';

@Component({
  selector: 'app-management-list',
  templateUrl: './management-list.component.html',
  styleUrls: ['./management-list.component.less'],
  changeDetection: ChangeDetectionStrategy.Default,
  providers: [PoniesService, QuestionsService]
})
export class ManagementListComponent implements OnInit, OnChanges, OnDestroy {
  private open: boolean;
  private ponies: any;
  private questions: any;
  private poniesSubscription: Subscription;
  private questionsSubscription: Subscription;
  defaultImgUrl;
  private activeRoute;

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
      this.managementService.setNewValue(null);
      this.route.parent.params.subscribe( params => {
        this.activeRoute = params.route;
        // checks which view is active
        if (params.route === 'questions') {
          this.questionsSubscription = this.questionsService.data.subscribe(data => {
            if (data) {
              this.questions = data['questions'];
            } else {
              this.questions = null;
            }
            this.changeDetectorRef.detectChanges();

          });
          this.refreshQuestions();
        } else {
          this.poniesSubscription = this.poniesService.data.subscribe(data => {
            if (data) {
              this.ponies = data;
            } else {
              this.ponies = null;
            }

            this.changeDetectorRef.detectChanges();
          });
          this.refreshPonies();
        }
      });
  }

  delete(id: number) {
    if (this.activeRoute === 'ponies') {
      this.poniesService.deletePony(id);
    } else if (this.activeRoute === 'questions') {
      this.questionsService.deleteQuestion(id);
    }
  }

  toggleButton() {
    this.open = !this.open;
  }

  refreshPonies() {
    this.poniesService.getPonies();
  }

  sanitizedImage(image?: string) {
    return this.sanitizer.bypassSecurityTrustResourceUrl((image) ? image : this.defaultImgUrl);
  }

  refreshQuestions() {
    this.questionsService.getQuestions();
  }

  ngOnChanges() {
    console.log('changed!', this.ponies);
  }

  ngOnDestroy() {
    if (this.poniesSubscription) {
      this.poniesSubscription.unsubscribe();
      this.poniesSubscription = null;
      this.poniesService = null;
    }

    if (this.questionsSubscription) {
      this.questionsSubscription.unsubscribe();
      this.questionsSubscription = null;
    }
    this.changeDetectorRef.detach();
  }
}
