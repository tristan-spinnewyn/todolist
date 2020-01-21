package esimed.cours.todo_list

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import esimed.cours.todo_list.data.model.Category
import esimed.cours.todo_list.data.tier.TaskDAO

class TaskSwipeController(private val category: Category,
                          private val dao: TaskDAO,
                          private val taskAdapter: TaskAdapter): ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder): Int {
        return makeMovementFlags(0, ItemTouchHelper.END)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val task = dao.getTask(viewHolder.adapterPosition,category.id as Long) ?: return
        dao.Delete(task)
        taskAdapter.notifyDataSetChanged()
    }
}