package app.submissions.dicoding.footballmatchschedule.layouts

import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import app.submissions.dicoding.footballmatchschedule.R
import org.jetbrains.anko.*

class HeaderLayout : AnkoComponent<ViewGroup> {
  override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
    return linearLayout {
      lparams(width = matchParent, height = wrapContent)
      textView {
        id = tvHeader
        textColor = resources.getColor(R.color.lighterDark)
        backgroundColor = resources.getColor(R.color.darkWhite)
        typeface = Typeface.DEFAULT_BOLD
      }.lparams(width = matchParent, height = wrapContent) {
        padding = dip(10)
      }
    }
  }

  companion object {
    const val tvHeader: Int = 0
  }
}