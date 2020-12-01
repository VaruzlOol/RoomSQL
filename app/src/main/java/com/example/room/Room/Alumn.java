package com.example.room.Room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alumn")
public class Alumn {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer uid;

    @ColumnInfo(name = "first_name")
    @NonNull
    private String firstName;

    @ColumnInfo(name = "last_name")
    @NonNull
    private String lastName;

    @ColumnInfo(name = "age")
    @Nullable
    private int age;

    @ColumnInfo(name = "tel")
    @Nullable
    private String tel;

    @NonNull
    public Integer getUid() {
        return uid;
    }

    public void setUid(@NonNull Integer uid) {
        this.uid = uid;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @Nullable
    public int getAge() {
        return age;
    }

    public void setAge(@Nullable int age) {
        this.age = age;
    }

    @Nullable
    public String getTel() {
        return tel;
    }

    public void setTel(@Nullable String tel) {
        this.tel = tel;
    }
}
