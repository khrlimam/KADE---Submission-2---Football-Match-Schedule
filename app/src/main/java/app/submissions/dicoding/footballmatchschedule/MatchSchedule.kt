package app.submissions.dicoding.footballmatchschedule

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import app.submissions.dicoding.footballmatchschedule.adapters.HeaderBodyRecyclerViewAdapter
import app.submissions.dicoding.footballmatchschedule.models.seals.DataType
import app.submissions.dicoding.footballmatchschedule.exts.invisible
import app.submissions.dicoding.footballmatchschedule.exts.visible
import app.submissions.dicoding.footballmatchschedule.models.Schedule
import app.submissions.dicoding.footballmatchschedule.presenters.MatchSchedulePresenter
import app.submissions.dicoding.footballmatchschedule.presenters.behavior.MatchScheduleBehavior
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_match_schedule.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.jetbrains.anko.startActivity

class MatchSchedule : AppCompatActivity(), AnkoLogger, MatchScheduleBehavior {

  private var activeShow: SCHEDULE = SCHEDULE.PREVIOUS
  private val matchSchedules: MutableList<DataType> = mutableListOf()
  private val adapter: HeaderBodyRecyclerViewAdapter = HeaderBodyRecyclerViewAdapter(matchSchedules, eventDetail())

  private fun eventDetail(): (Schedule) -> Unit {
    return {
      startActivity<MatchDetail>(BuildConfig.EVENT_DATA to it)
    }
  }

  private var disposable: Disposable? = null
  private val presenter: MatchSchedulePresenter = MatchSchedulePresenter(this)

  private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
    when (item.itemId) {
      R.id.previous_match -> {
        viwData(SCHEDULE.PREVIOUS)
        return@OnNavigationItemSelectedListener true
      }
      R.id.next_match -> {
        viwData(SCHEDULE.NEXT)
        return@OnNavigationItemSelectedListener true
      }
    }
    false
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_match_schedule)
    setSupportActionBar(toolbar)
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    rvRecylerView.layoutManager = LinearLayoutManager(this)
    rvRecylerView.adapter = this.adapter
    showPreviousSchedule()
  }

  private fun showNextSchedule() {
    presenter.getNextSchedule()
  }


  private fun showPreviousSchedule() {
    presenter.getPreviousSchedule()
  }

  private fun viwData(which: SCHEDULE) {
    if (activeShow != which)
      when (which) {
        SCHEDULE.NEXT -> {
          disposable?.dispose()
          showNextSchedule()
        }
        SCHEDULE.PREVIOUS -> {
          disposable?.dispose()
          showPreviousSchedule()
        }
      }
    activeShow = which
  }

  override fun onPause() {
    this.disposable?.dispose()
    super.onPause()
  }

  override fun onStop() {
    this.disposable?.dispose()
    super.onStop()
  }

  override fun showLoading() {
    pbLoading.visible()
  }

  override fun hideLoading() {
    pbLoading.invisible()
  }

  override fun showMatch(schedules: List<DataType>) {
    this.matchSchedules.clear()
    this.matchSchedules.addAll(schedules)
    this.adapter.notifyDataSetChanged()
  }

  override fun setDisposable(disposable: Disposable) {
    this.disposable = disposable
  }

  override fun onError(message: String) {
    alert(message) {
      okButton { }
    }.show()
  }

  enum class SCHEDULE {
    PREVIOUS,
    NEXT
  }

}
