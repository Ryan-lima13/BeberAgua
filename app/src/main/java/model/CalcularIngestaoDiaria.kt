package model

class CalcularIngestaoDiaria {
    private  var ML_JOVEM = 40.0
    private  var ML_ADULTO = 35
    private  var ML_IDOSO = 30
    private  var ML_MAIS_DE_66_ANOS = 25

    private  var resultadoMl = 0.0
    private  var resultadoTotal = 0.0

    fun CalcularMl(peso:Double, idade:Int){
        if(idade <= 17){
            resultadoMl = peso * ML_JOVEM
            resultadoTotal = resultadoMl

        }else if(idade <= 55){
            resultadoMl = peso * ML_ADULTO
            resultadoTotal = resultadoMl
        }else if( idade <= 65){
            resultadoMl = peso * ML_IDOSO
            resultadoTotal = resultadoMl
        }else{
            resultadoMl = peso * ML_MAIS_DE_66_ANOS
            resultadoTotal = resultadoMl
        }

    }
    fun RESULTADOML():Double{
        return  resultadoTotal

    }

}