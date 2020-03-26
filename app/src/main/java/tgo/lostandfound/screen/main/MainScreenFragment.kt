package tgo.lostandfound.screen.main


import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.OnClick
import gooner.demo.mvvm.BaseFragment
import tgo.lostandfound.R
import tgo.lostandfound.adapter.PostAdapter
import tgo.lostandfound.api.meta.MetaData
import tgo.lostandfound.database.AppDataBase
import tgo.lostandfound.model.Item
import tgo.lostandfound.model.Post
import tgo.lostandfound.screen.createpost.CreatePostFragment


/**
 * A simple [Fragment] subclass.
 *
 */
class MainScreenFragment : BaseFragment<MainScreenViewModel>() {

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

    override fun getViewModelClass(): Class<MainScreenViewModel>? = MainScreenViewModel::class.java


    override fun onCreateLayout() {
    }

    override fun observerViewModel() {
        mViewModel?.getListItem()?.observe(this, object : gooner.demo.api.BaseObserser<MetaData>() {
            override fun onFail(error: String) {
                Log.d("Info", "Meta: " + error)
            }

            override fun onSuccess(data: MetaData) {
                Log.d("Info", "Meta: " + data.listHooks.toString())
            }
        })
    }


    @OnClick(R.id.main_add_btn)
    fun onMove() {
        mActivity?.run {
            mScreenTransitionImp.addScreen(CreatePostFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "MainScreenFragment")
    }

}
