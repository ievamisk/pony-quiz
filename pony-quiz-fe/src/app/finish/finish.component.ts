import { Component, OnInit } from '@angular/core';
import {Globals} from "../../globals";
import {QuestionsService} from "../questions.service";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-finish',
  templateUrl: './finish.component.html',
  styleUrls: ['./finish.component.less'],
  providers: [QuestionsService]
})
export class FinishComponent implements OnInit {

  constructor(private global: Globals, private service: QuestionsService,
              private sanitizer: DomSanitizer) {
    this.defaultImgUrl = 'http://www.tiptoncommunications.com/components/com_easyblog/themes/wireframe/images/placeholder-image.png';
    this.name = this.global.name;
    this.answers = this.global.answers;
  }

  defaultImgUrl;
  pony: any;
  answers: any;
  name: string;
  ngOnInit() {
    this.service.submitAnswers(this.answers)
      .then(data => {
        this.pony = data;
        console.log(data);
      });
  }

  sanitizedImage(image?: string) {
    return this.sanitizer.bypassSecurityTrustResourceUrl((image) ? image : this.defaultImgUrl);
  }

  ngOnDestroy() {
    this.service = null;
    this.pony = null;
  }

}
