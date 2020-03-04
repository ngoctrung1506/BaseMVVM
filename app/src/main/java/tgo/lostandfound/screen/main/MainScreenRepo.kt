package tgo.lostandfound.screen.main

import androidx.lifecycle.MutableLiveData
import base.api.BaseCallBack
import base.api.Result
import base.di.DaggerApiComponent
import base.di.DaggerRepositoryComponent
import tgo.lostandfound.api.meta.IMetaApi
import tgo.lostandfound.api.meta.MetaData
import tgo.lostandfound.dao.ItemDao
import javax.inject.Inject

class MainScreenRepo {

    lateinit var mItemDao: ItemDao

    @Inject
    lateinit var mMetaDataApi: IMetaApi

    init {
        DaggerApiComponent.builder().build().inject(this)
    }

//    fun getListItem() = mItemDao.getAllItem()

    fun getListItem(): MutableLiveData<Result<MetaData>> {
        var data = MutableLiveData<Result<MetaData>>()

        mMetaDataApi.getMeta().enqueue(object : BaseCallBack<MetaData>() {
            override fun onResult(result: Result<MetaData>) {
                data.value = result
            }
        })

        return data
    }
}