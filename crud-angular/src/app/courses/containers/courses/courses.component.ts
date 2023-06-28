import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Course } from '../../model/course';
import { CoursesService } from '../../services/courses.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  courses$: Observable <Course[]> | null = null;
  // courses: Course[] = [];

  // coursesService: CoursesService;

  constructor(
    private coursesService: CoursesService,
    public dialog: MatDialog,
    public router: Router,
    public route: ActivatedRoute,
    private snackBar: MatSnackBar
    ) {
    // this.courses = [];
    // this.coursesService = new CoursesService();
    this.refresh();
    // this.coursesService.list().subscribe(courses => this.courses = courses);
  }

  refresh() {
    this.courses$ = this.coursesService.list()
    .pipe(
      catchError(error => {
        this.onError('Error when loading courses!');
        return of([])
      })
    );
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

  onDelete(course: Course) {
    this.coursesService.delete(course._id).subscribe(
      () => {
        this.refresh();
        this.snackBar.open('Course was removed successfully', 'X', {
          duration: 2000,
          verticalPosition: 'top',
          horizontalPosition: 'center'
         });
      },
      error => this.onError('Error when trying to delete course')
    );
  }

}
