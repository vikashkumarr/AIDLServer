package com.example.aidlserver;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable {

    String name;

    public Employee(String name) {
        this.name = name;
    }

    protected Employee(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    public void readFromParcel(Parcel in){
        name = in.readString();

    }
}
