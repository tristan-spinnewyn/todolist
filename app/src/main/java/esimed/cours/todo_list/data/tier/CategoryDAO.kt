package esimed.cours.todo_list.data.tier

import androidx.room.*
import esimed.cours.todo_list.data.model.Category

@Dao
interface CategoryDAO {
    @Query("SELECT * FROM Category")
    fun getCategories() : List<Category>

    @Query("SELECT * FROM Category LIMIT 1 OFFSET :position")
    fun getCategory(position: Int) : Category?

    @Query("SELECT COUNT(*) FROM Category")
    fun countCategory(): Int

    @Insert
    fun insert(category: Category): Long

    @Update
    fun update(category: Category)

    @Delete
    fun delete(category: Category)
}