package com.laisd.desafiofirebase.home.repository

import com.laisd.desafiofirebase.home.model.Game

class GameRepository {

    fun getGames(callback: (games: List<Game>) -> Unit) {
        val planetsDataSet = setGamesList()
        callback.invoke(planetsDataSet)
    }

    private fun setGamesList(): List<Game> {
        return listOf(
            Game("1",
                    "Jazz Jackrabbit",
                "https://static.wikia.nocookie.net/jazzjackrabbit/images/1/1f/Jazz-jackrabbit-dos-front-cover.jpg/revision/latest?cb=20180223135950",
                "1994",
                "Jazz Jackrabbit is a platform game developed and published by Epic MegaGames." +
                        "It was released in 1994 for MS-DOS-based computers, with subsequent Macintosh and " +
                        "Microsoft Windows releases in 1995 and 1996."
            ),
            Game("2",
                    "Carmen Sandiego",
                "https://upload.wikimedia.org/wikipedia/en/7/7c/Where_in_the_World_Is_Carmen_Sandiego.jpg",
                "1996",
                "Where in the World Is Carmen Sandiego? (sometimes referred to as Where in the " +
                        "World Is Carmen Sandiego? v3.0) is a 1996 video game part of the Carmen Sandiego " +
                        "franchise. It was the third version of the game, after the 1985 title of the same " +
                        "name and a 1992 Deluxe version of said game."
            ),
            Game("3",
                    "Earthworm Jim 2",
                "https://static.wikia.nocookie.net/ewj/images/5/53/Earthworm_Jim_2.jpg/revision/latest?cb=20100614223208",
                "1995",
                "Earthworm Jim 2 is a run and gun platform video game starring " +
                        "an earthworm named Jim in a robotic suit who battles evil. It is a sequel to the " +
                        "original Earthworm Jim, and the second and final game in the Earthworm Jim series" +
                        " developed by original creators Doug TenNapel, David Perry, and Shiny Entertainment."
            ),
            Game("4",
                    "Doom",
                "https://store-images.s-microsoft.com/image/apps.55563.14303655336501012.2beb08d9-395e-453b-b5e3-0d4ac24d9d15.f9a0e430-b546-45f8-85f1-1d2b90bb84d5?mode=scale&q=90&h=1080&w=1920",
                "1993",
                "Doom is a 1993 first-person shooter (FPS) game developed by id Software for " +
                        "MS-DOS. Players assume the role of a space marine, popularly known as Doomguy, " +
                        "fighting his way through hordes of invading demons from Hell."
            )
        )
    }
}