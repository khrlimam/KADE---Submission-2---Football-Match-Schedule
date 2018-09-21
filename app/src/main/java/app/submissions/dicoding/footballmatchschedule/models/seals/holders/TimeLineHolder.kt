package app.submissions.dicoding.footballmatchschedule.models.seals.holders

data class TimeLineHolder(val teamType: Int, val predicate: String, val time: String, val subject: String) {

  fun toIntTime(): Int = this.time.removeSuffix("'").toInt()

  companion object {
    const val HOME: Int = 0
    const val AWAY: Int = 1
  }
}