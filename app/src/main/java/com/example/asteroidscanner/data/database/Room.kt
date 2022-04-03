package com.example.asteroidscanner.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asteroidscanner.shared.Utils


@Dao
interface AsteroidDao {

    @Query("select * from databaseasteroid WHERE date >= :currentDate ORDER BY date ")
    fun getAsteroids(currentDate:String): LiveData<List<DatabaseAsteroid>>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg asteroids:DatabaseAsteroid)

    @Query("DELETE FROM databaseasteroid WHERE date < :currentDate")
    suspend fun removeOld(currentDate:String)
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