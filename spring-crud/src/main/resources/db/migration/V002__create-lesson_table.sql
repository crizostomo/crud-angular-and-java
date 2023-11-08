CREATE TABLE lesson (
    course_id BIGINT NOT NULL,
    id BIGINT NOT NULL,
    shareableyoutubeurl VARCHAR(11) NOT NULL,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);
