package tgo.lostandfound.screen.main

import androidx.lifecycle.MutableLiveData
import gooner.demo.api.BaseCallBack
import gooner.demo.api.Result
import tgo.lostandfound.api.meta.IMetaApi
import tgo.lostandfound.api.meta.MetaData
import tgo.lostandfound.dao.ItemDao
import tgo.lostandfound.di.MySelfApp
import javax.inject.Inject

class MainScreenRepo {

    lateinit var mItemDao: ItemDao

    @Inject
    lateinit var mMetaDataApi: IMetaApi

    init {
        MySelfApp.mApiComponent.inject(this)
    }

//    fun getListItem() = mItemDao.getAllItem()

    fun getListItem(): MutableLiveData<gooner.demo.api.Result<MetaData>> {
        var data = MutableLiveData<gooner.demo.api.Result<MetaData>>()

        mMetaDataApi.getMeta().enqueue(object : gooner.demo.api.BaseCallBack<MetaData>() {
            override fun onResult(result: gooner.demo.api.Result<MetaData>) {
                data.value = result
            }
        })

        return data
    }
}