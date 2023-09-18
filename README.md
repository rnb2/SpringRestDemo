# SpringRestDemo
Spring Rest example project

# Supported EndPoints:
Get: http://localhost:8082/rnb/api/employees
Get: http://localhost:8082/rnb/api/employees/{emplyeeId}
Get: http://localhost:8082/rnb/api/employees/name/{emplyeeFirstName}

POST: http://localhost:8082/rnb/api/employees
RequestBody:
{
  "firstName": "firstNameValue",
  "lastName": "lastNameValue",
  "email": "mail@value.com"
}

PUT: http://localhost:8082/rnb/api/employees
RequestBody:
{
   "id": 0000,
  "firstName": "firstNameValue",
  "lastName": "lastNameValue",
  "email": "mail@value.com"
}
