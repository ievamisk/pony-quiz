import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {
  title = 'app';
  private isOn = false;

  constructor () {
  }

  toggle() {
    this.isOn = !this.isOn;
  }

}

particlesJS.load('background', '/assets/json/sparkle.json', () => {
  console.log('particles loaded');
});

