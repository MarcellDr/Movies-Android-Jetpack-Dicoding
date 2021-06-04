package com.marcelldr.cinemaz.data.dummy

import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.FavoriteEntity
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.MoviesEntity
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.TvEntity
import com.marcelldr.cinemaz.utils.GenreUtils

object DataDummy {
    private val MOVIES = listOf(
        mapOf(
            "id" to 399566,
            "title" to "Godzilla vs. Kong",
            "genre" to listOf(
                28, 878
            ),
            "rating" to 8.3,
            "overview" to "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            "poster" to "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            "background" to "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg"
        ),
        mapOf(
            "id" to 615678,
            "title" to "Thunder Force",
            "genre" to listOf(
                28, 12, 35, 14
            ),
            "rating" to 5.8,
            "overview" to "In a world where supervillains are commonplace, two estranged childhood best friends reunite after one devises a treatment that gives them powers to protect their city.",
            "poster" to "/279yOM4OQREL36B3SECnRxoB4MZ.jpg",
            "background" to "/z7HLq35df6ZpRxdMAE0qE3Ge4SJ.jpg"
        ),
        mapOf(
            "id" to 460465,
            "title" to "Mortal Kombat",
            "genre" to listOf(
                14, 28, 12, 878, 53
            ),
            "rating" to 8.2,
            "overview" to "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "poster" to "/6K0RCDfP9ExbTcYgryaQHTGmET7.jpg",
            "background" to "/6ELCZlTA5lGUops70hKdB83WJxH.jpg"
        ),
        mapOf(
            "id" to 791373,
            "title" to "Zack Snyder's Justice League",
            "genre" to listOf(
                28, 12, 14, 878
            ),
            "rating" to 8.5,
            "overview" to "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
            "poster" to "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
            "background" to "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg"
        ),
        mapOf(
            "id" to 634528,
            "title" to "The Marksman",
            "genre" to listOf(
                28, 53, 80
            ),
            "rating" to 7.1,
            "overview" to "Jim Hanson’s quiet life is suddenly disturbed by two people crossing the US/Mexico border – a woman and her young son – desperate to flee a Mexican cartel. After a shootout leaves the mother dead, Jim becomes the boy’s reluctant defender. He embraces his role as Miguel’s protector and will stop at nothing to get him to safety, as they go on the run from the relentless assassins.",
            "poster" to "/6vcDalR50RWa309vBH1NLmG2rjQ.jpg",
            "background" to "/5Zv5KmgZzdIvXz2KC3n0MyecSNL.jpg"
        ),
        mapOf(
            "id" to 412656,
            "title" to "Chaos Walking",
            "genre" to listOf(
                878, 28, 12, 53
            ),
            "rating" to 7.4,
            "overview" to "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
            "poster" to "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
            "background" to "/ovggmAOu1IbPGTQE8lg4lBasNC7.jpg"
        ),
        mapOf(
            "id" to 615457,
            "title" to "Nobody",
            "genre" to listOf(
                28, 53, 80
            ),
            "rating" to 8.5,
            "overview" to "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
            "poster" to "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
            "background" to "/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg"
        ),
        mapOf(
            "id" to 527774,
            "title" to "Raya and the Last Dragon",
            "genre" to listOf(
                16, 12, 14, 10751, 28
            ),
            "rating" to 8.3,
            "overview" to "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
            "poster" to "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
            "background" to "/7prYzufdIOy1KCTZKVWpjBFqqNr.jpg"
        ),
        mapOf(
            "id" to 632357,
            "title" to "The Unholy",
            "genre" to listOf(
                27
            ),
            "rating" to 5.5,
            "overview" to "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
            "poster" to "/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
            "background" to "/lkInRiMtLgl9u9xE0By5hqf66K8.jpg"
        ),
        mapOf(
            "id" to 793723,
            "title" to "Sentinelle",
            "genre" to listOf(
                53, 28, 18
            ),
            "rating" to 6.0,
            "overview" to "Transferred home after a traumatizing combat mission, a highly trained French soldier uses her lethal skills to hunt down the man who hurt her sister.",
            "poster" to "/fFRq98cW9lTo6di2o4lK1qUAWaN.jpg",
            "background" to "/eTgQlyIQH0nA5BsmYpvCzSPAorg.jpg"
        ),
    )
    private val TVSERIES = listOf(
        mapOf(
            "id" to 88396,
            "title" to "The Falcon and the Winter Soldier",
            "genre" to listOf(
                10765, 10759, 18, 10768
            ),
            "rating" to 7.9,
            "overview" to "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            "poster" to "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            "background" to "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg"
        ),
        mapOf(
            "id" to 71712,
            "title" to "The Good Doctor",
            "genre" to listOf(
                18
            ),
            "rating" to 8.6,
            "overview" to "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
            "poster" to "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            "background" to "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg"
        ),
        mapOf(
            "id" to 60735,
            "title" to "The Flash",
            "genre" to listOf(
                18, 10765
            ),
            "rating" to 7.7,
            "overview" to "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "poster" to "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            "background" to "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg"
        ),
        mapOf(
            "id" to 95557,
            "title" to "Invincible",
            "genre" to listOf(
                16, 10759, 18
            ),
            "rating" to 8.9,
            "overview" to "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
            "poster" to "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
            "background" to "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg"
        ),
        mapOf(
            "id" to 69050,
            "title" to "Riverdale",
            "genre" to listOf(
                9648, 18, 80
            ),
            "rating" to 8.6,
            "overview" to "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            "poster" to "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
            "background" to "/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg"
        ),
        mapOf(
            "id" to 1416,
            "title" to "Grey's Anatomy",
            "genre" to listOf(
                18
            ),
            "rating" to 8.2,
            "overview" to "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            "poster" to "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
            "background" to "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg"
        ),
        mapOf(
            "id" to 63174,
            "title" to "Lucifer",
            "genre" to listOf(
                80, 10765
            ),
            "rating" to 8.5,
            "overview" to "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
            "poster" to "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
            "background" to "/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg"
        ),
        mapOf(
            "id" to 79008,
            "title" to "Luis Miguel: The Series",
            "genre" to listOf(
                18
            ),
            "rating" to 8.0,
            "overview" to "The series dramatizes the life story of Mexican superstar singer Luis Miguel, who has captivated audiences in Latin America and beyond for decades.",
            "poster" to "/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg",
            "background" to "/wkyzeBBKLhSg1Oqhky5yoiFF2hG.jpg"
        ),
        mapOf(
            "id" to 62286,
            "title" to "The Walking Dead",
            "genre" to listOf(
                10759, 18, 10765
            ),
            "rating" to 8.1,
            "overview" to "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
            "poster" to "/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
            "background" to "/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg"
        ),
        mapOf(
            "id" to 85271,
            "title" to "WandaVision",
            "genre" to listOf(
                10765, 9648, 18
            ),
            "rating" to 8.4,
            "overview" to "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
            "poster" to "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
            "background" to "/1i1N0AVRb54H6ZFPDTwbo9MLxSF.jpg"
        ),
    )

