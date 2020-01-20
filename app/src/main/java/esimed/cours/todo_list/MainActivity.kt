package esimed.cours.todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import esimed.cours.todo_list.data.model.Task
import esimed.cours.todo_list.data.tier.TodoDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = TodoDatabase.getDatabase(this)
        db.seed()
        val taskDAO = db.taskDAO()

        lstTodo.layoutManager = LinearLayoutManager(this)
        lstTodo.adapter = TaskAdapter(taskDAO,this)
        val taskSwipeController = TaskSwipeController(taskDAO,lstTodo.adapter as TaskAdapter)
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
            val taskAdd = Task(task=txt)
            taskDAO.insert(taskAdd)
            (lstTodo.adapter as TaskAdapter).notifyDataSetChanged()
            addTaskTxt.setText("")
        }


    }
}