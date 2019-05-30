package tgo.lostandfound.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import tgo.lostandfound.R
import tgo.lostandfound.model.Item

class ItemAdapter(var mListItem: List<Item>, var mContext: Context) :
   RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.mNameTxt.text = mListItem[position].mItemName
        Glide.with(mContext).load(mListItem[position].mItemPictureLink).into(holder.mPreImg)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var view: View = LayoutInflater.from(mContext).inflate(R.layout.item_object, parent, false)
        return ItemViewHolder(view)
    }


    override fun getItemCount(): Int {
        return mListItem.size
    }

    inner class ItemViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.item_object_name_txt)
        lateinit var mNameTxt: TextView

        @BindView(R.id.item_object_pre_img)
        lateinit var mPreImg: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

    }
}