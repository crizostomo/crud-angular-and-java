import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Course } from '../model/course';
import { delay, take, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private readonly API = 'api/courses'; // Modify something in the string to exhibit the error Button | It was /assets/courses.json | The rest of the URL for courses is in proxy.conf.js

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Course[]>(this.API)
    .pipe(
      take(1), // As soon as you get the answer from pipe, you finish the subscription
      delay(300), // Just to show the spinner "<mat-spinner></mat-spinner>" used in courses.component.html
      tap(courses => console.log(courses))
    );;
  }

  loadById(id: string) {
    return this.httpClient.get<Course>(`${this.API}/${id}`);
  }

  save(record: Partial<Course>) {
   // console.log(record);
    if (record._id) {
      //console.log('update');
      return this.update(record);
    }
    //console.log('create');
    return this.create(record);
  }

  private create(record: Partial<Course>) {
    return this.httpClient.post<Course>(this.API, record)
    .pipe(
      take(1)
      );
  }

  private update(record: Partial<Course>) {
    return this.httpClient.put<Course>(`${this.API}/${record._id}`, record)
    .pipe(
      take(1)
      );
  }

  delete(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`)
    .pipe(
      take(1)
      );
  }

}
