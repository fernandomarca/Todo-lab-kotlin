package br.com.fmmagalhaes.lab_todolist.presenter

import android.app.Activity
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import br.com.fmmagalhaes.lab_todolist.databinding.ActivityAddTaskBinding
import br.com.fmmagalhaes.lab_todolist.databinding.ActivityMainBinding
import br.com.fmmagalhaes.lab_todolist.extensions.format
import br.com.fmmagalhaes.lab_todolist.extensions.text
import br.com.fmmagalhaes.lab_todolist.domain.Task
import br.com.fmmagalhaes.lab_todolist.framework.TaskListViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddTaskActivity() : AppCompatActivity() {

  private lateinit var binding: ActivityAddTaskBinding

  private val taskListViewModel = TaskListViewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAddTaskBinding.inflate(layoutInflater)
    setContentView(binding.root)

    if(intent.hasExtra(TASK_ID)){
      val taskId = intent.getIntExtra(TASK_ID, 0)
      taskListViewModel.findById(taskId)?.let{
        binding.tilTitle.text = it.title
        binding.tilHour.text = it.hour
        binding.tilData.text = it.date
        binding.tilDescription.text = it.description
      }
    }
    insertListeners()
  }

  private fun insertListeners() {
    binding.tilData.editText?.setOnClickListener {
      val datePicker = MaterialDatePicker.Builder.datePicker().build()
      datePicker.addOnPositiveButtonClickListener {
        binding.tilData.text = Date(it).format()
      }
      datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
    }

    binding.tilHour.editText?.setOnClickListener {
      val timePicker = MaterialTimePicker.Builder()
        .setTimeFormat(TimeFormat.CLOCK_24H)
        .build()
      timePicker.addOnPositiveButtonClickListener {
        val minute = if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
        val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour

        binding.tilHour.text = "${hour}:${minute}"
      }
      timePicker.show(supportFragmentManager, null)
    }

    binding.btnCancelTask.setOnClickListener {
      finish()
    }

    binding.btnNewTask.setOnClickListener {
      val task = Task(
        title = binding.tilTitle.text,
        date = binding.tilData.text,
        hour = binding.tilHour.text,
        description = binding.tilDescription.text,
        id = intent.getIntExtra(TASK_ID,0)
      )
      taskListViewModel.insertTask(task)
      finish()
    }
  }

  companion object {
    const val TASK_ID = "task_id"
  }

}