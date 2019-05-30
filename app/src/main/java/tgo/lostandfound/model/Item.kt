package tgo.lostandfound.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item constructor(

    @ColumnInfo(name = "item_name")
    val mItemName: String?,

    @ColumnInfo(name = "item_picture_link")
    val mItemPictureLink: String?,

    @ColumnInfo(name = "item_position")
    val mItemPosition: String?,

    @ColumnInfo(name = "item_store_position")
    val mItemStoreLink: String?,

    @ColumnInfo(name = "item_known_person")
    val mPersonKnown: String? = "Unknown"
) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var mId: Int? = null

}
