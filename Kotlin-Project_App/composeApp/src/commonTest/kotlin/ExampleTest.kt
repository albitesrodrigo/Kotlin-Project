import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ExampleTest {

    @Test
    fun sum_must_success() {
        // Given: dado cierto valor, objeto, variable
        val x = 5
        val y  = 10

        // When: cuando realizamos cierta tarea, cierta accion
        val result = x + y

        // Then: quiero obtener un resultado
        assertEquals(15, result)
    }

    @Test
    fun sum_must_fail() {
        // Given: dado cierto valor, objeto, variable
        val x = 5
        val y  = 10

        // When: cuando realizamos cierta tarea, cierta accion
        val result = x + y

        // Then: quiero obtener un resultado
        assertNotEquals(10, result)
    }
}