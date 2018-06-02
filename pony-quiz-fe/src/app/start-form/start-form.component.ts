import {Component, OnInit} from '@angular/core';
import {Globals} from "../../globals";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {QuestionsService} from "../questions.service";

@Component({
  selector: 'app-start-form',
  templateUrl: './start-form.component.html',
  styleUrls: ['./start-form.component.less'],
  providers: [QuestionsService]
})
export class StartFormComponent implements OnInit {

  nameForm: FormGroup;
  name: string;
  payload;

  constructor(private global: Globals, private service: QuestionsService) {
  }

  ngOnInit() {
    this.nameForm = new FormGroup({
      'name': new FormControl('',Validators.required)
    });
    this.global.answers = {
      results: []
    };
    this.service.getAllQuestionsIds()
      .then(data => {
        this.payload = data;
        this.global.questions = data;
        this.global.questionsCount = this.payload.questionsIds.length
      })
  }

  save() {
    this.global.name = this.name;
  }

}
