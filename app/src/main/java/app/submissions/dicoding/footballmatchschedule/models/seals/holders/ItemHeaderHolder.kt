package app.submissions.dicoding.footballmatchschedule.models.seals.holders

import app.submissions.dicoding.footballmatchschedule.models.seals.DataType

data class ItemHeaderHolder(val header: String) : DataType() {

  override var type: Int
    get() = DataType.header
    set(value) {}
}