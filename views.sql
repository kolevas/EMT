CREATE MATERIALIZED VIEW books_per_author AS
SELECT a.id         AS author_id,
       CONCAT(a.name, ' ', a.surname) AS author_name,
       COUNT(b.id)   AS num_books
FROM author a
         LEFT JOIN book b ON b.author_id = a.id
GROUP BY a.id, a.name, a.surname;

CREATE MATERIALIZED VIEW authors_per_country AS
SELECT c.id        AS country_id,
       c.name      AS country_name,
       COUNT(a.id) AS author_count
FROM country c
         LEFT JOIN
     author a ON a.country_id = c.id
GROUP BY c.id, c.name;