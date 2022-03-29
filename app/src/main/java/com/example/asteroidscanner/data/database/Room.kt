package com.example.asteroidscanner.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidDao {
    @Query("select * from databaseasteroid")
    fun getAsteroids(): LiveData<List<DatabaseAsteroid>>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg asteroids:DatabaseAsteroid)
}

@Database(entities=[DatabaseAsteroid::class],version=1)
abstract class AsteroidDatabase: RoomDatabase(){
    abstract val asteroidDao:AsteroidDao
}

private lateinit var INSTANCE: AsteroidDatabase

fun getDatabase(context: Context): AsteroidDatabase {
    if(!::INSTANCE.isInitialized){
        INSTANCE = Room.databaseBuilder(context.applicationContext,AsteroidDatabase::class.java,"asteroids").build()
    }
    return INSTANCE

}