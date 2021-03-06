package esimed.cours.todo_list

import android.content.Context
import android.content.DialogInterface
import android.icu.util.ULocale
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import esimed.cours.todo_list.data.model.Category
import esimed.cours.todo_list.data.tier.TaskDAO

class TaskAdapter(private val category: Category,
                  private val dao: TaskDAO,
                  private val context:Context): RecyclerView.Adapter<TaskViewHolder?>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.listrow, parent, false))
    }

    override fun getItemCount(): Int {
        return dao.countTask(category.id as Long)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = dao.getTask(position,category.id as Long) ?: return
        holder.txtView.setText(task.task)
        if(task.done){
            holder.imgRow.setImageDrawable(context.resources.getDrawable(R.drawable.ok,null))
        }else{
            holder.imgRow.setImageDrawable(context.resources.getDrawable(R.drawable.notok,null))
        }
        holder.itemView.setOnClickListener{
            task.done = !task.done
            dao.update(task)
            notifyDataSetChanged()
        }
    }
}