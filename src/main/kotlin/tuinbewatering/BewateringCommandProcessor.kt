package tuinbewatering

import tuinbewatering.common.CommandProcessor

class BewateringCommandProcessor: CommandProcessor {
    override fun process(command: String) {
        println("Processing command: $command")
    }
}