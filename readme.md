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

TODO:

7. Parse url.
8. Controllers. Link url with Controllers. Properties for Controllers?
9. Decorate myBatis mappers, entitirs.
10. Select JSON lib.
11. Entity to JSON decore.
12. Dispatch url\`s
13. REST.
14. Test REST.
