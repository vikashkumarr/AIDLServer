package com.example.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {

    MyAIDLImplementation myAIDLImplementation = new MyAIDLImplementation();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(this.getClass().getSimpleName(), "Service onBind !!");
        return myAIDLImplementation;

    }

    class MyAIDLImplementation extends IAddition.Stub {

        @Override
        public int addition(int num1, int num2) {
            Log.d(this.getClass().getSimpleName(), "MyAIDLImplementation Class !!");
            int finalValue = num1 + num2 + 10;
            return finalValue;
        }

        @Override
        public List<Employee> getEmployeeList() throws RemoteException {
            return getEmployeeNameList();
        }

        @Override
        public void updateEmployeeList(final Employee emp) throws RemoteException {

            emp.setName(emp.getName() + "It's with new Data !!");

            /*    new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    emp.setName(emp.getName() + "It's with new Data !!");
                }
            }, 5000);*/

        }
    }

    private List<Employee> getEmployeeNameList() {

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Vikash"));
        employeeList.add(new Employee("Pranesh"));
        return employeeList;
    }
}
