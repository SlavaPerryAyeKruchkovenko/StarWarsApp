package com.example.starwarsapp.database.dao

import androidx.room.*
import com.example.starwarsapp.data.interfaces.IPeople
import com.example.starwarsapp.database.entity.MovieEntity
import com.example.starwarsapp.database.entity.PeopleEntity
import com.example.starwarsapp.database.entity.PilotEntity
import com.example.starwarsapp.database.entity.StarshipEntity
import com.example.starwarsapp.database.relation.PeopleWithMovies

@Dao
interface PeopleDao {
    @Query("Select * from PeopleEntity WHERE id = :id")
    suspend fun getPeopleById(id: String): PeopleWithMovies?

    @Query("SELECT * FROM PeopleEntity WHERE LOWER(name) LIKE '%' || LOWER(:name) || '%'")
    suspend fun getPeoplesByName(name: String): List<PeopleWithMovies>

    @Query("SELECT * FROM PeopleEntity WHERE isLike = 1")
    suspend fun getLikedPeoples(): List<PeopleWithMovies>
    @Query("UPDATE PeopleEntity SET isLike = 0 WHERE isLike >= 1")
    suspend fun dislikeAllPeoples()
    @Transaction
    suspend fun softInsertPeoples(peoples: List<IPeople>) {
        peoples.forEach { people ->
            val dbPeople = getPeopleById(people.id)
            val movies = people.films.map {
                MovieEntity.fromIMovie(it)
            }
            if (dbPeople != null) {
                deletePeople(dbPeople.people, dbPeople.movies)
                insertPeople(
                    PeopleEntity.fromIPeople(people, dbPeople.people.isLike),
                    movies
                )
            } else {
                insertPeople(PeopleEntity.fromIPeople(people), movies)
            }
        }
    }
    @Delete
    suspend fun deletePeople(
        people: PeopleEntity,
        movie: List<MovieEntity>
    )
    @Update
    suspend fun updatePeople(people: PeopleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(people: PeopleEntity,movie: List<MovieEntity>)
}