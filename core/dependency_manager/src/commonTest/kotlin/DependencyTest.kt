import com.github.tumusx.savelist.dependency_manager.DependencyManager
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DependencyTest {

    @Test
    fun whenInsertingDependency_verifyIfReturnsIt() {
        val dependency = DependencyManager.onRegisterDependency(true, "TEST")

        assertTrue(dependency)
    }

    @Test
    fun whenRemovingDependency_verifyIfItIsNull() {
        DependencyManager.onRegisterDependency(true, "TEST")
        DependencyManager.clearSpecificDependency("TEST")
        val dependency = DependencyManager.onGetDependency<Boolean>("TEST")

        assertEquals(expected = null, actual = dependency)
    }

}