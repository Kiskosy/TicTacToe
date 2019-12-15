package university.tictactoe.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import university.tictactoe.dao.UserModelDAO;
import university.tictactoe.models.UserModel;

@Database(entities = {UserModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserModelDAO userModelDAO();
}
