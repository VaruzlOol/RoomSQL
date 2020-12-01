package com.example.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class AlumnLab {
    @SuppressLint("StaticFieldLeak")
    private static AlumnLab sAlumnLab;
    private AlumnDAO mAlumnDao;

    private AlumnLab(Context context) {
        Context appContext = context.getApplicationContext();
        AppDatabaseRoom database = Room.databaseBuilder(appContext, AppDatabaseRoom.class, "database-school-2")
                .allowMainThreadQueries().build();
        mAlumnDao = database.alumnDAO();
    }

    public void deleteMigration(Context context){
        Context appContext = context.getApplicationContext();
        AppDatabaseRoom database2 = Room.databaseBuilder(appContext, AppDatabaseRoom.class, "database-school-2")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static AlumnLab get(Context context) {
        if (sAlumnLab == null) {
            sAlumnLab = new AlumnLab(context);
        }
        return sAlumnLab;
    }

    public List<Alumn> getAlumns() {
        return mAlumnDao.getAlumns();
    }

    public Alumn getAlumn(Integer Ids) {
        return mAlumnDao.getAlumn(Ids);
    }

    public void addAlumn(Alumn alumn) {
        mAlumnDao.insert(alumn);
    }

    public void updateAlumn(Alumn alumn) {
        mAlumnDao.update(alumn);
    }

    public void deleteAlumn(Alumn alumn) {
        mAlumnDao.delete(alumn);
    }

    public void deleteAllAlumns(){mAlumnDao.deleteAllAlumns();}

    public void deleteAlumn(int uid){mAlumnDao.deleteAlumn(uid);}
}
