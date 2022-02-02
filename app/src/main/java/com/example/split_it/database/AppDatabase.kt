package com.example.split_it.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.split_it.database.dao.ExpenseDao
import com.example.split_it.database.dao.GroupDao
import com.example.split_it.database.dao.TransactionDao
import com.example.split_it.database.dao.UserDao
import com.example.split_it.database.model.Expense
import com.example.split_it.database.model.Group
import com.example.split_it.database.model.Transaction
import com.example.split_it.database.model.User
import kotlinx.coroutines.CoroutineScope

/**
 * Abstract class for the database of the app
 * Contains db name and the db singleton object
 */
@Database(
    entities = [
        User::class,
        Group::class,
        Expense::class,
        Transaction::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    //DAO's
    abstract fun userDao(): UserDao
    abstract fun groupDao(): GroupDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun transactionDao(): TransactionDao


    companion object {

        private const val DB_NAME = "split_it_database"


        //Singleton Object of the Database instance
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Function to return the singleton database instance.
         * If instance already exists, returns it or creates using
         * Room DatabaseBuilder.
         */
        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "split_it_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }

    }
}