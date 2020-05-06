// IAddition.aidl
package com.example.aidlserver;
import  com.example.aidlserver.Employee;
// Declare any non-default types here with import statements

interface IAddition {

   int addition(int num1, int num2);
   List<Employee> getEmployeeList();
   void updateEmployeeList(inout Employee emp);

/*   1. When using in mode, the parameter values are transmitted unidirectionally. After the client passes the object to the server, it still uses its own object values, which are not affected by the server.
   2. When the array type is passed by out mode, the client only passes the length of the array to the server, and the client gets the new array after the server assigns the value.
   3. When using inout mode to transfer the array type, the client will pass the complete array to the server, and the client will get the modified array from the server.
   4. When the Parcelable object is passed by out mode, the client passes an object whose attribute values are all empty to the server, and obtains the object reassigned by the server.
   5. When the Parcelable object is passed by inout mode, the client will pass the complete object to the server and get the modified object.*/
}
