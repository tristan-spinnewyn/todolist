package esimed.cours.todo_list.data.tier

import androidx.room.*
import esimed.cours.todo_list.data.model.Task

@Dao
interface TaskDAO {
    @Query("SELECT * FROM Task")
    fun getTasks(): List<Task>

    @Query("SELECT * FROM Task LIMIT 1 OFFSET :position")
    fun getTask(position: Int): Task?

    @Query("SELECT COUNT(*) FROM Task")
    fun countTask(): Int

    @Insert
    fun insert(task: Task): Long

    @Update
    fun update(task: Task)

    @Delete
    fun Delete(task: Task)
}