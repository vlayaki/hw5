-- authors
insert into tauthor(id, first_name, last_name)
values (1, 'Joshua', 'Bloch');
insert into tauthor(id, first_name, last_name)
values (3, 'Kathy', 'Sierra');
insert into tauthor(id, first_name, last_name)
values (4, 'Bert', 'Bates');

-- genre
insert into tganre(id, name, description)
values (1, 'educational', 'desc');

-- book
insert into tbook(isbn, title, description)
values ('0596009208', 'Head First Java',
        'Learning a complex new language is no easy task especially when it s an object-oriented computer programming...');


-- tbook_author
insert into tbook_author(book_id, author_id) values('0596009208', 3);
insert into tbook_author(book_id, author_id) values('0596009208', 4);

-- tbook_ganre
insert into tbook_ganre (book_id, ganre_id)
values ('0596009208', 1);


