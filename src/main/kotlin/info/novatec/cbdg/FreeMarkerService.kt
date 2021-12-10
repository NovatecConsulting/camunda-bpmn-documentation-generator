package info.novatec.cbdg

import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import info.novatec.cbdg.models.BpmnObject
import java.io.FileWriter
import java.nio.file.FileAlreadyExistsException
import java.util.Locale
import kotlin.io.path.Path
import kotlin.io.path.createDirectories

/**
 * A simple singleton service to create an output HTML file from a given input template and [models.BpmnObject]
 */
object FreeMarkerService {
    private var cfg = createDefaultConfiguration()

    /**
     * Creates an output html file from a given template file fills it with the [input] data.
     * @param input input data.
     * @param templateName name of the template file to be used, will be prefixed with the default path "templates/".
     * @param outputPath path to the output file to be created by this method, defaults to "out/[templateName].html".
     * @param config Configuration extension function to temporarily alter the configuration for the generation.
     * The configuration is replaced by the previous configuration after the generation is done.
     */
    fun writeTemplate(
        input: BpmnObject,
        templateName: String,
        outputPath: String = "out/$templateName.html",
        config: (Configuration.() -> Unit)? = null
    ) {
        // Null-checks the config function in case there is no additional configuration needed
        val oldConfig = config?.let(FreeMarkerService::configure) ?: cfg
        val filePath = Path(outputPath)
        try {
            filePath.parent.createDirectories()
        } catch (e: FileAlreadyExistsException) {
            /* This method throws an exception if a directory along the path already exists, but generates missing directories anyways.
             * Thus, the exception can be ignored unless the information about already existing directories would be required.
             */
        }
        FileWriter(filePath.toFile()).use {
            cfg.getTemplate(templateName).process(mapOf("bpmn" to input), it)
        }
        cfg = oldConfig
    }

    /**
     * Replaces the current configuration with a boilerplate one and further configures it with the given parameter.
     * @param configurationLambda Further configures the boilerplate configuration.
     * @return the previous configuration.
     */
    private fun configure(configurationLambda: Configuration.() -> Unit): Configuration {
        val originalConfig = cfg
        cfg = createDefaultConfiguration()
        cfg.configurationLambda()
        return originalConfig
    }

    /**
     * Creates a default configuration that can be used as a boilerplate.
     */
    private fun createDefaultConfiguration() = Configuration(Configuration.VERSION_2_3_31).apply {
        setClassForTemplateLoading(FreeMarkerService::class.java, "templates")
        defaultEncoding = "UTF-8"
        locale = Locale.GERMAN
        templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
    }
}
