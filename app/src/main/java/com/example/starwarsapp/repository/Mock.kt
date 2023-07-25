package com.example.starwarsapp.repository

import com.example.starwarsapp.data.models.*

class Mock {
    fun getStarWarsObjects(): List<StarWarsObject> {
        return getObjectData()
    }

    private fun getObjectData(): List<StarWarsObject> {
        return listOf(
            Planet(
                "1",
                "Tatooine",
                "10465",
                "200000",
                listOf(
                    Movie(
                        "1",
                        "A New Hope",
                        "George Lucas",
                        "Gary Kurtz, Rick McCallum"
                    ),
                    Movie(
                        "3",
                        "Return of the Jedi",
                        "Richard Marquand",
                        "Howard G. Kazanjian, George Lucas, Rick McCallum"
                    )
                )
            ),
            Character(
                "1",
                "Luke Skywalker",
                "male",
                2,
                listOf(
                    Movie(
                        "1",
                        "A New Hope",
                        "George Lucas",
                        "Gary Kurtz, Rick McCallum"
                    ),
                    Movie(
                        "2",
                        "The Empire Strikes Back",
                        "Irvin Kershner",
                        "Gary Kurtz, Rick McCallum"
                    ),
                    Movie(
                        "3",
                        "Return of the Jedi",
                        "Richard Marquand",
                        "Howard G. Kazanjian, George Lucas, Rick McCallum"
                    )
                )
            ),
            Starship(
                "1",
                "CR90 corvette",
                "CR90 corvette",
                "Corellian Engineering Corporation",
                listOf(
                    Pilot(
                        "1",
                        "Luke Skywalker",
                        "male",
                        2,
                    )
                ),
                listOf(
                    Movie(
                        "1",
                        "A New Hope",
                        "George Lucas",
                        "Gary Kurtz, Rick McCallum"
                    ),
                    Movie(
                        "3",
                        "Return of the Jedi",
                        "Richard Marquand",
                        "Howard G. Kazanjian, George Lucas, Rick McCallum"
                    )
                )
            )
        )
    }
}