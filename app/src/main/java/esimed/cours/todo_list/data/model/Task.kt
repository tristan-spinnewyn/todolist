package esimed.cours.todo_list.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(indices = [ Index(value = ["id"], unique = true), Index(value= ["category"]) ],
    foreignKeys = [ForeignKey(entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["category"],
        onDelete = ForeignKey.CASCADE)])
data class Task(@PrimaryKey(autoGenerate = true)  var id:Long? = null,
                var task:String,
                var done:Boolean=false,
                var category:Long): Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as esimed.cours.todo_list.data.model.Task

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "id:$id task:$task done:$done"
    }
}