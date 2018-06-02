import { Injectable } from '@angular/core';

@Injectable()
export class Globals {
  name: string = '';
  answers = {
    results: []
  };
  questionsCount;
  questions;
}
