# skills-tracker-app

This app is created to track the skills of the employees in an organization.This app manages has 2 resources 'employees' and 'skills' and manages them.
This app is created as restful API using Spring Boot and inmemory DB i.e H2 DB. Credentials to connect to DB can be found in properties file.

Please find below the endpoints which are helpful:

Adding employees to an organization :  http://localhost:8080/api/v1/employees

Sample payload : 

 [
 
       {
        "firstName": "Divakar",
         "lastName": "Siriki"        
	},
       {
	"firstName": "Anand",
        "lastName": "Satya"        
	}
	
  ] 
 
 Get Employees :  http://localhost:8080/api/v1/employees
 
 getEmployeeByID : http://localhost:8080/api/v1/employees/{id}
 
 update employee by ID : http://localhost:8080/api/v1/employees/{id}
 
 delete employee by ID : http://localhost:8080/api/v1/employees/{id}
 
 Skills:
 
 Add Skills to Employee : http://localhost:8080/api/v1/employees/{employeeId}/skills
 
 get Skills By EmployeeId : http://localhost:8080/api/v1/employees/{employeeId}/skills
 
 update skill by skillId for employee : http://localhost:8080/api/v1/employees/{employeeId}/skills/{skillId}
 
 delete skill by skillId for employee : http://localhost:8080/api/v1/employees/{employeeId}/skills/{skillId}
 
 
