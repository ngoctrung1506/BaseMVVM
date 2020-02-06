package tgo.lostandfound.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import tgo.lostandfound.R
import tgo.lostandfound.model.Post

class PostAdapter(var mListPost: List<Post>, var mContext: Context) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    lateinit var mItemAdapter: ItemAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var view: View = LayoutInflater.from(mContext).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mListPost.size
    }

    fun updateList(listPost: List<Post>) {
        mListPost = listPost
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.mDayTxt.text = mListPost[position].day
        mItemAdapter = ItemAdapter(mListPost[position].item, mContext)
        holder.mDayRecyler.layoutManager =
            androidx.recyclerview.widget.GridLayoutManager(mContext, 2)
        holder.mDayRecyler.adapter = mItemAdapter
    }

    inner class PostViewHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.item_post_txt)
        lateinit var mDayTxt: TextView

        @BindView(R.id.item_post_recycler)
        lateinit var mDayRecyler: androidx.recyclerview.widget.RecyclerView

        init {
            ButterKnife.bind(this, itemView)
        }

    }
}