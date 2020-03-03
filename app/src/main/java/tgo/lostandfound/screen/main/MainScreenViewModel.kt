package tgo.lostandfound.screen.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import tgo.lostandfound.model.Item

class MainScreenViewModel(application: Application) : BaseViewModel(application) {

//    override fun initRepositoryClass(): Class<MainRepository> {
//        return MainRepository::class.java
//    }

    lateinit var mListItem: MutableLiveData<Item>

    lateinit var mMainRepository: MainRepository

    init {

//        mMainRepository = MainRepository()
        val mListItem = mMainRepository.getListItem("trung")
    }
}