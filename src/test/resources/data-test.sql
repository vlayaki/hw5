-- authors
insert into tauthor(id, first_name, last_name)
values (1, 'Joshua', 'Bloch');
insert into tauthor(id, first_name, last_name)
values (3, 'Kathy', 'Sierra');
insert into tauthor(id, first_name, last_name)
values (2, 'Martin', 'Fowler');
insert into tauthor(id, first_name, last_name)
values (4, 'Bert', 'Bates');

-- genre
insert into tgenre(id, name, description)
values (1, 'educational', 'desc');

-- book
insert into tbook(isbn, title, description)
values ('0596009208', 'Head First Java',
        'Learning a complex new language is no easy task especially when it s an object-oriented computer programming...');

insert into tbook(isbn, title, description)
values ('0321127420', 'Patterns of Enterprise Application Architecture',
        'The practice of enterprise application development has benefited from the emergence of many...');


-- tbook_author
insert into tbook_author(book_id, author_id) values('0596009208', 3);
insert into tbook_author(book_id, author_id) values('0596009208', 4);
insert into tbook_author(book_id, author_id) values('0321127420', 2);

-- tbook_genre
insert into tbook_genre (book_id, genre_id)
values ('0596009208', 1);
insert into tbook_genre (book_id, genre_id)
values ('0321127420', 1);

