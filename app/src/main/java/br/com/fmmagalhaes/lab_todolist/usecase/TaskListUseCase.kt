package br.com.fmmagalhaes.lab_todolist.usecase

import br.com.fmmagalhaes.lab_todolist.data.TaskRepository

class TaskListUseCase(private val taskRepository: TaskRepository) {
  operator fun invoke() = taskRepository.getAllTaskFromDataSource()
}