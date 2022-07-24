package com.rlds.bebergua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import model.CalcularIngestaoDiaria

class MainActivity : AppCompatActivity() {
    private lateinit var edit_peso:EditText
    private  lateinit var edit_idade:EditText
    private  lateinit var bt_calcular:Button
    private  lateinit var txt_resultado:TextView
    private  lateinit var ic_redefinirDados: ImageView
    private  lateinit var  calcularIngestaoDiaria:CalcularIngestaoDiaria
    private  var resultadoMl = 0.0


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
    }
    private  fun inicializarComponentes(){
        edit_peso = findViewById(R.id.edit_peso)
        edit_idade = findViewById(R.id.edit_idade)
        bt_calcular = findViewById(R.id.btnCalcular)
        txt_resultado = findViewById(R.id.txt_resultado_ml)
        ic_redefinirDados = findViewById(R.id.ic_redefinir)
    }
}