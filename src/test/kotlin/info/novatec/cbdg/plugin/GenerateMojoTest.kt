package info.novatec.cbdg.plugin

import org.apache.maven.plugin.Mojo
import org.apache.maven.plugin.testing.AbstractMojoTestCase
import org.codehaus.plexus.util.ReaderFactory
import org.codehaus.plexus.util.xml.Xpp3Dom
import org.codehaus.plexus.util.xml.Xpp3DomBuilder
import org.junit.jupiter.api.Test
import java.io.File

class GenerateMojoTest : AbstractMojoTestCase() {

    private val testPom = "src/test/resources/pom.xml"

    @Test
    fun `Generate with Non-Default parameters`() {
        super.setUp()
        val testPomFile: File = getTestFile(testPom)
        assertNotNull(testPomFile)
        assertTrue(testPomFile.exists())

        val myMojo = lookupMojo("generate", testPomFile)
        assertNotNull(myMojo)
        myMojo.execute()

        assertNotNull(File("build/test-results/cbdg/html/diagram1.html"))
    }

    /**
     * this method is override, because the origin expects a maven-pom in project-root
     */
    @Throws(Exception::class)
    override fun lookupMojo(goal: String?, pom: File?): Mojo {
        val pluginPom = File(getBasedir(), testPom)
        val pluginPomDom = Xpp3DomBuilder.build(ReaderFactory.newXmlReader(pluginPom))
        val artifactId = pluginPomDom.getChild("artifactId").value
        val groupId = resolveFromRootThenParent(pluginPomDom, "groupId")
        val version = resolveFromRootThenParent(pluginPomDom, "version")
        val pluginConfiguration = extractPluginConfiguration(artifactId, pom)
        return lookupMojo(groupId, artifactId, version, goal, pluginConfiguration)
    }

    @Throws(java.lang.Exception::class)
    private fun resolveFromRootThenParent(pluginPomDom: Xpp3Dom, element: String): String? {
        var elementDom = pluginPomDom.getChild(element)
        // parent might have the group Id so resolve it
        if (elementDom == null) {
            val pluginParentDom = pluginPomDom.getChild("parent")
            elementDom = pluginParentDom?.getChild(element) ?: throw java.lang.Exception("unable to determine $element")
        }
        return elementDom.value
    }
}
