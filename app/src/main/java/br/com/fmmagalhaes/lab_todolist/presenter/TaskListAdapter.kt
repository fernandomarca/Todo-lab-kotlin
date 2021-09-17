package br.com.fmmagalhaes.lab_todolist.presenter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import br.com.fmmagalhaes.lab_todolist.R
import br.com.fmmagalhaes.lab_todolist.databinding.ItemTaskBinding
import br.com.fmmagalhaes.lab_todolist.domain.Task

class TaskListAdapter(private val list: List<Task>) : RecyclerView.Adapter<TaskViewHolder>() {

  var listenerEdit: (Task) -> Unit = {}
  var listenerDelete: (Task) -> Unit = {}

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val itemTaskBinding = ItemTaskBinding.inflate(inflater, parent, false)
    return TaskViewHolder(itemTaskBinding,this)
  }

  override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
    return holder.bind(list[position])
  }

  override fun getItemCount(): Int {
    return list.size
  }

  fun setTask(){
    notifyDataSetChanged();
  }
}

class TaskViewHolder(private val binding: ItemTaskBinding, private val adapter: TaskListAdapter) :
  RecyclerView.ViewHolder(binding.root) {

  fun bind(item: Task) {
    binding.tvTitle.text = item.title
    binding.tvDate.text = "${item.date} ${item.hour}"
    binding.more.setOnClickListener {
      showPopup(item)
    }
  }

  private fun showPopup(item: Task) {
    val more = binding.more
    val popupMenu = PopupMenu(more.context, more)
    popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
    popupMenu.setOnMenuItemClickListener {
      when (it.itemId) {
        R.id.action_edit -> adapter.listenerEdit(item)
        R.id.action_delete -> adapter.listenerDelete(item)
      }
      return@setOnMenuItemClickListener true
    }
    popupMenu.show()
  }

}