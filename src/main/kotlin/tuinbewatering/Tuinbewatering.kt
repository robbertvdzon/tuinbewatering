package tuinbewatering

fun main(args: Array<String>) {
    val dbFirestore = FirebaseConfig.initializeFirestore()
    FirebaseListener.processCommands(dbFirestore){
        Processor.process(it)
    }
    // TODO: control display en process knoppen van kastje
    Util.waitForever()
}






