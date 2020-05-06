// IAddition.aidl
package com.example.aidlserver;
import  com.example.aidlserver.Employee;
// Declare any non-default types here with import statements

interface IAddition {

   int addition(int num1, int num2);
   List<Employee> getEmployeeList();
   void updateEmployeeList(inout Employee emp);
}
