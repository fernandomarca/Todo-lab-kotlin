package br.com.fmmagalhaes.lab_todolist.implementations

import br.com.fmmagalhaes.lab_todolist.data.ITaskDataSource
import br.com.fmmagalhaes.lab_todolist.domain.Task

class TaskDataSourceImplementation() : ITaskDataSource {

  private val list = arrayListOf<Task>()

  override fun getAllTasks(): List<Task> {
    return list
  }

  override fun insertTask(task: Task) {
    if (task.id == 0) {
      list.add(task.copy(id = list.size + 1))
    } else {
      list.removeAt(task.id)
      list.add(task)
    }
  }

  override fun findById(taskId: Int): Task? {
    val task = list.find { it.id == taskId }
    return task
  }

  override fun deleteTask(task: Task) {
    list.remove(task)
  }


}

