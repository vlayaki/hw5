DROP TABLE IF EXISTS tauthor;
create table tauthor
(
  id         long primary key,
  first_name varchar2(255),
  last_name  varchar2(255)
);

drop table if exists tganre;
create table tganre
(
  id          long primary key,
  name        varchar2(255),
  description varchar2(255)
);

drop table if exists tbook;
create table tbook
(
  isbn        varchar2(10) primary key,
  title       varchar2(255),
  description varchar2(255)
);

drop table if exists tbook_author;
create table tbook_author
(
  author_id long,
  book_id   varchar2(10),
  PRIMARY KEY (author_id, book_id),
  FOREIGN KEY (author_id) references tauthor (id),
  FOREIGN KEY (book_id) references tbook (isbn)
);

create table tbook_ganre
(
  book_id  varchar2(10),
  ganre_id long,
  primary key (book_id, ganre_id),
  foreign key (book_id) references tbook (isbn),
  foreign key (ganre_id) references tganre (id)
)


