package esimed.cours.todo_list

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import esimed.cours.todo_list.data.model.Category
import esimed.cours.todo_list.data.tier.CategoryDAO

class CategoryAdapter(private val dao: CategoryDAO,
                      private val context:Context) :RecyclerView.Adapter<CategoryViewHolder?>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.category_cell,parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dao.countCategory()
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = dao.getCategory(position) ?: return
        holder.txtRow.setText(category.name)
        holder.btnDelete.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Can you sure to delete this category ?")
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() })
                .setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog,_->
                        dao.delete(category)
                        notifyDataSetChanged()
                        dialog.dismiss()
                    })
            builder.create().show()
        }
        holder.btnUpdate.setOnClickListener{
            val dlg = Dialog(context)
            dlg.setContentView(R.layout.category_edit)
            (dlg.findViewById<View>(R.id.editCategoryTxt) as EditText).setText(category.name)
            (dlg.findViewById<View>(R.id.cancelEditBtnCategory) as Button)
                .setOnClickListener{dlg.dismiss()}
            (dlg.findViewById<View>(R.id.addEditCategory) as Button)
                .setOnClickListener{
                    val nameCatego = (dlg.findViewById<View>(R.id.editCategoryTxt) as EditText).text.toString()
                    category.name = nameCatego
                    dao.update(category)
                    notifyDataSetChanged()
                    dlg.dismiss()
                }
            dlg.show()
        }
        holder.itemView.setOnClickListener{
            val category: Category = dao.getCategory(position) ?: return@setOnClickListener
            val intent = Intent(context,TaskActivity::class.java)
            intent.putExtra("category",category)
            context.startActivity(intent)
        }
    }
}