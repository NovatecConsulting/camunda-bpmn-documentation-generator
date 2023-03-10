package info.novatec.docu.generator

import info.novatec.cbdg.FreeMarkerService
import info.novatec.docu.parser.main.BpmnParser
import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.exists

class DocuGenerator {
    fun parseAndGenerate(
        templateFile: File,
        camundaBpmnDir: File,
        resultOutputDir: File,
        bpmnDiagramImageDir: File? = null
    ) {
        if (templateFile.name.equals("default.ftl")) {
            FileOutputStream(templateFile, false).use {
                javaClass.classLoader.getResourceAsStream("templates/default.ftl")
                    ?.transferTo(it) ?: throw FileNotFoundException("templates/default.ftl don't exist.")
            }
        }

        val log = LoggerFactory.getLogger("cbdg")
        val parser = BpmnParser()

        camundaBpmnDir.listFiles()?.forEach {
            log.info("Generating documentation for file ${it.absolutePath}")
            log.info("Using template ${templateFile.absolutePath}")

            val imageSrcPath = Path("${bpmnDiagramImageDir?.absolutePath}/${it.nameWithoutExtension}.png")
            val imageTargetPath = Path("${resultOutputDir.absolutePath}/images/${it.nameWithoutExtension}.png")
            imageTargetPath.parent.createDirectories()
            if (imageSrcPath.exists()) {
                Files.copy(imageSrcPath, imageTargetPath, StandardCopyOption.REPLACE_EXISTING)
            }

            val bpmnObject = parser.parseBpmnFile(it, "${it.nameWithoutExtension}.png")
            FreeMarkerService.writeTemplate(
                bpmnObject,
                templateFile.name,
                "${resultOutputDir.absolutePath}/${it.nameWithoutExtension}.html"
            ) {
                setDirectoryForTemplateLoading(templateFile.parentFile)
            }
            log.info("Output report into path ${resultOutputDir.absolutePath}")
        } ?: throw FileNotFoundException("${camundaBpmnDir.absolutePath} don't exist.")
        resultOutputDir.listFiles()?.forEach {
            log.info("Output: " + it.absolutePath)
        } ?: throw FileNotFoundException("${resultOutputDir.absolutePath} don't exist.")
    }
}
