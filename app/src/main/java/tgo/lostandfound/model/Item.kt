package tgo.lostandfound.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    val mId: Int?,

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
){
    constructor(name:String?, pictureLink:String?, itemLink:String?, itemStoreLink:String?, person:String?)
            : this(1,name,pictureLink, itemLink, itemStoreLink, person) {

    }
}
