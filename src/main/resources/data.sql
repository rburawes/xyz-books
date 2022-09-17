-- Authors
insert into authors (first_name, last_name, date_created, date_updated)
values ('Ryan', 'Holiday', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into authors (first_name, last_name, date_created, date_updated)
values ('Martin', 'Odersky', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into authors (first_name, last_name, date_created, date_updated)
values ('Lex', 'Spoon', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into authors (first_name, last_name, date_created, date_updated)
values ('Bill', 'Venners', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Publishers
insert into publishers (name, date_created, date_updated)
values ('Portfolio - Penguin Group', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into publishers (name, date_created, date_updated)
values ('Artima Inc', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Books
insert into books (title, isbn, publisher_id, price, publication_year, image_url, edition, date_created, date_updated)
values ('Ego Is The Enemy', '9781591847816', 1, 15.94, '2016', 'https://images-na.ssl-images-amazon.com/images/I/41Lq9V+gtHL._SX352_BO1,204,203,200_.jpg', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into books (title, isbn, publisher_id, price, publication_year, image_url, edition, date_created, date_updated)
values ('The Obstacle Is the Way: The Timeless Art of Turning Trials into Triumph', '9781591846352', 1, 15.94, '2014', 'https://images-na.ssl-images-amazon.com/images/I/41BRXkq6D8L._SX352_BO1,204,203,200_.jpg', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into books (title, isbn, publisher_id, price, publication_year, image_url, edition, date_created, date_updated)
values ('Programming in Scala', '9780981531618', 2, 48.88, '2019', 'https://images-na.ssl-images-amazon.com/images/I/415cpVSxVmL._SX377_BO1,204,203,200_.jpg', '4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Books_Authors (many-to-many)
insert into books_authors(books_id, authors_id) values (1, 1); -- Ryan Holiday - Ego Is The Enemy
insert into books_authors(books_id, authors_id) values (2, 1); -- Ryan Holiday - The Obstacle Is the Way: The Timeless Art of Turning Trials into Triumph
insert into books_authors(books_id, authors_id) values (3, 2); -- Martin Odersky - Programming in Scala
insert into books_authors(books_id, authors_id) values (3, 3); -- Lex Spoon - Programming in Scala
insert into books_authors(books_id, authors_id) values (3, 4); -- Bill Venners - Programming in Scala