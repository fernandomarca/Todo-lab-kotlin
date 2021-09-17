package br.com.fmmagalhaes.lab_todolist.domain

data class Task(
  val title: String,
  val description: String,
  val hour: String,
  val date: String,
  val id: Int = 0
)