CREATE TABLE course (
    id BIGINT NOT NULL,
    status VARCHAR(10) NOT NULL,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(200) NOT NULL CHECK (category IN ('BACKEND', 'FRONTEND')),
    PRIMARY KEY (id)
);