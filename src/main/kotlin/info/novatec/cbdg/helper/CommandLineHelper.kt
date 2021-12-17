package info.novatec.cbdg.helper

import java.io.File
import java.lang.ProcessBuilder.Redirect.INHERIT
import java.util.concurrent.TimeUnit.MINUTES

class CommandLineHelper {

    companion object {

        /**
         * Helper method to execute a certain command in a certain directory.
         *
         * @receiver the command that is executed in the workingDir
         * @param workingDir the directory in which the command is executed
         */
        infix fun String.runCommand(workingDir: File) {
            ProcessBuilder(*split(" ").toTypedArray())
                .directory(workingDir)
                .redirectOutput(INHERIT)
                .redirectError(INHERIT)
                .start()
                .waitFor(60, MINUTES)
        }
    }
}
