# EventRest API SH POC
API for searching and managing music, sports, art and many other type of events

- Java 8
- Maven
- JAX-RS
- AWS (Elastic Beanstalk & RDS MySql database)
- JDBC
- Tomcat 8.5

 # Testing URLS:
 
 - Events: http://eventrest-env-2.eba-sq7mzjh3.eu-west-1.elasticbeanstalk.com/api/events
 - Events by category ID: http://eventrest-env-2.eba-sq7mzjh3.eu-west-1.elasticbeanstalk.com/api/events/category/6
 - Specific event: http://eventrest-env-2.eba-sq7mzjh3.eu-west-1.elasticbeanstalk.com/api/events/1
 - Specific Event breadcrumb: http://eventrest-env-2.eba-sq7mzjh3.eu-west-1.elasticbeanstalk.com/api/events/3/breadcrumb
 
 - Categories: http://eventrest-env-2.eba-sq7mzjh3.eu-west-1.elasticbeanstalk.com/api/categories
 - Specific categories: http://eventrest-env-2.eba-sq7mzjh3.eu-west-1.elasticbeanstalk.com/api/categories/4
 
 # Functionalities
 
 - CRUD for Events and Categories (client such as Postman needed for testing POST, PUT, DELETE)
 - Stored procedure dbo.get_event_breadcrumb to quickly get an event breadcrumb/category path: https://github.com/razvan-cons/eventrest/blob/main/src/scripts/initdatabase.sql
 
 # Improvements (ToDo's)
 
 - Apply security to the API (users, secrets, permissions)
 - Add unit testing jUnit to the services
