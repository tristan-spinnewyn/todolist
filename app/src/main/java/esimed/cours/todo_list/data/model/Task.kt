package esimed.cours.todo_list.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(indices = [ Index(value = ["id"], unique = true) ])
data class Task(@PrimaryKey(autoGenerate = true)  var id:Long? = null,
                var task:String,
                var done:Boolean=false): Serializable {
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