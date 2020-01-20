package esimed.cours.todo_list.data.tier

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import esimed.cours.todo_list.data.model.Task
import java.text.ParseException

@Database(version = 1, entities = [Task::class])
abstract class TodoDatabase : RoomDatabase(){
    abstract fun taskDAO() : TaskDAO

    fun seed(){
        try{
            if(taskDAO().countTask() == 0){
                val task1 = Task(
                    task= "Tache 1",
                    done= false
                )
                taskDAO().insert(task1)
                val task2 = Task(
                    task = "Tache 2",
                    done = true
                )
                taskDAO().insert(task2)
            }
        }catch(pe: ParseException){

        }
    }

    companion object {
        var INSTANCE: TodoDatabase? = null
        fun getDatabase (context: Context): TodoDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, TodoDatabase::class.java, "todo.db")
                    .allowMainThreadQueries().build()
            }
            return INSTANCE!!
        }
    }
}