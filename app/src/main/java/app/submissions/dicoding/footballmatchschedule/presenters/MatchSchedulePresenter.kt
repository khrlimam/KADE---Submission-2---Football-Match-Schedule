package app.submissions.dicoding.footballmatchschedule.presenters

import app.submissions.dicoding.footballmatchschedule.models.seals.DataType
import app.submissions.dicoding.footballmatchschedule.models.seals.holders.ItemBodyHolder
import app.submissions.dicoding.footballmatchschedule.models.seals.holders.ItemHeaderHolder
import app.submissions.dicoding.footballmatchschedule.constants.League
import app.submissions.dicoding.footballmatchschedule.models.Schedule
import app.submissions.dicoding.footballmatchschedule.presenters.behavior.MatchScheduleBehavior
import app.submissions.dicoding.footballmatchschedule.requests.to.LeagueSchedule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MatchSchedulePresenter(val behavior: MatchScheduleBehavior) {

  private lateinit var disposable: Disposable

  fun getPreviousSchedule() {
    behavior.showLoading()
    behavior.setDisposable(
        LeagueSchedule.get.past15(League.ENGLISH)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
              behavior.showMatch(getGroupedDataItem(it.events))
            }, {
              onError(it.message)
            }, {
              behavior.hideLoading()
            }))
  }

  private fun onError(msg: String?) {
    behavior.onError("Oops. Error occured!\n%s".format(msg))
  }

  private fun getGroupedDataItem(data: List<Schedule>): MutableList<DataType> {
    val dataItem: MutableList<DataType> = mutableListOf()
    data.groupBy { it.dateEvent }.forEach {
      dataItem.add(ItemHeaderHolder(it.key))
      dataItem.addAll(it.value.map { ItemBodyHolder(it) })
    }
    return dataItem
  }

  fun getNextSchedule() {
    behavior.showLoading()
    behavior.setDisposable(LeagueSchedule.get.next15(League.ENGLISH)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          behavior.showMatch(getGroupedDataItem(it.events))
        }, {
          onError(it.message)
        }, {
          behavior.hideLoading()
        }))
  }
}