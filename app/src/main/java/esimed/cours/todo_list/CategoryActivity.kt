package esimed.cours.todo_list

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import esimed.cours.todo_list.data.model.Category
import esimed.cours.todo_list.data.tier.TodoDatabase

import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.content_category.*

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        val db = TodoDatabase.getDatabase(this)
        setSupportActionBar(toolbar)
        db.seed()

        val categoryDAO = db.categoryDAO()
        lstCategory.layoutManager = LinearLayoutManager(this)
        lstCategory.adapter = CategoryAdapter(categoryDAO,this)

        fab.setOnClickListener{
            val dialog = Dialog(this@CategoryActivity)
            dialog.setContentView(R.layout.category_add)
            //button cancel
            (dialog.findViewById<View>(R.id.cancelAddBtnCategory) as Button)
                .setOnClickListener{dialog.dismiss()}
            //button add
            (dialog.findViewById<View>(R.id.addBtnCategory) as Button)
                .setOnClickListener{
                    val nameCatego = (dialog.findViewById<View>(R.id.addCategoryTxt) as EditText).text.toString()
                    categoryDAO.insert(
                        Category(name=nameCatego)
                    )
                    (lstCategory.adapter as CategoryAdapter).notifyDataSetChanged()
                    dialog.dismiss()
                }
            dialog.show()
        }

    }

}
