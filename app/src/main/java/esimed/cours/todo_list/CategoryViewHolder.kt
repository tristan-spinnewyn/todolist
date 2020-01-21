package esimed.cours.todo_list

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryViewHolder(row: View) : RecyclerView.ViewHolder(row){
    val txtRow = row.findViewById<TextView>(R.id.categoryTxt)
    val btnDelete = row.findViewById<ImageButton>(R.id.deleteCategoryBtn)
    val btnUpdate = row.findViewById<ImageButton>(R.id.editCategoryBtn)
}