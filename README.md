# omnirio_assignment
Assignment for Omnirio

Java Assignment

Customer and account Microservices communication with WebClient and Service Registry
  
Complexity	High
Technology	Java, Spring
Effort(PDs)	8
 
 
Create two spring boot applications Customer and Account which have their own rest APIs for CRUD operation. The account application needs to fetch the customer details stored in the “Customer” application.
 
User Application table data (Microservice 1)
 
Column	Type	Remarks
userId	Varchar	Primary key
userName	Varchar	
dateOfBirth	Date	
gender	Char	Mandatory
phoneNumber	Varchar	

 Role Application table data (Microservice 1)
Column	Type	Remarks
roleId	Varchar	Primary key
roleName	Varchar	Unique
roleCode	Varchar	Unique
		User types - Customer, Branch Manager
		

Account Application table data (Microservice 2)
 
Column	Type	Remarks
accountId	Varchar	Primary key
accountType	Varchar	
openDate	Date	
CustomerId	Varchar	Mandatory, FK(User)
customerName	Varchar	
branch	Varchar	
minorIndicator	Char	Tell whether the age of the holder is above 18 years. Derive it from the date of birth.
 
·       User and role have one to one relationship between then. You can add more tables and keys if you think you required to design the solution.
·       Use the JWT token for the session created with the combination of roles to achieve authentication and authorization.
.       Only the Branch Manager can see all customer details and can modify it, the customer can only see his details in read-only mode. 
·       Use Spring WebClient for communication with Rest API.
·       Even if a new customer is to be added during account opening, create API of Customer service (Post Method) should be called to create the customer.
·       The two applications should be deployed on different ports and running on different instances of web-server.
·       Use the Eureka service registry to automatically register the applications and communicate without specifying the IP address and port.
·       Eureka provides a way to add required details in .properties files and get register on the Eureka server.
·       Multiple instances of the same application will be running and the request will be dynamically transferred to any instance by the load balancer.
 
 
Testing:
·       Manual testing of the rest APIs has to be done with the Postman tool.
·       Automation testing has to be done with Mockito.

Note: 

●	Need to follow design patterns, design standards, and coding standards.
●	API should be designed with the Richardson Maturity Model level 3.
●	Push your code to the public git repository and share the link.
●	You can use free Heroku cloud services for the deployment in case needed.



