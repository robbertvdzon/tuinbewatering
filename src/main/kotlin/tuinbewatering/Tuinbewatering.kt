package tuinbewatering

import tuinbewatering.common.FirebaseConfig
import tuinbewatering.common.FirebaseListener

private const val SERVICE_ACCOUNT_FILE =
    "/Users/robbertvanderzon/tuinbewatering-firebase-adminsdk-mdooy-b394f2553c.json"
private const val DATABASE_URL = "https://tuinbewatering.firebaseio.com"
private const val COLLECTION = "bewatering"
private const val COMMANDS_DOCUMENT = "commands"

fun main(args: Array<String>) {
    // initialize the components
    val firebaseConfig = FirebaseConfig(SERVICE_ACCOUNT_FILE, DATABASE_URL)
    val commandProcessor = BewateringCommandProcessor()
    val firebaseListener = FirebaseListener(COLLECTION, COMMANDS_DOCUMENT, commandProcessor)

    // instantiate the Firestore database and start listening for commands
    val dbFirestore = firebaseConfig.initializeFirestore()
    firebaseListener.processCommands(dbFirestore)

    // process display and listen for button presses
    // TODO: control display en process knoppen van kastje

    // wait forever
    Util.waitForever()
}






