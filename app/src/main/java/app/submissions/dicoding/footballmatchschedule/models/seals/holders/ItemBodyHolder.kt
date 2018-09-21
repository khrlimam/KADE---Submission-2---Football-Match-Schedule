package app.submissions.dicoding.footballmatchschedule.models.seals.holders

import app.submissions.dicoding.footballmatchschedule.models.seals.DataType
import app.submissions.dicoding.footballmatchschedule.models.Schedule

data class ItemBodyHolder(val body: Schedule) : DataType() {
  override var type: Int
    get() = DataType.body
    set(value) {}
}