package app.submissions.dicoding.footballmatchschedule.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.submissions.dicoding.footballmatchschedule.R
import app.submissions.dicoding.footballmatchschedule.models.seals.DataType
import app.submissions.dicoding.footballmatchschedule.models.seals.holders.ItemBodyHolder
import app.submissions.dicoding.footballmatchschedule.models.seals.holders.ItemHeaderHolder
import app.submissions.dicoding.footballmatchschedule.layouts.HeaderLayout
import app.submissions.dicoding.footballmatchschedule.models.Schedule
import kotlinx.android.synthetic.main.match_item.view.*
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.imageResource
import java.util.*

class HeaderBodyRecyclerViewAdapter(private val matches: List<DataType>,
                                    private val listener: (Schedule) -> Unit) :
    RecyclerView.Adapter<HeaderBodyRecyclerViewAdapter.BodyHeaderViewHolder>(), AnkoLogger {

  private lateinit var holder: BodyHeaderViewHolder

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BodyHeaderViewHolder {
    val inflater: LayoutInflater = LayoutInflater.from(parent.context)
    when (viewType) {
      DataType.header -> {
        /**
         * using this anko layout instead of xml because xml GIVES error
         * WITH MESSAGE: error inflating textview, WITH no reasons :(
         * AND THAT ERROR TAKES ME 1 MORE DAYS TO COMPLETE THIS SUBMISSION :'''''((((
         * That, now I am using Anko Layout. Thanks to Anko :)))
         */
        holder = HeaderViewHolder(HeaderLayout().createView(AnkoContext.create(parent.context, parent)))
      }
      DataType.body -> {
        holder = BodyViewHolder(inflater.inflate(R.layout.match_item, parent, false), listener)
      }
    }
    return holder
  }

  override fun getItemViewType(position: Int): Int = matches[position].type

  override fun getItemCount(): Int = matches.size

  private fun getItem(position: Int): DataType = matches[position]

  override fun onBindViewHolder(holder: BodyHeaderViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  class HeaderViewHolder(view: View) : BodyHeaderViewHolder(view), AnkoLogger {

    val tvHeader = view.find<TextView>(HeaderLayout.tvHeader)

    override fun bind(data: DataType) {
      val item: ItemHeaderHolder = data as ItemHeaderHolder
      tvHeader.text = item.header
    }
  }

  class BodyViewHolder(view: View, val listener: (Schedule) -> Unit) : BodyHeaderViewHolder(view), AnkoLogger {

    override fun bind(data: DataType) {
      data as ItemBodyHolder
      val body: Schedule = data.body
      itemView.tvTime.text = body.getTime()
      itemView.tvMatch?.text = body.strEvent
      itemView.tvWinner?.text = body.winnerDescription()
      itemView.tvLoser?.text = body.loserDescription()
      itemView.ivTimeContainer.imageResource = randomizeColor()
      itemView.setOnClickListener { listener(data.body) }
    }

    @SuppressLint("Recycle")
    private fun randomizeColor(): Int {
      val colors = itemView.resources.obtainTypedArray(R.array.rygbCircle)
      val bound: Int = colors.length()
      return colors.getResourceId(Random().nextInt(bound), 0)
    }
  }

  abstract class BodyHeaderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: DataType)
  }

}