-- authors
insert into tauthor(id, first_name, last_name)
values (1, 'Joshua', 'Bloch');
insert into tauthor(id, first_name, last_name)
values (2, 'Martin', 'Fowler');
insert into tauthor(id, first_name, last_name)
values (3, 'Kathy', 'Sierra');
insert into tauthor(id, first_name, last_name)
values (4, 'Bert', 'Bates');

-- genre
insert into tgenre(id, name, description)
values (1, 'educational', 'desc');
insert into tgenre(id, name, description)
values (2, 'science fiction', 'desc');

-- book
insert into tbook(isbn, title, description)
values ('0134685997', 'Effective Java',
        'The Definitive Guide to Java Platform Best Practices–Updated for Java 7, 8, and 9');

insert into tbook(isbn, title, description)
values ('0321127420', 'Patterns of Enterprise Application Architecture',
        'The practice of enterprise application development has benefited from the emergence of many...');

insert into tbook(isbn, title, description)
values ('0596009208', 'Head First Java',
        'Learning a complex new language is no easy task especially when it s an object-oriented computer programming...');


-- tbook_author
insert into tbook_author(book_id, author_id) values('0134685997', 1);
insert into tbook_author(book_id, author_id) values('0321127420', 2);
insert into tbook_author(book_id, author_id) values('0596009208', 3);
insert into tbook_author(book_id, author_id) values('0596009208', 4);

-- tbook_genre
insert into tbook_genre (book_id, genre_id)
values ('0134685997', 1);
insert into tbook_genre (book_id, genre_id)
values ('0321127420', 1);
insert into tbook_genre (book_id, genre_id)
values ('0596009208', 1);


