package com.example.sqlitepractice;

import android.os.Parcel;
import android.os.Parcelable;

public class UserRecord implements Parcelable {
    private String phone;
    private String name;
    private String email;
    private String id;

    UserRecord() {

    }

    protected UserRecord(Parcel in) {
        phone = in.readString();
        name = in.readString();
        email = in.readString();
        id = in.readString();
    }

    public static final Creator<UserRecord> CREATOR = new Creator<UserRecord>() {
        @Override
        public UserRecord createFromParcel(Parcel in) {
            return new UserRecord(in);
        }

        @Override
        public UserRecord[] newArray(int size) {
            return new UserRecord[size];
        }
    };

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.phone);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.id);
    }
}
