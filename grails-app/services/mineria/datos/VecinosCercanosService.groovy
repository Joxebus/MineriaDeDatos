package mineria.datos

import java.math.MathContext

class VecinosCercanosService {

    def evaluarVecinosCercanos(BigDecimal valorB, BigDecimal valorC, Integer k){
        Registro.list().each { registro ->
             pitagoras(registro, valorB, valorC)
        }
        return evaluarClasificacion(k)
    }

    def pitagoras(Registro registro, BigDecimal valorNuevoB, BigDecimal valorNuevoC){
        BigDecimal valorRegistroB = new BigDecimal(valorNuevoB.subtract(registro.valorB)*valorNuevoB.subtract(registro.valorB)).round(new MathContext(3))
        BigDecimal valorRegistroC = new BigDecimal(valorNuevoC.subtract(registro.valorC)*valorNuevoC.subtract(registro.valorC)).round(new MathContext(3))
        BigDecimal distancia = new BigDecimal(Math.sqrt(valorRegistroB + valorRegistroC))
        new VecinosCercanos(
                clasificacion:registro.valorD,
                distancia:distancia
        ).save()
    }

    def evaluarClasificacion(Integer k){
        def listaClasificaciones = []
        def clasificacionCorrespondiente
        VecinosCercanos.list([sort: "distancia", order: "asc"]).each { vecino ->
            if(k>0){
                listaClasificaciones << vecino
                new VecinosCercanosSeleccionados(clasificacion:vecino.clasificacion)
                k--
            }
        }

        listaClasificaciones.each{ clasificacion ->
            if(!clasificacionCorrespondiente){
                clasificacionCorrespondiente = clasificacion
            }else if(VecinosCercanosSeleccionados.countByClasificacionLike(clasificacion) > VecinosCercanosSeleccionados.countByClasificacionLike(clasificacionCorrespondiente)){
                clasificacionCorrespondiente = clasificacion
            }
        }

        return clasificacionCorrespondiente
    }

}
