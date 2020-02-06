package tgo.lostandfound.screen.main

import tgo.lostandfound.dao.ItemDao
import tgo.lostandfound.database.AppDataBase

class MainScreenRepo {

    lateinit var mItemDao: ItemDao

    init {

    }

    fun getListItem() = mItemDao.getAllItem()
}