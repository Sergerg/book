#Book REST service.

`mvn clean package`

Use:
`http://localhost:8080/books/readers/{reader_id}/books/{book_id}`
<br/>
Methods:
<br/>
- GET - book_id not use, list of book for reader
<br/>
Url `http://localhost:8080/books/readers/1/books`
- PUT - add book to reader
<br/>
Url `http://localhost:8080/books/readers/1/books/2`
- DELETE - 'back' book from reader
<br/>
Url `http://localhost:8080/books/readers/1/books/2`

Bonus:
Add book:
<br/>
Method: `PUT`
<br/>
Url: `http://localhost:8080/books/books/`
<br/>
Body: `{"book":{"title":"title 1", "author":"Author 100"}}`


<br/>
Tasks list:

1. MyBatis query for get Reader\`s by Book, get Book`s by Reader.
2. Create 3 Book, 2 Reader, 1 Reader read 0 Book, 1 Reader read 2 Book. Test 1 query. 
3. Run tomcat.
4. Create servlet.
5. War.
In pom file: <br/>
`	<!--<packaging>jar</packaging>-->`<br/>
`	<packaging>war</packaging>`
6. Test injection mappers into Servlet throught Services.
7. Parse url.
8. Dispatch url\`s
9. Controllers. Link url with Controllers throught reflection.
Controller interface.
10. Select JSON lib.
11. Http methods parse.
12. End interface, Controllers.
13. Decorate myBatis mappers, entities.
14. Entity to JSON decore.
15. REST pribciples.
- URL:<br/>
Example: **/books/v1.0/books/10** <br/>
**../books/search/Title** <br/>
Add parsing !!!
- Params:<br/>
URL - only query, JSON - all data.
- Methods:
  - GET - list, concrete<br/>
    /books/v1.0/books/10<br/>
    /books/v1.0/books<br/>
    Search: ?title=book1
  - DELETE - удаление<br/>
    /books/v1.0/books/10
  - PUT - idempotent - add, update<br/>
    ???
  - POST - ???
- Errors:
  - 200 OK
  - 201 Created (Запись создана)
  - 400 Bad Request (некорректный запрос)
  - 404 Not Found (Данные не найдены)
  - 500 Internal server error (внутренняя ошибка сервера)
  return text<br/>
  error JSON format
16. Methods:
- выдать книгу абоненту; readers/{id}/books/{id} put
- принять книгу у абонента; readers/{id}/books/{id} delete?
- получить список книг, выданных заданному абоненту readers/{id}/books get

TODO:

17. Test REST.
18. End all domain REST.

19. Rest:
- Version:<br/>
v1.0
- Validation?
