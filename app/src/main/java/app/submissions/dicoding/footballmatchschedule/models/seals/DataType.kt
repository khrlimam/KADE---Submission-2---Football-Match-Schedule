package app.submissions.dicoding.footballmatchschedule.models.seals

abstract class DataType {
  companion object {
    const val header: Int = 0
    const val body: Int = 1
  }

  abstract var type: Int

}