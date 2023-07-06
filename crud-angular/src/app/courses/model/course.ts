import { Lesson } from "./lesson";

export interface Course {
  _id: string;
  name: string;
  category: string;
  lessons: Lesson[]; // We need 'lessons:?' to run the app even if we have empty lessons
}
