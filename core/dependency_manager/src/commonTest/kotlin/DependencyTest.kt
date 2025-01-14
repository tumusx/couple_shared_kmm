import com.github.tumusx.savelist.dependency_manager.DependencyManager
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
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

    @Test
    fun when_removing_all_dependencies_verify_its_null() {
        DependencyManager.onRegisterDependency(false, "TEST")
        DependencyManager.clearAllDependencies()
        val dependency = DependencyManager.onGetDependency<Boolean>("TEST")
        assertEquals(expected = null, actual = dependency)
    }

    @Test
    fun when_insert_dependency_verify_return_it() {
        val dependency = DependencyManager.onRegisterDependency(false, "TEST")
        assertFalse(dependency)
    }
}