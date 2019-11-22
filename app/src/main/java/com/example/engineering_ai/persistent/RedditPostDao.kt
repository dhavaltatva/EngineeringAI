package com.example.engineering_ai.persistent

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.engineering_ai.model.Hit

@Dao
interface RedditPostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts : List<Hit>)

    @Query("SELECT * FROM hit")
    fun postsBySubreddit() : DataSource.Factory<Int, Hit>

}