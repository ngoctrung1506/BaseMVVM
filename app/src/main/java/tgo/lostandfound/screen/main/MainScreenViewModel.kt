package tgo.lostandfound.screen.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import tgo.lostandfound.model.Item
import tgo.lostandfound.model.Post

class MainScreenViewModel(application: Application) : BaseViewModel(application) {

    lateinit var mListItem: MutableLiveData<Item>
    lateinit var mMainScreenRepo: MainScreenRepo

    init {
        val mListItem = mMainScreenRepo.getListItem()
    }
}