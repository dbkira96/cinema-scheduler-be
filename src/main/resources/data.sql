CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

INSERT INTO users (username, password, enabled) VALUES ('user', '$2a$08$Wgg60iqG/rFDkcoix0E9.uV4jW/mYctaSZKjAEd5i0smRwXGtI4x2', TRUE);
INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$08$BULfALRf5G8hxGWq5uVJpuDxKWod53l9rxQrxlYv1zD0Q9rPU5M2.', TRUE);

INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO movie (id, title, duration, release_date, genre, director, image) VALUES (1, 'Evangelion: 3.0+1.0', 96, '2021-03-08', 'Action', 'Hideaki Anno', 'https://via.placeholder.com/150?text=Evangelion');
INSERT INTO movie (id, title, duration, release_date, genre, director, image) VALUES (2, 'Perfect Blue', 81, '1997-02-28', 'Psychological Thriller', 'Satoshi Kon', 'https://via.placeholder.com/150?text=Perfect+Blue');
INSERT INTO movie (id, title, duration, release_date, genre, director, image) VALUES (3, 'Akira', 124, '1988-07-16', 'Sci-Fi', 'Katsuhiro Otomo', 'https://via.placeholder.com/150?text=Akira');
INSERT INTO movie (id, title, duration, release_date, genre, director, image) VALUES (4, 'Your Name', 106, '2016-08-26', 'Romance', 'Makoto Shinkai', 'https://via.placeholder.com/150?text=Your+Name');
INSERT INTO movie (id, title, duration, release_date, genre, director, image) VALUES (5, 'Spirited Away', 125, '2001-07-20', 'Fantasy', 'Hayao Miyazaki', 'https://via.placeholder.com/150?text=Spirited+Away');
INSERT INTO movie (id, title, duration, release_date, genre, director, image) VALUES (6, 'Ghost in the Shell', 83, '1995-11-18', 'Cyberpunk', 'Mamoru Oshii', 'https://via.placeholder.com/150?text=Ghost+in+the+Shell');

INSERT INTO cinema_room (id, name, capacity, technology) VALUES (1, 'Room 1', 100, 'STANDARD');
INSERT INTO cinema_room (id, name, capacity, technology) VALUES (2, 'Room 2', 80, 'IMAX');
INSERT INTO cinema_room (id, name, capacity, technology) VALUES (3, 'Room 3', 60, 'STANDARD');
INSERT INTO cinema_room (id, name, capacity, technology) VALUES (4, 'Room 4', 120, 'STANDARD');


-- Evangelion: 3.0+1.0 in Room 1 e Room 2, 2 settimane, orari diversi
INSERT INTO schedule (id, movie_id, room_id, start_date, end_date, time) VALUES (1, 1, 1, '2025-09-13', '2025-09-27', '18:30:00');
INSERT INTO schedule (id, movie_id, room_id, start_date, end_date, time) VALUES (2, 1, 1, '2025-09-13', '2025-09-27', '20:30:00');
INSERT INTO schedule (id, movie_id, room_id, start_date, end_date, time) VALUES (3, 1, 2, '2025-09-14', '2025-09-28', '19:00:00');
INSERT INTO schedule (id, movie_id, room_id, start_date, end_date, time) VALUES (4, 1, 2, '2025-09-14', '2025-09-28', '21:00:00');

-- Perfect Blue in Room 2 e Room 3, 1 settimana, orari diversi
INSERT INTO schedule (id, movie_id, room_id, start_date, end_date, time) VALUES (5, 2, 2, '2025-09-13', '2025-09-20', '20:00:00');
INSERT INTO schedule (id, movie_id, room_id, start_date, end_date, time) VALUES (6, 2, 3, '2025-09-15', '2025-09-22', '18:00:00');

-- Akira in Room 3 e Room 4, 3 settimane, orari diversi
INSERT INTO schedule (id, movie_id, room_id, start_date, end_date, time) VALUES (7, 3, 3, '2025-09-14', '2025-10-05', '21:00:00');
INSERT INTO schedule (id, movie_id, room_id, start_date, end_date, time) VALUES (8, 3, 4, '2025-09-16', '2025-10-06', '19:30:00');

-- Your Name in Room 1 e Room 4, 2 settimane, orari diversi
INSERT INTO schedule (id, movie_id, room_id, start_date, end_date, time) VALUES (9, 4, 1, '2025-09-15', '2025-09-29', '17:00:00');
INSERT INTO schedule (id, movie_id, room_id, start_date, end_date, time) VALUES (10, 4, 4, '2025-09-17', '2025-10-01', '20:00:00');

-- Spirited Away in Room 4 e Room 2, 1 settimana, orari diversi
INSERT INTO schedule (id, movie_id, room_id, start_date, end_date, time) VALUES (11, 5, 4, '2025-09-15', '2025-09-22', '19:30:00');
INSERT INTO schedule (id, movie_id, room_id, start_date, end_date, time) VALUES (12, 5, 2, '2025-09-18', '2025-09-25', '21:30:00');

-- Ghost in the Shell in Room 2 e Room 3, 3 settimane, orari diversi
INSERT INTO schedule (id, movie_id, room_id, start_date, end_date, time) VALUES (13, 6, 2, '2025-09-16', '2025-10-06', '22:00:00');
INSERT INTO schedule (id, movie_id, room_id, start_date, end_date, time) VALUES (14, 6, 3, '2025-09-19', '2025-10-09', '20:00:00');

ALTER TABLE movie ALTER COLUMN id RESTART WITH 7;
ALTER TABLE cinema_room ALTER COLUMN id RESTART WITH 5;
ALTER TABLE schedule ALTER COLUMN id RESTART WITH 15;


