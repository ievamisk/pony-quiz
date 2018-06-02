import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {QuestionFormComponent} from './question-form/question-form.component';
import {ProgressBarComponent} from './progress-bar/progress-bar.component';
import {CardComponent} from './card/card.component';
import {StartFormComponent} from './start-form/start-form.component';
import {FinishComponent} from './finish/finish.component';
import {ManagementMenuComponent} from './management-menu/management-menu.component';
import {ManagementCardComponent} from './management-card/management-card.component';
import {ManagementListComponent} from './management-list/management-list.component';
import {ManagementFormComponent} from './management-form/management-form.component';
import {ManagementService} from './management.service';
import {HttpModule} from '@angular/http';
import {PoniesService} from './ponies.service';
import {Globals} from "../globals";

const routes: Routes = [
  {
    path: 'quiz',
    component: CardComponent,
    children: [
      {path: '', component: StartFormComponent},
      {path: 'questions/:id', component: QuestionFormComponent},
      {path: 'result', component: FinishComponent}
    ]
  },
  {
    path: 'management',
    component: ManagementMenuComponent,
    data: { title: 'Management Menu' }
  },
  {
    path: 'management/:route',
    component: ManagementCardComponent,
    children: [
      {path: '', component: ManagementListComponent},
      {path: ':id', component: ManagementFormComponent},
      {path: 'create', component: ManagementFormComponent}
    ]
  },
  { path: '',
    redirectTo: 'quiz',
    pathMatch: 'full'
  }
  // { path: '**', component: PageNotFoundComponent }
];


@NgModule({
  declarations: [
    AppComponent,
    QuestionFormComponent,
    ProgressBarComponent,
    CardComponent,
    StartFormComponent,
    FinishComponent,
    ManagementMenuComponent,
    ManagementCardComponent,
    ManagementListComponent,
    ManagementFormComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(
      routes,
      { enableTracing: false }
    ),
    HttpClientModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [ManagementService, PoniesService, Globals],
  bootstrap: [AppComponent]
})

export class AppModule { }
