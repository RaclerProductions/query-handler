# QueryHandler

The QueryHandler is a library that allows you to easily create MYSQL and SQLite database connections and queries.

## Add the QueryHandler to your project [![](https://jitpack.io/v/RaclerProductions/query-handler.svg)](https://jitpack.io/#RaclerProductions/query-handler)
Add the JitPack repository to your pom.xml file
```
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
```
Add the dependency
```
        <dependency>
            <groupId>com.github.RaclerProductions</groupId>
            <artifactId>query-handler</artifactId>
            <version>0.1.1</version>
        </dependency>
```

## How to use
Any simple query should be possible with the QueryHandler. If you want to get a result of a select query, you have to use queryHandler.select() and if you want to get a true or false query from an update, delete or insert query, use queryHandler.execute() instead.

First, you should set up the QueryHandler as explained below.


### Setup
```
QueryHandler queryHandler = new QueryHandler(new DatabaseSettings.Builder()
        .host(HOST)
        .port(PORT)
        .user(USER)
        .password(PASSWORD)
        .database("test")
        .poolSize(10)
        .poolName("Query-Handler-Test")
        .queryTimeout(1000)
        .build());


queryHandler.connect();
```

The QueryHandler is now ready for use.

## Examples

### Select example
```
Result result = queryHandler.select("SELECT name, email FROM users WHERE id >= ?", 1);

for (Row row : result) {
    System.out.println(String.format("Name: %s, Email: %s, Password: %s", row.getString("name"), row.getString("email")));
}
```

### Update example
```
boolean success = queryHandler.execute("UPDATE users SET name = ? WHERE id =", "Ms. Test", 1);
```

### Insert example
```
boolean success = queryHandler.execute("INSERT INTO users (name, email, password) VALUES (?, ?, ?)", "Mr. Test", "test@example.com", "password213");
```

### Delete example
```
boolean success = queryHandler.execute("DELETE FROM users WHERE id = ?", 1);
```

### Count example
```
String query = "SELECT COUNT(*) as total FROM users WHERE id >= ?";

Result result = queryHandler.select(query, 10);

if (result.size() == 1) {
    Row row = result.get(0);
    System.out.println(String.format("Number of users with a id greater than 9: %s", row.getString("total")));
}
```

### Join example
```
String query = "SELECT * FROM table1, table2";

Result result = queryHandler.select(query);
```

### Left Join example
```
String query = "SELECT * FROM table1 LEFT JOIN table2 ON table1.id=table2.id";

Result result = queryHandler.select(query);
```

## License

Copyright 2019 Racler Productions

MIT, see LICENSE for details
