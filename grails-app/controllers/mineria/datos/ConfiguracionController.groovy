package mineria.datos

import org.springframework.dao.DataIntegrityViolationException

class ConfiguracionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def regresionLinealService
    def vecinosCercanosService

    def index() {
        if(!Configuracion.count())
            redirect(action: "create", params: params)
        else
            redirect(action: "list", params: params)
    }

    def list() {
        if(Configuracion.count()){
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            [registroInstanceList: Registro.list(params), registroInstanceTotal: Registro.count(),configuracion:Configuracion.list().get(0),registroNuevo:new Registro()]
        }else{
            flash.message = "No hay configuración disponible para mostrar"
            redirect(action: "create", params: params)
        }
    }

    def create() {
        if(params.id){
            def configuracionInstance = Configuracion.get(params.id)
            configuracionInstance.delete(flush:true)
            Registro.list().each{ registro ->
                registro.delete(flush:true)
            }
            flash.message = "La configuración anterior ha sido eliminada"
        }
        [configuracionInstance: new Configuracion(params)]
    }

    def save() {
        def i = 0
        def archivo = new File("temp.txt")
        archivo.delete()
        archivo << params.archivo.bytes
        archivo.eachLine{ linea ->
            println linea
            linea = linea.split(',')
            if(i==0){
                new Configuracion(
                        columnaA:linea[0],
                        columnaB:linea[1],
                        columnaC:linea[2],
                        columnaD:linea[3]
                ).save(flush:true)
                i++
            }else{
                new Registro(
                        valorA:linea[0],
                        valorB:linea[1],
                        valorC:linea[2],
                        valorD:linea[3]
                ).save(flush:true)
            }

        }
        archivo.delete()
		flash.message = message(code: 'default.created.message', args: [message(code: 'configuracion.label', default: 'Configuracion'), Configuracion.list().get(0).id])
        redirect(action: "list", params:params)
    }

    def edit() {
        def configuracionInstance = Configuracion.get(params.id)
        if (!configuracionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'configuracion.label', default: 'Configuracion'), params.id])
            redirect(action: "list")
            return
        }

        redirect (action:'create', params:params)
    }

    def delete() {
        def configuracionInstance = Configuracion.get(params.id)
        try {
            configuracionInstance.delete(flush:true)
            Registro.list().each{ registro ->
                registro.delete(flush:true)
            }
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'configuracion.label', default: 'Configuracion'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'configuracion.label', default: 'Configuracion'), params.id])
            redirect(action: "index")
        }
    }

    def regresionLineal = {
        def valorNuevoC  = regresionLinealService.calcularPromedioVarianzas(params.valorNuevoA, new BigDecimal(params.valorNuevoB))
        def registroNuevo = new Registro(
                valorA:params.valorNuevoA,
                valorB:params.valorNuevoB,
                valorC:valorNuevoC
        )
        flash.message = "Cálculo de valores por Regresión Lineal"
        clear()
        render (view:'list',model:[registroInstanceList: Registro.list(params), registroInstanceTotal: Registro.count(),
                configuracion:Configuracion.list().get(0),registroNuevo:registroNuevo])
    }

    def vecinosCercanos = {
        if(params.valorK){
            def valorNuevoC  = regresionLinealService.calcularPromedioVarianzas(params.valorNuevoA, new BigDecimal(params.valorNuevoB))
            def clasificacion = vecinosCercanosService.evaluarVecinosCercanos(new BigDecimal(params.valorNuevoB), new BigDecimal(valorNuevoC), params.valorK.toInteger())
            def registroNuevo = new Registro(
                    valorA:params.valorNuevoA,
                    valorB:params.valorNuevoB,
                    valorC:valorNuevoC,
                    valorD:clasificacion
            )

            flash.message = "Cálculo de valores por Vecinos Cercanos"
            clear()
            render (view:'list',model:[registroInstanceList: Registro.list(params), registroInstanceTotal: Registro.count(),
                    configuracion:Configuracion.list().get(0),registroNuevo:registroNuevo, valorK:params.valorK])
        }else{
            flash.error = "Se debe ingresar un valor de K para calcular los vecinos cercanos"
            def registroNuevo = new Registro(
                    valorA:params.valorNuevoA,
                    valorB:params.valorNuevoB
            )
            render (view:'list',model:[registroInstanceList: Registro.list(params), registroInstanceTotal: Registro.count(),
                    configuracion:Configuracion.list().get(0),registroNuevo:registroNuevo])
        }
    }

    def clear(){
        RegresionLineal.list().each{ it.delete() }
        VecinosCercanos.list().each{ it.delete() }
        VecinosCercanosSeleccionados.list().each{ it.delete() }
    }
}
