package tgo.lostandfound.screen.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import base.di.DaggerRepositoryComponent
import tgo.lostandfound.model.Item
import javax.inject.Inject

class MainScreenViewModel(application: Application) : BaseViewModel(application) {


    init {
        DaggerRepositoryComponent.builder().build().inject(this)
    }

//    lateinit var mListItem: MutableLiveData<Item>

    @Inject
    lateinit var mMainRepository: MainScreenRepo

    init {
        val mListItem = mMainRepository.getListItem()
    }

    fun getListItem() = mMainRepository.getListItem()
}