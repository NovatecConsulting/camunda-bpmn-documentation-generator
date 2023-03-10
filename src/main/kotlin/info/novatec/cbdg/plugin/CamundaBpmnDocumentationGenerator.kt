package info.novatec.cbdg.plugin

import info.novatec.docu.generator.DocuGenerator
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import java.io.File

class CamundaBpmnDocumentationGenerator : Plugin<Project> {
    override fun apply(project: Project) {
        val configuration = project.extensions.create("cbdg", CamundaBpmnDocumentationGeneratorConfiguration::class.java)
        configuration.templateFile.convention(project.layout.buildDirectory.file("resources/main/templates/default.ftl").get().asFile)
        configuration.camundaBpmnDir.convention(project.layout.buildDirectory.dir("resources/main/bpmn").get().asFile)
        configuration.resultOutputDir.convention(project.layout.buildDirectory.dir("cbdg/html").get().asFile)
        configuration.bpmnDiagramImageDir?.convention(project.layout.buildDirectory.dir("resources/main/images").get().asFile)

        project.task("generate").doLast {
            DocuGenerator().parseAndGenerate(
                configuration.templateFile.get(),
                configuration.camundaBpmnDir.get(),
                configuration.resultOutputDir.get(),
                configuration.bpmnDiagramImageDir?.get()
            )
        }
    }
}

interface CamundaBpmnDocumentationGeneratorConfiguration {
    val templateFile: Property<File>
    val camundaBpmnDir: Property<File>
    val resultOutputDir: Property<File>
    val bpmnDiagramImageDir: Property<File>?
}
