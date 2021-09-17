package br.com.fmmagalhaes.lab_todolist.framework

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fmmagalhaes.lab_todolist.data.TaskRepository
import br.com.fmmagalhaes.lab_todolist.implementations.TaskDataSourceImplementation
import br.com.fmmagalhaes.lab_todolist.domain.Task
import br.com.fmmagalhaes.lab_todolist.usecase.NewTaskUseCase
import br.com.fmmagalhaes.lab_todolist.usecase.TaskListUseCase
import java.lang.Exception

class TaskListViewModel : ViewModel() {

  companion object {
    const val TAG = "TaskRepository"
  }

  private val taskDataSource = TaskDataSourceImplementation()
  private val taskRepository = TaskRepository(taskDataSource)
  private val taskListUseCase = TaskListUseCase(taskRepository)
  private val newTaskUseCase = NewTaskUseCase(taskRepository)

  private var _tasksList = MutableLiveData<List<Task>>()
  private var list = listOf<Task>()

  val taskList: LiveData<List<Task>>
    get() = _tasksList

  fun init() {
    getAllTasks()
  }

  private fun getAllTasks() {
    Thread {
      try {
        _tasksList.postValue(taskListUseCase.invoke())
      } catch (exception: Exception) {
        Log.e(TAG, exception.message.toString())
      }
    }.start()
  }

  fun insertTask(task:Task) {
    try {
      newTaskUseCase.invoke(task)
      list = taskListUseCase.invoke()
      _tasksList.postValue(list)
    } catch (exception: Exception) {
      Log.e(TAG, exception.message.toString())
    }
  }

  fun findById(taskId: Int):Task{
    return Task(
      title = "teste",
      description = "descr",
      hour = "12:00",
      date = "17/09/21",
      id=1
    )
  }

}