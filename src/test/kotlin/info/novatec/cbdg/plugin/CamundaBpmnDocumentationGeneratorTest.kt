package info.novatec.cbdg.plugin

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CamundaBpmnDocumentationGeneratorTest {

    @Test
    fun generateTest() {
        val project: Project = ProjectBuilder.builder().build()

        project.getPluginManager().apply("info.novatec.cbdg")

        assertTrue(project.getPluginManager().hasPlugin("info.novatec.cbdg"))
        assertNotNull(project.getTasks().getByName("generate"))
    }
}
