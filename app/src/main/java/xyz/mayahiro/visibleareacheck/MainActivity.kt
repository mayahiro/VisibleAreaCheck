package xyz.mayahiro.visibleareacheck

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recycler_view).let {
            it.adapter = ItemAdapter(this)
            it.layoutManager = LinearLayoutManager(this)
            it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    checkFirstVisibleItem((recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition(), recyclerView)
                    checkLastVisibleItem((recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition(), recyclerView)
                }
            })
        }
    }

    private fun checkFirstVisibleItem(position: Int, recyclerView: RecyclerView) {
        recyclerView.findViewHolderForLayoutPosition(position)?.let { viewHolder ->
            viewHolder as ItemAdapter.ItemViewHolder
            coloringView(viewHolder.itemView.visiblePercentage(), (viewHolder.itemView as CardView))
        }
    }

    private fun checkLastVisibleItem(position: Int, recyclerView: RecyclerView) {
        recyclerView.findViewHolderForLayoutPosition(position)?.let { viewHolder ->
            viewHolder as ItemAdapter.ItemViewHolder
            coloringView(viewHolder.itemView.visiblePercentage(), (viewHolder.itemView as CardView))
        }
    }

    private fun coloringView(visiblePercentage: Int, cardView: CardView) {
        cardView.setCardBackgroundColor(
            when (visiblePercentage) {
                in 75..100 -> Color.WHITE
                in 50..75 -> Color.BLUE
                in 25..50 -> Color.YELLOW
                else -> Color.RED
            }
        )
    }
}
