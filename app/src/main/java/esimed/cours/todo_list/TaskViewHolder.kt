package esimed.cours.todo_list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskViewHolder(row: View) : RecyclerView.ViewHolder(row){
    val imgRow = row.findViewById<ImageView>(R.id.imgListRow)
    val txtView = row.findViewById<TextView>(R.id.txtListRow)
}