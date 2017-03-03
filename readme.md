#Book REST service.

For test logic run '`mvn test`'.


Tasks:

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
8. Controllers. Link url with Controllers throught reflection.
Controller interface.
9. Select JSON lib.
10. Http methods parse.

TODO:

11. End interface, Controllers.
12. Decorate myBatis mappers, entities.
13. Entity to JSON decore.
14. Dispatch url\`s
15. REST.
16. Test REST.
