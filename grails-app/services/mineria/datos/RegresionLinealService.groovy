package mineria.datos

import java.math.MathContext

class RegresionLinealService {
    def regresionA
    def regresionB
    def regresionY

    def calcularPromedioVarianzas(valorNuevoA, valorNuevoB){
        def listaRegistros = Registro.list()
        def totalRegistros = listaRegistros.size()
        def totalValoresB = 0
        def totalValoresC = 0
        listaRegistros.each{ registro ->
            totalValoresB += registro.valorB
            totalValoresC += registro.valorC
        }

        // Generar nuevos valores de regresion lineal
        def promedioValoresB = totalValoresB / totalRegistros
        def promedioValoresC = totalValoresC / totalRegistros

        listaRegistros.each{ registro ->
             cacularVarianza(registro,promedioValoresB,promedioValoresC)
        }

        // Obtener totales de regresion lineal
        def totalVarianzaXX = 0
        def totalVarianzaXY = 0
        RegresionLineal.list().each(){ regresionLineal ->
            totalVarianzaXX += regresionLineal.varianzaXX
            totalVarianzaXY += regresionLineal.varianzaXY
        }
        println "Sumatoria VarianzaX^2 $totalVarianzaXX"
        println "Sumatoria VarianzaXY $totalVarianzaXY"
        regresionB = totalVarianzaXY / totalVarianzaXX
        regresionA = promedioValoresC - (regresionB*promedioValoresB)
        regresionY = regresionA + (regresionB*valorNuevoB)
        return regresionY.round(new MathContext(3))

    }

    def cacularVarianza(registro, promedioX, promedioY){
        def regresionLineal = new RegresionLineal()
        def varianzaX = registro.valorB - promedioX
        def varianzaY = registro.valorC - promedioY
        regresionLineal.varianzaXX = varianzaX * varianzaX
        regresionLineal.varianzaYY = varianzaY * varianzaY
        regresionLineal.varianzaXY = varianzaX * varianzaY
        regresionLineal.save()
    }


}
