package br.com.fmmagalhaes.lab_todolist.extensions

import com.google.android.material.textfield.TextInputLayout
import java.util.*

private val locale = Locale("pt","BR")

fun Date.format():String{
  val timeZone = TimeZone.getDefault()
  val offset = timeZone.getOffset(this.time) * -1
  val date = this.time + offset
  return java.text.SimpleDateFormat("dd/MM/yy", locale).format(date)
}

var TextInputLayout.text: String
  get()=editText?.text?.toString() ?:""
  set(value){
    editText?.setText(value)
  }