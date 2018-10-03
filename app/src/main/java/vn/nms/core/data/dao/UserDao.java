package vn.nms.core.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import vn.nms.core.data.entity.User;


@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    Flowable<List<User>> getUsers();

    @Query("SELECT * FROM user WHERE userId IN (:userIds)")
    Flowable<List<User>> loadAllByIds(int[] userIds);

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
//            + "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User users);

    @Delete
    void delete(User user);
}
