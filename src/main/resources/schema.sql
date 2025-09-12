CREATE TABLE movie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    duration INT NOT NULL,
    release_date DATE,
    genre VARCHAR(100),
    director VARCHAR(100),
    image VARCHAR(255),
    create_user VARCHAR(100),
    update_user VARCHAR(100),
    create_date TIMESTAMP,
    update_date TIMESTAMP
);

CREATE TABLE cinema_room (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    capacity INT NOT NULL,
    technology VARCHAR(20)
);

CREATE TABLE schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    start_date DATE,
    end_date DATE,
    time TIME,
    create_user VARCHAR(100),
    update_user VARCHAR(100),
    create_date TIMESTAMP,
    update_date TIMESTAMP,
    CONSTRAINT fk_schedule_movie FOREIGN KEY (movie_id) REFERENCES movie(id),
    CONSTRAINT fk_schedule_room FOREIGN KEY (room_id) REFERENCES cinema_room(id)
);

CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username)
);


