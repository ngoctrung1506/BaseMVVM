package tgo.lostandfound.screen.main


import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import base.BaseFragment
import base.BaseViewModel
import butterknife.BindView
import butterknife.OnClick
import tgo.lostandfound.R
import tgo.lostandfound.adapter.PostAdapter
import tgo.lostandfound.database.AppDataBase
import tgo.lostandfound.model.Item
import tgo.lostandfound.model.Post
import tgo.lostandfound.screen.createpost.CreatePostFragment


/**
 * A simple [Fragment] subclass.
 *
 */
class MainScreenFragment : BaseFragment<MainScreenViewModel>() {

    override fun getViewModelClass(): Class<MainScreenViewModel>? = MainScreenViewModel::class.java


    @BindView(R.id.main_search_btn)
    lateinit var mSearchBtn: ImageButton

    @BindView(R.id.main_object_name_txt)
    lateinit var mObjectNameTxt: TextView

    @BindView(R.id.main_add_btn)
    lateinit var mAddBtn: Button

    @BindView(R.id.main_list_object_recycler)
    lateinit var mObjectRecycler: RecyclerView

    lateinit var mPostAdapter: PostAdapter
    lateinit var mListPost: MutableList<Post>
    lateinit var mListItem: List<Item>

    lateinit var mDatabase: AppDataBase


    override fun getLayoutId(): Int {
        return R.layout.fragment_main_screen
    }


    override fun onCreateLayout() {
        activity?.let {
            mDatabase = Room.databaseBuilder(
                activity!!.applicationContext,
                AppDataBase::class.java, "item"
            ).allowMainThreadQueries().build()

        }
        mListItem = if (mDatabase.itemDao().getAllItem() != null) {
            mDatabase.itemDao().getAllItem()
        } else mutableListOf<Item>()


        mListPost = mutableListOf<Post>()
        mListPost.add(Post("13/05/2019", mListItem))
        mListPost.add(Post("14/05/2019", mListItem))
        mListPost.add(Post("15/05/2019", mListItem))
        mListPost.add(Post("16/05/2019", mListItem))
        mListPost.add(Post("17/05/2019", mListItem))
        mListPost.add(Post("18/05/2019", mListItem))
        mPostAdapter = PostAdapter(mListPost, requireContext())
        var layoutManager1: LinearLayoutManager =
            LinearLayoutManager(
                activity,
                RecyclerView.VERTICAL, false
            )
        activity
        mObjectRecycler.layoutManager = layoutManager1
        mObjectRecycler.adapter = mPostAdapter

        mViewModel.
    }


    @OnClick(R.id.main_add_btn)
    fun onMove() {
        changeToScreen(CreatePostFragment())
//        val list = mDatabase.itemDao().getAllItem()
//        mListPost.add(Post("13/05/2019", list))
//        mListPost.add(Post("14/05/2019", list))
//        mListPost.add(Post("15/05/2019", list))
//        mListPost.add(Post("16/05/2019", list))
//        mPostAdapter.updateList(mListPost)
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "MainScreenFragment")
    }

}
