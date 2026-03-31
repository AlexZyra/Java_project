-- Create database
CREATE DATABASE movie_db;

-- Connect to database
\c movie_db;

-- Create tables (Spring Boot will create them automatically with ddl-auto=update)
-- But you can also create them manually if needed

-- Insert some sample genres
INSERT INTO genres (name) VALUES
                              ('Action'),
                              ('Comedy'),
                              ('Drama'),
                              ('Science Fiction'),
                              ('Horror'),
                              ('Romance');

-- Insert sample movies
INSERT INTO movies (title, year, genre_id) VALUES
                                               ('Inception', 2010, 4),
                                               ('The Dark Knight', 2008, 1),
                                               ('Pulp Fiction', 1994, 3),
                                               ('The Hangover', 2009, 2),
                                               ('The Shining', 1980, 5);

-- Insert sample reviews
INSERT INTO reviews (comment, movie_id) VALUES
                                            ('Amazing movie!', 1),
                                            ('Great visuals and story', 1),
                                            ('Heath Ledger was incredible!', 2),
                                            ('Classic Tarantino!', 3),
                                            ('Funniest movie ever!', 4);