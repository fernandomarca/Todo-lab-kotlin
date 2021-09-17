package br.com.fmmagalhaes.lab_todolist.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import br.com.fmmagalhaes.lab_todolist.databinding.ActivityMainBinding
import br.com.fmmagalhaes.lab_todolist.framework.TaskListViewModel
import br.com.fmmagalhaes.lab_todolist.domain.Task

class MainActivity : AppCompatActivity() {

  private lateinit var activityMainBinding: ActivityMainBinding
  private lateinit var taskListViewModel: TaskListViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(activityMainBinding.root)

    taskListViewModel = ViewModelProvider.NewInstanceFactory().create(TaskListViewModel::class.java)
    taskListViewModel.init()

    initObserver()
    emptyTasks(true)
    insertListeners()
  }

  private fun initObserver() {
    taskListViewModel.taskList.observe(this, { list ->
      if (list.isNotEmpty()) {
        populateTask(list)
        emptyTasks(false)
      }
    })
  }

  private fun populateTask(list: List<Task>){
    activityMainBinding.rvTasks.apply {
      hasFixedSize()
      adapter = TaskListAdapter(list)
    }
  }

  private fun insertListeners() {
    activityMainBinding.btnAddTask.setOnClickListener {
      startActivity(Intent(this, AddTaskActivity::class.java))
    }
  }

  private fun emptyTasks(isEmpty:Boolean){
    activityMainBinding.includeEmpty.emptyStateView.visibility = if(isEmpty) View.VISIBLE else View.GONE
  }

}

