package com.example.newapp.data.entities

import android.graphics.Color.*
import android.provider.ContactsContract.CommonDataKinds.Organization.TITLE
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newapp.Constants

@Entity
data class Note(
    @ColumnInfo(name="Title")
    val title:String,
    @ColumnInfo(name="Content")
    val content:String,
    @ColumnInfo(name="Category")
    val category:String,
    var isFavorite :Boolean?=false
)
{       @PrimaryKey(autoGenerate = true)
          var id:Int?=null

}
