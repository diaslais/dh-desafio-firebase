package com.laisd.desafiofirebase.home.repository

import com.laisd.desafiofirebase.home.model.Game

class GameRepository {

    fun getGames(callback: (games: List<Game>) -> Unit) {
        val planetsDataSet = setGamesList()
        callback.invoke(planetsDataSet)
    }

    private fun setGamesList(): List<Game> {
        return listOf(
            Game("Jazz Jackrabbit",
                "https://upload.wikimedia.org/wikipedia/en/4/4c/Jazz-cover.jpg",
                "1994",
                "Jazz Jackrabbit is a platform game developed and published by Epic MegaGames." +
                        "It was released in 1994 for MS-DOS-based computers, with subsequent Macintosh and " +
                        "Microsoft Windows releases in 1995 and 1996."
            ),
            Game("Carmen Sandiego",
                "https://upload.wikimedia.org/wikipedia/en/7/7c/Where_in_the_World_Is_Carmen_Sandiego.jpg",
                "1996",
                "Where in the World Is Carmen Sandiego? (sometimes referred to as Where in the " +
                        "World Is Carmen Sandiego? v3.0) is a 1996 video game part of the Carmen Sandiego " +
                        "franchise. It was the third version of the game, after the 1985 title of the same " +
                        "name and a 1992 Deluxe version of said game."
            ),
            Game("Earthworm Jim 2",
                "https://upload.wikimedia.org/wikipedia/en/d/da/Earthworm_Jim_2_%28EUR%29.PNG",
                "1995",
                "Earthworm Jim 2 is a run and gun platform video game starring " +
                        "an earthworm named Jim in a robotic suit who battles evil. It is a sequel to the " +
                        "original Earthworm Jim, and the second and final game in the Earthworm Jim series" +
                        " developed by original creators Doug TenNapel, David Perry, and Shiny Entertainment."
            ),
            Game("Doom",
                "https://upload.wikimedia.org/wikipedia/pt/5/57/Doom_cover_art.jpg",
                "1993",
                "Doom is a 1993 first-person shooter (FPS) game developed by id Software for " +
                        "MS-DOS. Players assume the role of a space marine, popularly known as Doomguy, " +
                        "fighting his way through hordes of invading demons from Hell."
            )
        )
    }
}