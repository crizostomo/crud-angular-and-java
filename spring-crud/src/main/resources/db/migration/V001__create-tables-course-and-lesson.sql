create table course (
  id bigint not null auto_increment,
  name varchar(100) not null,
  category varchar(200) not null,
  status varchar(10) not null,

  primary key (id)
) engine=InnoDB default charset=utf8;

create table lesson (
  id bigint not null auto_increment,
  name varchar(100) not null,
  shareableYoutubeUrl varchar(11) not null,
  course_id bigint not null,

  primary key (id),

  constraint fk_lesson_course foreign key (course_id) references course (id)
) engine=InnoDB default charset=utf8;