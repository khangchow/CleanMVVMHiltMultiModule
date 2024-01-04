package com.chow.cleanmvvmhiltmultimodule.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.chow.cleanmvvmhiltmultimodule.data.local.dao.UserDao
import com.chow.cleanmvvmhiltmultimodule.data.local.entity.UserEntity
import com.chow.cleanmvvmhiltmultimodule.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    //Use the Callback class to init data in room database
    class Callback @Inject constructor(
        private val database: Provider<UserDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val dao = database.get().userDao()
            applicationScope.launch {
                dao.insert(UserEntity(1, "Khang", "khang@gmail.com"))
                dao.insert(UserEntity(2, "Phuc", "phuc@gmail.com"))
                dao.insert(UserEntity(3, "Nguyen", "nguyen@gmail.com"))
            }
        }
    }
}