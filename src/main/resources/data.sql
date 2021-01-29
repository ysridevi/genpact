DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS library;

CREATE TABLE library (
  library_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

insert into library values(1, 'lib1');
insert into library values(2, 'lib2');

CREATE TABLE book (
  book_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  library_id INT not null
);

ALTER TABLE book
    ADD FOREIGN KEY (library_id)
    REFERENCES library(library_id);
