package com.rlds.bebergua

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.*
import androidx.appcompat.app.AlertDialog
import model.CalcularIngestaoDiaria
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var edit_peso:EditText
    private  lateinit var edit_idade:EditText
    private  lateinit var bt_calcular:Button
    private  lateinit var txt_resultado:TextView
    private  lateinit var ic_redefinirDados: ImageView
    private  lateinit var  calcularIngestaoDiaria:CalcularIngestaoDiaria
    private  var resultadoMl = 0.0
    private  lateinit var  bt_lembrete: Button
    private  lateinit var  bt_alarme:Button
    private  lateinit var  txt_hora:TextView
    private  lateinit var  txt_minutos:TextView
    lateinit var  timePickerDialog: TimePickerDialog
    lateinit var calendario:Calendar
    var horaAtual = 0
    var minutosAtuais = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        inicializarComponentes()
        calcularIngestaoDiaria = CalcularIngestaoDiaria()
        bt_calcular.setOnClickListener {
            if(edit_peso.text.toString().isEmpty()){
                Toast.makeText(
                    this,
                    R.string.toast_informe_peso,
                    Toast.LENGTH_LONG
                ).show()
            }else if(edit_idade.text.toString().isEmpty()){
                Toast.makeText(
                    this,
                    R.string.toast_informe_idade,
                    Toast.LENGTH_LONG
                ).show()
            }else{
                // calcular
                val peso = edit_peso.text.toString().toDouble()
                val idade = edit_idade.text.toString().toInt()
                calcularIngestaoDiaria.CalcularMl(peso,idade)
                resultadoMl = calcularIngestaoDiaria.RESULTADOML()
                txt_resultado.text = resultadoMl.toString() + " ml"
            }
        }
        ic_redefinirDados.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(R.string.redefinir_dados)
            alertDialog.setMessage(R.string.mensagem)
            alertDialog.setCancelable(false)
            alertDialog.setPositiveButton("Ok") { dialogInterface, i ->
                edit_peso.setText("")
                edit_idade.setText("")
                txt_resultado.text = ""

            }
            alertDialog.setNegativeButton("Cancelar") { dialogInterface, i ->


            }
            val dialog = alertDialog.create()
            dialog.show()
        }
        // botao alarme
        bt_alarme.setOnClickListener {
            if(!txt_hora.text.toString().isEmpty() && !txt_minutos.text.toString().isEmpty()){
                val intent = Intent(AlarmClock.ACTION_SET_ALARM)
                intent.putExtra(AlarmClock.EXTRA_HOUR, txt_hora.text.toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MINUTES, txt_minutos.text.toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MESSAGE,getString(R.string.mensagem_alerta))
                startActivity(intent)
                if (intent.resolveActivity(packageManager)!= null){
                    startActivity(intent)
                }
            }

        }

        // butao lembrete
        bt_lembrete.setOnClickListener {
            calendario = Calendar.getInstance()
            horaAtual = calendario.get(Calendar.HOUR_OF_DAY)
            minutosAtuais = calendario.get(Calendar.MINUTE)
            timePickerDialog = TimePickerDialog(this,
                {timePicker:TimePicker,hourOfDay:Int, minutes:Int ->
                    txt_hora.text = String.format("%02d",hourOfDay)
                    txt_minutos.text = String.format("%02d", minutes)
                },horaAtual, minutosAtuais,true)
            timePickerDialog.show()
        }


    }
    private  fun inicializarComponentes(){
        edit_peso = findViewById(R.id.edit_peso)
        edit_idade = findViewById(R.id.edit_idade)
        bt_calcular = findViewById(R.id.btnCalcular)
        txt_resultado = findViewById(R.id.txt_resultado_ml)
        ic_redefinirDados = findViewById(R.id.ic_redefinir)
        bt_lembrete = findViewById(R.id.btn_definir_lembrete)
        bt_alarme = findViewById(R.id.btn_alarme)
        txt_hora = findViewById(R.id.txt_hora)
        txt_minutos = findViewById(R.id.txt_minutos)

    }
}