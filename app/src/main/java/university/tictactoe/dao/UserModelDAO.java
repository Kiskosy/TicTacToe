package university.tictactoe.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import university.tictactoe.models.UserModel;

@Dao
public interface UserModelDAO {

    @Query("SELECT * FROM UserModel")
    List<UserModel> getAll();

    @Insert
    void insert(UserModel userModel);

    @Delete
    void delete(UserModel userModel);

    @Update
    void update(UserModel userModel);

}
