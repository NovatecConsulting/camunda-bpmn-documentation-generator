import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import models.BpmnObject
import java.io.FileWriter
import java.nio.file.FileAlreadyExistsException
import java.util.Locale
import kotlin.io.path.Path
import kotlin.io.path.createDirectories

/**
 * A simple singleton service to create an output HTML file from a given input template and [models.BpmnObject]
 */
object FreeMarkerService {
    private val cfg = Configuration(Configuration.VERSION_2_3_31)

    init {
        cfg.apply {
            setClassForTemplateLoading(FreeMarkerService::class.java, "templates")
            defaultEncoding = "UTF-8"
            locale = Locale.GERMAN
            templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
        }
    }

    /**
     * Creates an output html file from a given template file fills it with the [input] data.
     * @param input input data.
     * @param templateName name of the template file to be used, will be prefixed with the default path "templates/".
     * @param outputPath path to the output file to be created by this method, defaults to "out/[templateName].html".
     */
    fun writeTemplate(
        input: Map<String, BpmnObject>,
        templateName: String,
        outputPath: String = "out/$templateName.html"
    ) {
        val filePath = Path(outputPath)
        try {
            filePath.parent.createDirectories()
        } catch (e: FileAlreadyExistsException) {
            /* This method throws an exception if a directory along the path already exists, but generates missing directories anyways.
             * Thus, the exception can be ignored unless the information about already existing directories would be required.
             */
        }
        FileWriter(filePath.toFile()).use {
            cfg.getTemplate(templateName).process(input, it)
        }
    }
}
