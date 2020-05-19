package com.srthk.coronatiem.data.db.entries

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Statewise(
    val active: String,
    val confirmed: String,
    val deaths: String,
    val deltaconfirmed: String,
    val deltadeaths: String,
    val deltarecovered: String,
    val lastupdatedtime: String,
    val recovered: String,
    val state: String,
    @PrimaryKey(autoGenerate = false)
    val statecode: String,
    val statenotes: String
)