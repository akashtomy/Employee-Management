# Employee-Management
CRUD API's to save employee details

Employees Management System
A company wants to have an Employee Management System in which the user can save data for new 
employees, update the salary of any employee and display the employee with the maximum salary.



Requirements:
1. void insertData(String empNo,String employeeName, int salary) to insert employee data in the table.
 --I named this fnnction as saveEmployeeDetails
2. String maxSalary() - This method should return the Employee Number with maximum salary.
 --I named this fnnction as maxSalaryEmployeeDetails
3. void updateSalary(String empNo, int salary) - This method should update the salary of the employee 
with the new value of salary passed.
 --I named this fnnction as updateEmployeeDetails

# Validation:
1. Salary should be positive in first and third functionality. If not, then return an exception 
"InvalidEmployeeDetails"

Please find the Postman collection Link : https://www.getpostman.com/collections/9b556b72ce8e3f5a8c04
SQL file is also attached in the repository