    fun getMovies(): List<MoviesEntity> {
        val list = mutableListOf<MoviesEntity>()
        val dummies = MOVIES
        for (i in dummies) {
            val genre = GenreUtils.getGenre(i, "MOVIES")
            val movies = MoviesEntity(
                i["id"] as Int,
                i["title"] as String,
                genre,
                i["rating"] as Double,
                i["overview"] as String,
                "https://image.tmdb.org/t/p/w500${i["poster"] as String}",
                "https://image.tmdb.org/t/p/w500${i["background"] as String}"
            )
            list.add(movies)
        }
        return list
    }

    fun getTv(): List<TvEntity> {
        val list = mutableListOf<TvEntity>()
        val dummies = TVSERIES
        for (i in dummies) {
            val genre = GenreUtils.getGenre(i, "TV")
            val tv = TvEntity(
                i["id"] as Int,
                i["title"] as String,
                genre,
                i["rating"] as Double,
                i["overview"] as String,
                "https://image.tmdb.org/t/p/w500${i["poster"] as String}",
                "https://image.tmdb.org/t/p/w500${i["background"] as String}"
            )
            list.add(tv)
        }
        return list
    }

    fun getMoviesFavorite(): List<FavoriteEntity> {
        val list = mutableListOf<FavoriteEntity>()
        val dummies = MOVIES
        for (i in dummies) {
            val genre = GenreUtils.getGenre(i, "MOVIES")
            val movies = FavoriteEntity(
                i["id"] as Int,
                "MOVIES",
                i["title"] as String,
                genre,
                i["rating"] as Double,
                i["overview"] as String,
                "https://image.tmdb.org/t/p/w500${i["poster"] as String}",
                "https://image.tmdb.org/t/p/w500${i["background"] as String}"
            )
            list.add(movies)
        }
        return list
    }

    fun getTvFavorite():List<FavoriteEntity> {
        val list = mutableListOf<FavoriteEntity>()
        val dummies = TVSERIES
        for (i in dummies) {
            val genre = GenreUtils.getGenre(i, "TV")
            val tv = FavoriteEntity(
                i["id"] as Int,
                "TV",
                i["title"] as String,
                genre,
                i["rating"] as Double,
                i["overview"] as String,
                "https://image.tmdb.org/t/p/w500${i["poster"] as String}",
                "https://image.tmdb.org/t/p/w500${i["background"] as String}"
            )
            list.add(tv)
        }
        return list
    }
}