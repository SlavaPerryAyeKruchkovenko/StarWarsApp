package com.example.starwarsapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.starwarsapp.data.interfaces.IPeople
import com.example.starwarsapp.database.entity.PeopleEntity

@Dao
interface PeopleDao {
    @Query("Select * from PeopleEntity WHERE id = :id")
    suspend fun getPeopleById(id: String): PeopleEntity?

    @Query("SELECT * FROM PeopleEntity WHERE name = :name")
    suspend fun getPeoplesByName(name: String): List<PeopleEntity>

    @Query("SELECT * FROM PeopleEntity WHERE isLike = 1")
    suspend fun getLikedPeoples(): List<PeopleEntity>

    @Transaction
    suspend fun softInsertPeoples(peoples: List<IPeople>) {
        peoples.forEach { people ->
            val dbPeople = getPeopleById(people.id)
            if (dbPeople != null) {
                updatePeople(PeopleEntity.fromIPeople(people, dbPeople.isLike))
            } else {
                insertPeople(PeopleEntity.fromIPeople(people))
            }
        }
    }

    @Update
    suspend fun updatePeople(people: PeopleEntity)

    @Insert
    suspend fun insertPeople(people: PeopleEntity)
}