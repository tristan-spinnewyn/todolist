package esimed.cours.todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import esimed.cours.todo_list.data.model.Category
import esimed.cours.todo_list.data.model.Task
import esimed.cours.todo_list.data.tier.TodoDatabase
import kotlinx.android.synthetic.main.activity_main.*

class TaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val category = intent.getSerializableExtra("category") as Category
        val db = TodoDatabase.getDatabase(this)

        val taskDAO = db.taskDAO()

        lstTodo.layoutManager = LinearLayoutManager(this)
        lstTodo.adapter = TaskAdapter(category,taskDAO,this)
        val taskSwipeController = TaskSwipeController(category,taskDAO,lstTodo.adapter as TaskAdapter)
        val itemTouchHelper = ItemTouchHelper(taskSwipeController)
        itemTouchHelper.attachToRecyclerView(lstTodo)

        addTaskBtn.setOnClickListener{
            val txt = addTaskTxt.text.toString()
            if(txt.trim().isEmpty()){
                Toast.makeText(this,
                    "Veuillez saisir une tache",
                    Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val taskAdd = Task(task=txt,category = category.id as Long)
            taskDAO.insert(taskAdd)
            (lstTodo.adapter as TaskAdapter).notifyDataSetChanged()
            addTaskTxt.setText("")
        }

    }

    override fun onCreateOptionsMenu(menu:Menu): Boolean{
        menuInflater.inflate(R.menu.task_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val db = TodoDatabase.getDatabase(this)

        val taskDAO = db.taskDAO()
        val category = intent.getSerializableExtra("category") as Category
        return when (item.itemId) {
            R.id.menu_task_checkall -> {
                taskDAO.checkAll(true,category.id as Long)
                (lstTodo.adapter as TaskAdapter).notifyDataSetChanged()
                true
            }
            R.id.menu_task_uncheckall ->{
                taskDAO.checkAll(false,category.id as Long)
                (lstTodo.adapter as TaskAdapter).notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}