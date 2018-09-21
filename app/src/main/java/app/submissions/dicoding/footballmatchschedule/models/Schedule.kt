package app.submissions.dicoding.footballmatchschedule.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.format.DateTimeFormat

@Parcelize
data class Schedule(
    @SerializedName("idEvent")
    @Expose
    var idEvent: String? = null,
    @SerializedName("idSoccerXML")
    @Expose
    var idSoccerXML: String? = null,
    @SerializedName("strEvent")
    @Expose
    var strEvent: String? = null,
    @SerializedName("strFilename")
    @Expose
    var strFilename: String? = null,
    @SerializedName("strSport")
    @Expose
    var strSport: String? = null,
    @SerializedName("idLeague")
    @Expose
    var idLeague: String? = null,
    @SerializedName("strLeague")
    @Expose
    var strLeague: String = "",
    @SerializedName("strSeason")
    @Expose
    var strSeason: String? = null,
    @SerializedName("strDescriptionEN")
    @Expose
    var strDescriptionEN: String? = null,
    @SerializedName("strHomeTeam")
    @Expose
    var strHomeTeam: String? = null,
    @SerializedName("strAwayTeam")
    @Expose
    var strAwayTeam: String? = null,
    @SerializedName("intHomeScore")
    @Expose
    var intHomeScore: Int = 0,
    @SerializedName("intRound")
    @Expose
    var intRound: String? = null,
    @SerializedName("intAwayScore")
    @Expose
    var intAwayScore: Int = 0,
    @SerializedName("intSpectators")
    @Expose
    var intSpectators: String? = null,
    @SerializedName("strHomeGoalDetails")
    @Expose
    var strHomeGoalDetails: String? = "",
    @SerializedName("strHomeRedCards")
    @Expose
    var strHomeRedCards: String? = "",
    @SerializedName("strHomeYellowCards")
    @Expose
    var strHomeYellowCards: String? = "",
    @SerializedName("strHomeLineupGoalkeeper")
    @Expose
    var strHomeLineupGoalkeeper: String? = null,
    @SerializedName("strHomeLineupDefense")
    @Expose
    var strHomeLineupDefense: String? = null,
    @SerializedName("strHomeLineupMidfield")
    @Expose
    var strHomeLineupMidfield: String? = null,
    @SerializedName("strHomeLineupForward")
    @Expose
    var strHomeLineupForward: String? = null,
    @SerializedName("strHomeLineupSubstitutes")
    @Expose
    var strHomeLineupSubstitutes: String? = null,
    @SerializedName("strHomeFormation")
    @Expose
    var strHomeFormation: String? = null,
    @SerializedName("strAwayRedCards")
    @Expose
    var strAwayRedCards: String? = "",
    @SerializedName("strAwayYellowCards")
    @Expose
    var strAwayYellowCards: String? = "",
    @SerializedName("strAwayGoalDetails")
    @Expose
    var strAwayGoalDetails: String? = "",
    @SerializedName("strAwayLineupGoalkeeper")
    @Expose
    var strAwayLineupGoalkeeper: String? = null,
    @SerializedName("strAwayLineupDefense")
    @Expose
    var strAwayLineupDefense: String? = null,
    @SerializedName("strAwayLineupMidfield")
    @Expose
    var strAwayLineupMidfield: String? = null,
    @SerializedName("strAwayLineupForward")
    @Expose
    var strAwayLineupForward: String? = null,
    @SerializedName("strAwayLineupSubstitutes")
    @Expose
    var strAwayLineupSubstitutes: String? = null,
    @SerializedName("strAwayFormation")
    @Expose
    var strAwayFormation: String? = null,
    @SerializedName("intHomeShots")
    @Expose
    var intHomeShots: String? = null,
    @SerializedName("intAwayShots")
    @Expose
    var intAwayShots: String? = null,
    @SerializedName("dateEvent")
    @Expose
    var dateEvent: String = "",
    @SerializedName("strDate")
    @Expose
    var strDate: String? = null,
    @SerializedName("strTime")
    @Expose
    var strTime: String? = null,
    @SerializedName("strTVStation")
    @Expose
    var strTVStation: String? = null,
    @SerializedName("idHomeTeam")
    @Expose
    var idHomeTeam: String = "",
    @SerializedName("idAwayTeam")
    @Expose
    var idAwayTeam: String = "",
    @SerializedName("strResult")
    @Expose
    var strResult: String? = null,
    @SerializedName("strCircuit")
    @Expose
    var strCircuit: String? = null,
    @SerializedName("strCountry")
    @Expose
    var strCountry: String? = null,
    @SerializedName("strCity")
    @Expose
    var strCity: String? = null,
    @SerializedName("strPoster")
    @Expose
    var strPoster: String? = null,
    @SerializedName("strFanart")
    @Expose
    var strFanart: String? = null,
    @SerializedName("strThumb")
    @Expose
    var strThumb: String? = null,
    @SerializedName("strBanner")
    @Expose
    var strBanner: String? = null,
    @SerializedName("strMap")
    @Expose
    var strMap: String? = null,
    @SerializedName("strLocked")
    @Expose
    var strLocked: String? = null
) : Parcelable {

  fun winnerDescription(): String {
    if (isNext()) return this.strLeague
    if (this.intHomeScore > this.intAwayScore)
      return "Winner: ${this.strHomeTeam} with score ${this.intHomeScore}"
    if (this.intHomeScore < this.intAwayScore)
      return "Winner: ${this.strAwayTeam} with score ${this.intAwayScore}"
    return "Draw"
  }

  fun loserDescription(): String {
    if (isNext()) return "Set your alarm!"
    if (this.intHomeScore > this.intAwayScore)
      return "${this.strAwayTeam} loses with score ${this.intAwayScore}"
    if (this.intHomeScore < this.intAwayScore)
      return "${this.strHomeTeam} loses with score ${this.intHomeScore}"
    return "The match is draw with score ${this.intHomeScore}:${this.intAwayScore}"
  }

  fun getTime(): String {
    return "${this.strTime?.substring(0, 5)}"
  }

  private fun getJodaDate(): DateTime = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(this.dateEvent)

  fun isNext(): Boolean {
    val eventDate = getJodaDate()
    val now = DateTime.now()
    return Days.daysBetween(now, eventDate).days > 0
  }

  fun getFormattedDate(): String = getJodaDate().toString("E, dd MMMM yyyy")

}

data class Events(val events: List<Schedule>)