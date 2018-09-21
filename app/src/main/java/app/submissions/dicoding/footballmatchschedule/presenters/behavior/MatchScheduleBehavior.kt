package app.submissions.dicoding.footballmatchschedule.presenters.behavior

import app.submissions.dicoding.footballmatchschedule.models.seals.DataType
import io.reactivex.disposables.Disposable

interface MatchScheduleBehavior {
  fun showLoading()
  fun hideLoading()
  fun showMatch(schedules: List<DataType>)
  fun setDisposable(disposable: Disposable)
  fun onError(message: String)
}