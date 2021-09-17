package br.com.fmmagalhaes.lab_todolist.data

import br.com.fmmagalhaes.lab_todolist.domain.Task

interface ITaskDataSource {
  fun getAllTasks():List<Task>
  fun insertTask(task: Task)
  fun findById(taskId: Int): Task?
  fun deleteTask(task: Task)
}