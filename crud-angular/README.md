# CrudAngular

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 16.0.5.

## Angular material
Go to material.angular.io to check all the documentation available to use

## Updating Angular Version
To update an exisiting Angular version, go to update.angular.io and select the options that apply to your application | "ng update" -> to show the versions to update,
for example "ng update @angular/cli@13.3.3

If you have compiling errors, remove node_modules folder and run:
``
npm i

npm audit fix
``

## Useful Shortcuts used here
ng serve = To start the server
ng g m <NAME> --routing = It Generates a Module, for example, to separate pages. --routing routes the module for us
ng g c <NAME> OR <directory/directory> = It Generates a Component
ng g s <NAME> OR <directory/directory> = It Generates a Service
npm run start = To start the server, but now it uses the proxy.conf.js 

ctrl + shift + p = It shows shortcuts
ctrl + . = It updates the imports

## Extensions used
Thunder Client -> Rest API Client for VS Code, GUI based Http Client; Similar to POSTMAN
Peacock -> Customized the colors of your workspace (CTRL + SHFIFT + P -> Choose Peacock)

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.

## How To's
### The ADD button was not redirecting to a new URL ('http://localhost:4200/courses/new')
- This was the piece of code
``
          <button mat-mini-fab color="accent" aria-label="Add a course">
            <mat-icon (click)="onAdd()">add</mat-icon>
          </button>
``
- After inspecting the element using google chrome tools, it was decided to use this piece of code
``
          <button mat-mini-fab color="accent" aria-label="Add a course" class="mdc-fab mdc-fab--mini mat-mdc-mini-fab mat-accent mat-mdc-button-base" (click)="onAdd()">
            <mat-icon role="img" class="mat-icon notranslate material-icons mat-ligature-font mat-icon-no-color" aria-hidden="true" data-mat-icon-type="font">add</mat-icon>
          </button>
``

### Edit the margin of a card? 
``
mat-card {
  margin: 1em auto;
}
It's inside of courses.component.scss
``
