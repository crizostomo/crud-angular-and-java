import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Course } from '../../model/course';
import { CoursesService } from '../../services/courses.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  courses$: Observable <Course[]>;
  // courses: Course[] = [];

  // coursesService: CoursesService;

  constructor(
    private coursesService: CoursesService,
    public dialog: MatDialog,
    public router: Router,
    public route: ActivatedRoute
    ) {
    // this.courses = [];
    // this.coursesService = new CoursesService();
    this.courses$ = this.coursesService.list()
    .pipe(
      catchError(error => {
        this.onError('Error when loading courses!');
        return of([])
      })
    );

    // this.coursesService.list().subscribe(courses => this.courses = courses);
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  ngOnInit(): void {

  }

  onAdd() {
    // Used for the buttons showed in the courses.component.html
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  onEdit(course: Course) {
    this.router.navigate(['edit', course._id], {relativeTo: this.route}); // Route = edit
  }

}
