package br.com.fmmagalhaes.lab_todolist.data

import br.com.fmmagalhaes.lab_todolist.domain.Task

class TaskRepository(private val taskDataSource: ITaskDataSource) {
  fun getAllTaskFromDataSource() = taskDataSource.getAllTasks()
  fun newTaskFromDataSource(task: Task) = taskDataSource.insertTask(task)
}