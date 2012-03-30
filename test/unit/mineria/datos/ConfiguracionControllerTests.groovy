package mineria.datos



import org.junit.*
import grails.test.mixin.*

@TestFor(ConfiguracionController)
@Mock(Configuracion)
class ConfiguracionControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/configuracion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.configuracionInstanceList.size() == 0
        assert model.configuracionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.configuracionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.configuracionInstance != null
        assert view == '/configuracion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/configuracion/show/1'
        assert controller.flash.message != null
        assert Configuracion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/configuracion/list'


        populateValidParams(params)
        def configuracion = new Configuracion(params)

        assert configuracion.save() != null

        params.id = configuracion.id

        def model = controller.show()

        assert model.configuracionInstance == configuracion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/configuracion/list'


        populateValidParams(params)
        def configuracion = new Configuracion(params)

        assert configuracion.save() != null

        params.id = configuracion.id

        def model = controller.edit()

        assert model.configuracionInstance == configuracion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/configuracion/list'

        response.reset()


        populateValidParams(params)
        def configuracion = new Configuracion(params)

        assert configuracion.save() != null

        // test invalid parameters in update
        params.id = configuracion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/configuracion/edit"
        assert model.configuracionInstance != null

        configuracion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/configuracion/show/$configuracion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        configuracion.clearErrors()

        populateValidParams(params)
        params.id = configuracion.id
        params.version = -1
        controller.update()

        assert view == "/configuracion/edit"
        assert model.configuracionInstance != null
        assert model.configuracionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/configuracion/list'

        response.reset()

        populateValidParams(params)
        def configuracion = new Configuracion(params)

        assert configuracion.save() != null
        assert Configuracion.count() == 1

        params.id = configuracion.id

        controller.delete()

        assert Configuracion.count() == 0
        assert Configuracion.get(configuracion.id) == null
        assert response.redirectedUrl == '/configuracion/list'
    }
}
