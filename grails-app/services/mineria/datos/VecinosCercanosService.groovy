package mineria.datos

import java.math.MathContext
import mineria.datos.VecinosCercanosSeleccionados

class VecinosCercanosService {
    BigDecimal valorRegistroB
    BigDecimal valorRegistroC
    BigDecimal distancia

    def evaluarVecinosCercanos(BigDecimal valorB, BigDecimal valorC, Integer k){
        Registro.list().each { registro ->
             pitagoras(registro, valorB, valorC)
        }
        return evaluarClasificacion(k)
    }

    def pitagoras(Registro registro, BigDecimal valorNuevoB, BigDecimal valorNuevoC){
        valorRegistroB = new BigDecimal(valorNuevoB.subtract(registro.valorB)*valorNuevoB.subtract(registro.valorB)).round(new MathContext(3))
        valorRegistroC = new BigDecimal(valorNuevoC.subtract(registro.valorC)*valorNuevoC.subtract(registro.valorC)).round(new MathContext(3))
        distancia = new BigDecimal(Math.sqrt(valorRegistroB + valorRegistroC))
        new VecinosCercanos(
                clasificacion:registro.valorD,
                distancia:distancia
        ).save()
    }

    def evaluarClasificacion(Integer k){
        def listaClasificaciones = []
        def clasificacionCorrespondiente = null
        VecinosCercanos.list([sort: "distancia", order: "asc"]).each { vecino ->
            if(k>0){
                println "Clasificación: "+vecino.clasificacion
                listaClasificaciones << vecino.clasificacion
                new VecinosCercanosSeleccionados(clasificacion:vecino.clasificacion).save(flush:true)
                k--
            }
        }

        println "***** Evaluando clasificación *****"
        listaClasificaciones.each{ clasificacion ->
            println "Clasificacion "+clasificacion
            if(!clasificacionCorrespondiente){
                println "Clasificación default "+ clasificacion
                clasificacionCorrespondiente = clasificacion
            }else if(VecinosCercanosSeleccionados.findAllWhere(clasificacion:clasificacion).size() > VecinosCercanosSeleccionados.findAllWhere(clasificacion:clasificacionCorrespondiente).size()){
                clasificacionCorrespondiente = clasificacion
            }
        }
        println "Clasificación correspondiente: "+clasificacionCorrespondiente
        return clasificacionCorrespondiente
    }

}
