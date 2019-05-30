package tgo.lostandfound.database

import androidx.room.Database
import androidx.room.RoomDatabase
import tgo.lostandfound.dao.ItemDao
import tgo.lostandfound.model.Item

@Database(entities = arrayOf(Item::class), version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}