# Ponies Quiz
This is a quiz application which tells you which pony are you :) It was created by small group of students and I was developing 
front-end and connecting it to the back-end. Only part of front-end which was created by another student was question form functionality (displaying data from back-end and navigating through questions)

## About
* In this application you can:
  - manage all questions and ponies: add new or edit current ones
  - complete quiz and see the answer
* Created using HTML, CSS(LESS), Angular5

## Launching

### Front-end:
* Open project folder `pony-quiz`
* Go to pony-quiz-fe directory `cd pony-quiz-fe`
* Run `npm install` to install Angular
* Run `npm start`

### Back-end:
* Run `mongorestore --db mylittlepony mongo_export/dump/mylittlepony` to import initial data
* Open directory srv which is in folder pony-quiz-be
* Run srv using Tomcat:
  - Configure Tomcat server first:
![tomcat_config](https://user-images.githubusercontent.com/17141606/40879024-81098548-66a2-11e8-90c0-aaf233fce745.png)
![tomcat_config2](https://user-images.githubusercontent.com/17141606/40879023-80e921b8-66a2-11e8-9227-4e6a4e8e3f85.png)
  - Press OK and start the server

## Screenshots

![ponies_start](https://user-images.githubusercontent.com/17141606/40878857-e110ebf0-669f-11e8-84ad-beef7609835f.PNG)
![ponies_quiz](https://user-images.githubusercontent.com/17141606/40878855-e0c97770-669f-11e8-81c3-32a75b2fc669.PNG)
![ponies_quiz_result](https://user-images.githubusercontent.com/17141606/40878856-e0ed7878-669f-11e8-8b6f-84bf9b7e4853.PNG)
![ponies_menu](https://user-images.githubusercontent.com/17141606/40878858-e12a3632-669f-11e8-96aa-06af03da2c67.PNG)
![ponies_list](https://user-images.githubusercontent.com/17141606/40878859-e14a2d2a-669f-11e8-9076-fd7eda80924b.PNG)
![ponies_add](https://user-images.githubusercontent.com/17141606/40878852-e05fe378-669f-11e8-9161-b87fc1e16cb3.PNG)
![ponies_questions_edit](https://user-images.githubusercontent.com/17141606/40878853-e07f8afc-669f-11e8-9f3b-53279daa33bb.PNG)
![ponies_questions_add](https://user-images.githubusercontent.com/17141606/40878854-e0a33b82-669f-11e8-8710-f097d1e0afcd.PNG)
