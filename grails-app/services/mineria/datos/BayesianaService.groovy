package mineria.datos

import java.math.MathContext
import mineria.datos.Registro

class BayesianaService {
    def cantidadRegistros

    /**
     *
     *  Suponinedo que solo se tengan 4 columnas, este método
     *  envía una lista de elementos unicos al método que calcula
     *  su ocurrencia(probabilidad) en la base de datos
     */
    def probabilidad(){
        cantidadRegistros = Registro.count()
        def valores = []
        Registro.list().collect(valores){ it.valorD }
        valores.unique().each{ valorActual ->
            new Bayesiana(valor:valorActual, probabilidad: calcularProbabilidad(valorActual)).save(flush:true)
        }
    }

    /**
     *
     *  Este método genera las probabilidades de cada una de las columnas
     *  y con ello guarda un nuevo registro
     */
    private def calcularProbabilidad(valor){
        def total = 0
        def probabilidad = 0
        total = Registro.countByValorDLike(valor)
        probabilidad = total/cantidadRegistros
        return probabilidad
    }

    def inferenciaBayesiana = { valorA, valorB ->
        println valorA
        println valorB
        def probabilidadActual
        def bayesiana = Bayesiana.list()
        def cantidadValorA = 0
        def cantidadValorB = 0
        def cantidadValorX = 0
        bayesiana.each{
            println "Valor X = "+it.valor
            cantidadValorX = Registro.findAllByValorDLike(it.valor).size()
            println "Valor X cantidad = "+cantidadValorX
            println "Valor B cantidad = "+Registro.findAllWhere(valorB:new BigDecimal(valorA)).size()
            println "Valor C cantidad = "+Registro.findAllWhere(valorC:new BigDecimal(valorB)).size()
            cantidadValorA = Registro.findAllWhere(valorB:new BigDecimal(valorA)).size()/cantidadValorX
            cantidadValorB = Registro.findAllWhere(valorC:new BigDecimal(valorB)).size()/cantidadValorX
            new BayesianaTuplas(valor:it.valor, probabilidad:cantidadValorA*cantidadValorB).save(flush:true)
        }

        // Valor entre el que se dividen las probabilidades
        def probabilidadTotal = 0
        println "Valores Bayesiana"
        bayesiana.each(){
            probabilidadTotal += it.probabilidad
            println it.valor
        }

        // Calculo final de probabilidad
        def bayesianaTuplas = BayesianaTuplas.list()
        bayesiana.size().times{
            def numerador = bayesiana[it].probabilidad*bayesianaTuplas[it].probabilidad
            println "Numerador"+numerador
            println "Denominador"+probabilidadTotal
            println "Probabilidad"+(numerador/probabilidadTotal)
            new BayesianaResultados(valor:bayesiana[it].valor, probabilidad: numerador/probabilidadTotal).save(flush:true)
        }

        def resultado = BayesianaResultados.list([sort: "probabilidad", order: "desc"])[0]
        println "Probabilidad "+resultado.probabilidad
        return resultado.valor
    }


}
