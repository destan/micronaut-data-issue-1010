## Exceptions in different cases

```kotlin
fun list(): MutableList<SomeDtoWithGroup>
```

**runtime error**:

```
02:56:27.739 [main] WARN  o.h.e.jdbc.spi.SqlExceptionHelper - SQL Error: 42001, SQLState: 42001
02:56:27.739 [main] ERROR o.h.e.jdbc.spi.SqlExceptionHelper - Syntax error in SQL statement "SELECT USER0_.USERNAME AS COL_0_0_, USER0_.SOMETHING AS COL_1_0_, .[*] AS COL_2_0_, GROUP2_.ID AS ID1_0_, GROUP2_.NAME AS NAME2_0_ FROM APP_USER USER0_ INNER JOIN APP_USER_APP_GROUP GROUPS1_ ON USER0_.ID=GROUPS1_.USER_ID INNER JOIN APP_GROUP GROUP2_ ON GROUPS1_.GROUPS_ID=GROUP2_.ID "; expected "*, NOT, EXISTS, INTERSECTS"; SQL statement:
select user0_.username as col_0_0_, user0_.something as col_1_0_, . as col_2_0_, group2_.id as id1_0_, group2_.name as name2_0_ from app_user user0_ inner join app_user_app_group groups1_ on user0_.id=groups1_.user_id inner join app_group group2_ on groups1_.groups_id=group2_.id [42001-199]
02:56:27.743 [main] ERROR io.micronaut.runtime.Micronaut - Error starting Micronaut server: could not prepare statement
org.hibernate.exception.SQLGrammarException: could not prepare statement
        at org.hibernate.exception.internal.SQLExceptionTypeDelegate.convert(SQLExceptionTypeDelegate.java:63)

```

---

```kotlin
@Join("groups", type = Join.Type.LEFT)
fun list(): MutableList<SomeDtoWithGroup>
```

**runtime error**:

```
02:58:12.014 [main] ERROR io.micronaut.runtime.Micronaut - Error starting Micronaut server: Cannot convert object type class com.example.Group to required type: interface java.util.Set
java.lang.IllegalArgumentException: Cannot convert object type class com.example.Group to required type: interface java.util.Set
        at io.micronaut.data.runtime.mapper.BeanIntrospectionMapper.lambda$null$1(BeanIntrospectionMapper.java:74)
        at java.base/java.util.Optional.orElseGet(Optional.java:362)
        at io.micronaut.data.runtime.mapper.BeanIntrospectionMapper.lambda$map$2(BeanIntrospectionMapper.java:73)
        at java.base/java.util.Optional.orElseThrow(Optional.java:401)

```

---

```kotlin
@Query("SELECT u FROM User u LEFT JOIN FETCH u.groups")
fun list(): MutableList<SomeDtoWithGroup>
```

**runtime error**:
```
03:00:10.776 [main] ERROR io.micronaut.runtime.Micronaut - Error starting Micronaut server: class [Ljava.lang.Object; cannot be cast to class javax.persistence.Tuple ([Ljava.lang.Object; is in module java.base of loader 'bootstrap'; javax.persistence.Tuple is in unnamed module of loader 'app')
java.lang.ClassCastException: class [Ljava.lang.Object; cannot be cast to class javax.persistence.Tuple ([Ljava.lang.Object; is in module java.base of loader 'bootstrap'; javax.persistence.Tuple is in unnamed module of loader 'app')
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
        at java.base/java.util.Iterator.forEachRemaining(Iterator.java:133)
```

---

```kotlin
@Query("SELECT new com.example.SomeDtoWithGroup(u.username, u.something, u.groups) FROM User u LEFT JOIN u.groups g")
fun list(): MutableList<SomeDtoWithGroup>
```

**runtime error**:

```
03:02:48.895 [main] WARN  o.h.e.jdbc.spi.SqlExceptionHelper - SQL Error: 42001, SQLState: 42001
03:02:48.895 [main] ERROR o.h.e.jdbc.spi.SqlExceptionHelper - Syntax error in SQL statement "SELECT USER0_.USERNAME AS COL_0_0_, USER0_.SOMETHING AS COL_1_0_, .[*] AS COL_2_0_ FROM APP_USER USER0_ LEFT OUTER JOIN APP_USER_APP_GROUP GROUPS1_ ON USER0_.ID=GROUPS1_.USER_ID LEFT OUTER JOIN APP_GROUP GROUP2_ ON GROUPS1_.GROUPS_ID=GROUP2_.ID INNER JOIN APP_USER_APP_GROUP GROUPS3_ ON USER0_.ID=GROUPS3_.USER_ID INNER JOIN APP_GROUP GROUP4_ ON GROUPS3_.GROUPS_ID=GROUP4_.ID "; expected "*, NOT, EXISTS, INTERSECTS"; SQL statement:
select user0_.username as col_0_0_, user0_.something as col_1_0_, . as col_2_0_ from app_user user0_ left outer join app_user_app_group groups1_ on user0_.id=groups1_.user_id left outer join app_group group2_ on groups1_.groups_id=group2_.id inner join app_user_app_group groups3_ on user0_.id=groups3_.user_id inner join app_group group4_ on groups3_.groups_id=group4_.id [42001-199]
03:02:48.901 [main] ERROR io.micronaut.runtime.Micronaut - Error starting Micronaut server: org.hibernate.exception.SQLGrammarException: could not prepare statement
javax.persistence.PersistenceException: org.hibernate.exception.SQLGrammarException: could not prepare statement
        at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:154)
        at org.hibernate.query.internal.AbstractProducedQuery.list(AbstractProducedQuery.java:1602)
        at io.micronaut.data.hibernate.operations.HibernateJpaOperations.lambda$findAll$5(HibernateJpaOperations.java:323)
```

---

```kotlin
@Query("SELECT new com.example.SomeDtoWithGroup(u.username, u.something, u.groups) FROM User u LEFT JOIN FETCH u.groups g")
fun list(): MutableList<SomeDtoWithGroup>
```

**runtime error**:

```
03:04:52.585 [main] ERROR io.micronaut.runtime.Micronaut - Error starting Micronaut server: org.hibernate.QueryException: query specified join fetching, but the owner of the fetched association was not present in the select list [FromElement{explicit,not a collection join,fetch join,fetch non-lazy properties,classAlias=g,role=com.example.User.groups,tableName=app_group,tableAlias=group2_,origin=app_user user0_,columns={user0_.id,className=com.example.Group}}] [SELECT new com.example.SomeDtoWithGroup(u.username, u.something, u.groups) FROM com.example.User u LEFT JOIN FETCH u.groups g]
java.lang.IllegalArgumentException: org.hibernate.QueryException: query specified join fetching, but the owner of the fetched association was not present in the select list [FromElement{explicit,not a collection join,fetch join,fetch non-lazy properties,classAlias=g,role=com.example.User.groups,tableName=app_group,tableAlias=group2_,origin=app_user user0_,columns={user0_.id,className=com.example.Group}}] [SELECT new com.example.SomeDtoWithGroup(u.username, u.something, u.groups) FROM com.example.User u LEFT JOIN FETCH u.groups g]
        at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:138)
        at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:181)
        at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:188)
        at org.hibernate.internal.AbstractSharedSessionContract.createQuery(AbstractSharedSessionContract.java:725)
 ```