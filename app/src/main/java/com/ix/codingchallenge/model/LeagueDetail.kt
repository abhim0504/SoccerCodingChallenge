package com.ix.codingchallenge.model

import com.ix.codingchallenge.util.Utils
import java.time.LocalDateTime

/*
 * Data class that holds League Details
*/
data class LeagueDetail(
    val id: Int,
    val type: String,
    val homeTeam: TeamDetails,
    val awayTeam: TeamDetails,
    val date: String,
    val competitionStage: CompetitionStage,
    val venue: Venue,
    val state: String,
    val score: Score
    ){
    val dateFormatted: LocalDateTime    // Get LocalDateTime format according to the timezone
        get() {
            return Utils.getDateFormat(date)
        }
}

data class TeamDetails(
    val id: Int,
    val name: String,
    val shortName: String,
    val abbr: String,
    val alias: String
)

data class CompetitionStage(
    val competition: Competition
)

data class Competition(
    val id: Int,
    val name: String
)

data class Venue(
    val id: Int,
    val name: String
)

data class Score(
    val home: Int,
    val away: Int,
    val winner: String?
)