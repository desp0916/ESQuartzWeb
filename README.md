# ESQuartzWeb

## What is this?

This is a [Quartz Scheduler](http://www.quartz-scheduler.org/) web application for Elasticsearch. You can use it to schedule your Quartz jobs for Elasticsearch queries.

## Requirements

  1. PostgreSQL 9.2+
  2. Tomcat 7+
  3. quartz-2.2.3+

## How to Use?

### PostgreSQL

  1. Create a role: ```quartz```.
  2. Create a database: ``quartz`` and assign the owner to the role ```quartz```.
  3. Download the [Quartz tarball](http://www.quartz-scheduler.org/downloads/) and unpack it.  
  4. Run the db script under ```docs/dbTables/tables_postgres.sql```.

### Tomcat

1. Download [PostgreSQL JDBC driver](https://jdbc.postgresql.org/download.html) and copy it to ```${TOMCAT_HOME}/lib/```
2. Because JNDI is recommended, add the following snippet into ```${TOMCAT_HOME}/conf/context.xml```:

``` xml
    <Resource name="jdbc/postgres" auth="Container"
        type="javax.sql.DataSource" driverClassName="org.postgresql.Driver"
        url="jdbc:postgresql://127.0.0.1:5432/quartz"
        username="quartz" password="quartz" maxActive="20" maxIdle="10"
        maxWait="-1"/>
```

3. Modify ```web.xml```:
4. mvn clean package
5. Deploy the war to Tomcat

### References

  - [Java Quartz Configuration Example](https://examples.javacodegeeks.com/enterprise-java/quartz/java-quartz-configuration-example/)
  - [Correct way to persist Quartz triggers in database] (http://stackoverflow.com/questions/17402112/correct-way-to-persist-quartz-triggers-in-database)
  - [Apache Maven War Plugin - Usage](http://maven.apache.org/components/plugins/maven-war-plugin/usage.html)
  - [Tomcat7 使用Scheduler](http://tangblack.blogspot.tw/2014/09/tomcat7-scheduler.html)
  - [Quartz工作排程工具介紹(一)-簡單整合進Tomcat](http://justinyang1221.blogspot.tw/2014/05/quartz-tomcat.html)
  - [Tomcat: The Tomcat JDBC Connection Pool](https://tomcat.apache.org/tomcat-7.0-doc/jdbc-pool.html)
  - [Tomcat: JNDI Datasource HOW-TO](http://tomcat.apache.org/tomcat-7.0-doc/jndi-datasource-examples-howto.html#PostgreSQL)
  - [Change eclipse's built in tomcat context.xml file using WTP?](http://stackoverflow.com/questions/6759219/change-eclipses-built-in-tomcat-context-xml-file-using-wtp)
  - [SLF4J warning or error messages and their meanings](http://www.slf4j.org/codes.html#StaticLoggerBinder)
  - [Setup Quartz Scheduler with MYSQL Database](http://teknosrc.com/how-setup-quartz-scheduler-server-with-mysql-database/)