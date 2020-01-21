package esimed.cours.todo_list.data.tier

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import esimed.cours.todo_list.data.model.Category
import esimed.cours.todo_list.data.model.Task
import java.text.ParseException

@Database(version = 1, entities = [Category::class,Task::class])
abstract class TodoDatabase : RoomDatabase(){
    abstract fun taskDAO() : TaskDAO
    abstract fun categoryDAO(): CategoryDAO

    fun seed(){
            if(categoryDAO().countCategory() == 0){
                try {
                    val catego1 = Category(
                        name="En cours"
                    )
                    val id = categoryDAO().insert(catego1)

                    val task1 = Task(
                        task= "Tache 1",
                        done= false,
                        category = id
                    )
                    taskDAO().insert(task1)
                    val task2 = Task(
                        task = "Tache 2",
                        done = true,
                        category = id
                    )
                    taskDAO().insert(task2)

                    val catego2 = Category(
                        name="Chez sois"
                    )
                    val id2 = categoryDAO().insert(catego2)

                    val task3 = Task(
                        task= "Tache 3",
                        done = false,
                        category = id2
                    )
                    taskDAO().insert(task3)

                    val task4 = Task(
                        task="Tache 4",
                        done= true,
                        category = id2
                    )
                    taskDAO().insert(task4)

                }catch (pe: ParseException){

                }
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