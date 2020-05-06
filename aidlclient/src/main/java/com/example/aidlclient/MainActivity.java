package com.example.aidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aidlserver.Employee;
import com.example.aidlserver.IAddition;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    IAddition additionInf;
    TextView bindService, calculateAddition, empList, updateEmpList, showEmpList;
    ServiceConnection connectionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService = findViewById(R.id.bind_service);
        calculateAddition = findViewById(R.id.addition);
        empList = findViewById(R.id.employee_list);
        updateEmpList = findViewById(R.id.update_emp_list);
        showEmpList = findViewById(R.id.show_list);

        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(this.getClass().getSimpleName(), "Service Binded !!");
                Intent intent = new Intent("com.vikash.addition");
                bindService(convertImplicitIntentToExplicitIntent(intent, v.getContext()), connectionService, BIND_AUTO_CREATE);

            }
        });

        calculateAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int addition = additionInf.addition(10, 10);
                    Log.d(this.getClass().getSimpleName(), "Addition " + addition);
                    Toast.makeText(v.getContext(), "Addition :" + addition, Toast.LENGTH_LONG).show();

                    for (int i = 0; i < additionInf.getEmployeeList().size(); i++) {
                        empList.append("\n" + additionInf.getEmployeeList().get(i).getName());
                    }


                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });


        updateEmpList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Employee employee = new Employee("A");


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                additionInf.updateEmployeeList(employee);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                            //Toast.makeText(v.getContext(),"List " + employee.getName(),Toast.LENGTH_SHORT).show();
                            showEmpList.setText("");
                            showEmpList.setText(employee.getName());
                        }
                    },5000 );
                    //additionInf.updateEmployeeList(employee);
                    //Toast.makeText(v.getContext(),"List " + employee.getName(),Toast.LENGTH_SHORT).show();
                    showEmpList.setText(employee.getName());

            }
        });


        connectionService = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(this.getClass().getSimpleName(), "Service Connected !!");
                additionInf = IAddition.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(this.getClass().getSimpleName(), "Service DisConnected !!");
            }
        };
    }

    public static Intent convertImplicitIntentToExplicitIntent(Intent implicitIntent, Context context) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfoList = pm.queryIntentServices(implicitIntent, 0);

        if (resolveInfoList == null || resolveInfoList.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfoList.get(0);
        ComponentName component = new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        return explicitIntent;
    }

}
