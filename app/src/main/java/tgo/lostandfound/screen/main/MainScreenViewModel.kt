package tgo.lostandfound.screen.main

import android.app.Application
import base.mvvm.BaseViewModel
import base.di.MySelfApp
import javax.inject.Inject

class MainScreenViewModel(application: Application) : BaseViewModel(application) {


    init {
         MySelfApp.mRepositoryComponent.inject(this)
    }

//    lateinit var mListItem: MutableLiveData<Item>

    @Inject
    lateinit var mMainRepository: MainScreenRepo

    init {
        val mListItem = mMainRepository.getListItem()
    }

    fun getListItem() = mMainRepository.getListItem()
}