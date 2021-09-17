package br.com.fmmagalhaes.lab_todolist.usecase

import br.com.fmmagalhaes.lab_todolist.data.TaskRepository
import br.com.fmmagalhaes.lab_todolist.domain.Task

class NewTaskUseCase(private val taskRepository: TaskRepository) {
  operator fun invoke(task:Task) = taskRepository.newTaskFromDataSource(task)
}