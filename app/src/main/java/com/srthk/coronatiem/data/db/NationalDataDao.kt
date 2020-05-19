package com.srthk.coronatiem.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.srthk.coronatiem.data.db.entries.Statewise

@Dao
interface NationalDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNationalData(data: List<Statewise>)

    @Query("SELECT * FROM Statewise")
    fun getNationalData(): LiveData<List<Statewise>>
}