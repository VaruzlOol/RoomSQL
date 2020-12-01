package com.example.room.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlumnDAO {
    @Query("SELECT * FROM Alumn")
    List<Alumn> getAlumns();

    @Query("SELECT * FROM ALUMN WHERE uid LIKE :userIds")
    Alumn getAlumn(Integer userIds);

    @Query("SELECT * FROM Alumn WHERE uid IN (:userIds)")
    List<Alumn> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Alumn WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Alumn findByName(String first, String last);

    @Query("DELETE FROM Alumn")
    void deleteAllAlumns();

    @Query("DELETE FROM Alumn WHERE uid LIKE :uid")
    void deleteAlumn(int uid);

    @Insert
    void insertAll(Alumn... alumns);

    @Insert
    void insert(Alumn alumn);

    @Delete
    void deleteAll(Alumn... alumns);

    @Delete
    void delete(Alumn alumn);

    @Update
    void updateAll(Alumn... alumns);

    @Update
    void update(Alumn alumns);
}
