package tgo.lostandfound.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import tgo.lostandfound.model.Item

@Dao
interface ItemDao {

    @Query("SELECT * FROM item")
    fun getAllItem() : List<Item>

    @Insert
    fun insertItem(item:Item)
}