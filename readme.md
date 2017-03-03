#Book REST service.

For test logic run '`mvn test`'.
For command line run.
In pom file: <br/>
`	<!--<packaging>jar</packaging>-->`<br/>
`	<packaging>war</packaging>`
`mvn clean package`<br/>
`cd target && java -jar book*.jar`
hehe

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
8. Dispatch url\`s
9. Controllers. Link url with Controllers throught reflection.
Controller interface.
10. Select JSON lib.
11. Http methods parse.
12. End interface, Controllers.

TODO:

13. Decorate myBatis mappers, entities.
14. Entity to JSON decore.
15. REST pribciples.
16. Test REST.
17. End all domain REST.
