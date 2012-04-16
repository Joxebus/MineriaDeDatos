import mineria.datos.Configuracion
import mineria.datos.Registro
import mineria.datos.RegresionLineal
import mineria.datos.VecinosCercanos
import mineria.datos.VecinosCercanosSeleccionados

class BootStrap {

    def init = { servletContext ->
        if(!Configuracion.count()){
            Configuracion.list().each{ it.delete(failOnError:true) }
        }

        if(!Registro.count()){
            Registro.list().each{ it.delete(failOnError:true) }
        }

        if(!RegresionLineal.count()){
            RegresionLineal.list().each{ it.delete(failOnError:true) }
        }

        if(!VecinosCercanos.count()){
            VecinosCercanos.list().each{ it.delete(failOnError:true) }
        }

        if(!VecinosCercanosSeleccionados.count()){
            VecinosCercanosSeleccionados.list().each{ it.delete(failOnError:true) }
        }
    }
    def destroy = {

    }
}
