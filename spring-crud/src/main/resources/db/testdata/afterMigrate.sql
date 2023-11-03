delete from lesson;
delete from course;

insert into course (id, name, category, status) values (1, 'Angular with Spring', 'Front-End', 'Active');

insert into lesson (id, name, shareableYoutubeUrl, course_id) values (1, 'Introduction', 'dFg4yHju81', 1);
insert into lesson (id, name, shareableYoutubeUrl, course_id) values (2, 'Introduction', 'dFg4yHju82', 1);

unlock tables;

