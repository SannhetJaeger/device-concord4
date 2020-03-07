/**
 *  Concord 4 Integration via REST API Callback
 *
 *  Make sure and publish smartapp after pasting in code.
 *  Author: Scott Dozier
 */
definition(
    name: "Concord 4 Integration",
    namespace: "sannhetjaeger",
    author: "Sannhet Jaeger",
    description: "Handles the REST callback from concord and set virtual devices",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Labs/Cat-ST-Labs.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Labs/Cat-ST-Labs@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Labs/Cat-ST-Labs@3x.png")

preferences {
    section("Which is your Concord4?") {
		input "concord4", "capability.lock"
	}
	section("Zones") {
		input "zone1", "capability.contactSensor", title:"Zone 1"
        input "zone2", "capability.contactSensor", title:"Zone 2"
        input "zone3", "capability.contactSensor", title:"Zone 3"
        input "zone4", "capability.contactSensor", title:"Zone 4"
        input "zone5", "capability.contactSensor", title:"Zone 5"
        input "zone6", "capability.contactSensor", title:"Zone 6"
 		input "zone7", "capability.contactSensor", title:"Zone 7"
        input "zone8", "capability.contactSensor", title:"Zone 8"
        input "zone9", "capability.contactSensor", title:"Zone 9"
        input "zone10", "capability.contactSensor", title:"Zone 10"
        input "zone11", "capability.contactSensor", title:"Zone 11"
        input "zone12", "capability.contactSensor", title:"Zone 12"
        input "zone13", "capability.contactSensor", title:"Zone 13"
        input "zone14", "capability.contactSensor", title:"Zone 14"
        input "zone15", "capability.contactSensor", title:"Zone 15"
        input "zone16", "capability.contactSensor", title:"Zone 16"
        input "zone17", "capability.contactSensor", title:"Zone 17"
        input "zone18", "capability.contactSensor", title:"Zone 18"
        input "zone19", "capability.contactSensor", title:"Zone 19"
        input "zone20", "capability.contactSensor", title:"Zone 20"
		input "zone21", "capability.contactSensor", title:"Zone 21"
        input "zone22", "capability.contactSensor", title:"Zone 22"
        input "zone23", "capability.contactSensor", title:"Zone 23"
        input "zone24", "capability.contactSensor", title:"Zone 24"
        input "zone25", "capability.contactSensor", title:"Zone 25"
        input "zone26", "capability.contactSensor", title:"Zone 26"
 		input "zone27", "capability.contactSensor", title:"Zone 27"
        input "zone28", "capability.contactSensor", title:"Zone 28"
        input "zone29", "capability.contactSensor", title:"Zone 29"
        input "zone30", "capability.contactSensor", title:"Zone 30"
        input "zone31", "capability.contactSensor", title:"Zone 31"
        input "zone32", "capability.contactSensor", title:"Zone 32"
        input "zone33", "capability.contactSensor", title:"Zone 33"
        input "zone34", "capability.contactSensor", title:"Zone 34"
        input "zone35", "capability.contactSensor", title:"Zone 35"
        input "zone36", "capability.contactSensor", title:"Zone 36"
 		input "zone37", "capability.contactSensor", title:"Zone 37"
        input "zone38", "capability.contactSensor", title:"Zone 38"
        input "zone39", "capability.contactSensor", title:"Zone 39"
        input "zone40", "capability.contactSensor", title:"Zone 40"
		input "zone41", "capability.contactSensor", title:"Zone 41"
        input "zone42", "capability.contactSensor", title:"Zone 42"
        input "zone43", "capability.contactSensor", title:"Zone 43"
        input "zone44", "capability.contactSensor", title:"Zone 44"
        input "zone45", "capability.contactSensor", title:"Zone 45"
        input "zone46", "capability.contactSensor", title:"Zone 46"
 		input "zone47", "capability.contactSensor", title:"Zone 47"
        input "zone48", "capability.contactSensor", title:"Zone 48"
        input "zone49", "capability.contactSensor", title:"Zone 49"
        input "zone50", "capability.contactSensor", title:"Zone 50"
		input "zone51", "capability.contactSensor", title:"Zone 51"
        input "zone52", "capability.contactSensor", title:"Zone 52"
        input "zone53", "capability.contactSensor", title:"Zone 53"
        input "zone54", "capability.contactSensor", title:"Zone 54"
        input "zone55", "capability.contactSensor", title:"Zone 55"
        input "zone56", "capability.contactSensor", title:"Zone 56"
 		input "zone57", "capability.contactSensor", title:"Zone 57"
        input "zone58", "capability.contactSensor", title:"Zone 58"
        input "zone59", "capability.contactSensor", title:"Zone 59"
        input "zone60", "capability.contactSensor", title:"Zone 60"
		input "zone61", "capability.contactSensor", title:"Zone 61"
        input "zone62", "capability.contactSensor", title:"Zone 62"
        input "zone63", "capability.contactSensor", title:"Zone 63"
        input "zone64", "capability.contactSensor", title:"Zone 64"
        input "zone65", "capability.contactSensor", title:"Zone 65"
	}
    section("Presence Options") {
        input "autoArmDoorLock", "capability.lock", title: "Arm when door locked and all away and disarm on return with code?", required: false
        input "whoIsAway", "capability.presenceSensor", title: "When who is away?", multiple: true, required: false
    }
    section( "Notifications" ) {
        input "sendPushMessage", "enum", title: "Send a push notification?", options:["Yes", "No"], required: false
        input "phoneNumber", "phone", title: "Send a text message?", required: false
    }
}

mappings {
	path("/concord/:id/:item/:state") {
		action: [
			GET: "updateStatus"
		]
	}

}

void updateStatus() {
    log.debug("updateStatus params: ${params}")
	if (params.item == 'armstatus')
    {
        updateConcord4ArmStatus()
    }
    else if (params.item.contains('zone'))
    {
        updateConcord4ZoneStatus()
    }
}

void updateConcord4ArmStatus() {
	def armState = params.state
    def device = concord4.find { it.id == params.id }
    state.concord_id = params.id
	if (!device) {
			httpError(404, "Device not found")
		}
    log.debug("state: ${armState}")
    if(armState == "disarmed")
    {
        device.update("armStatus","Off")
        device.update("lock","unlocked")
        sendLocationEvent(name: "alarmSystemStatus", value: "off")
    }
    else if(armState == "armed_away")
    {
        device.update("armStatus","Away")
        device.update("lock","locked")
        sendLocationEvent(name: "alarmSystemStatus", value: "away")
    }
    else if(armState == "armed_stay")
    {
        device.update("armStatus","Stay")
        device.update("lock","locked")
        sendLocationEvent(name: "alarmSystemStatus", value: "stay")
    }
}

void updateConcord4ZoneStatus() {
    log.debug("zonestatus params: ${params}")
    state.concord_id = params.id
	def zoneState = params.state
	def zone = params.item

    def device = concord4.find { it.id == params.id }
	if (!device) {
			httpError(404, "Device not found")
		}
    else
    {
        device.update("${zone}",zoneState)
    }
}
private void update(devices) {
	log.debug "update, request: params: ${params}, devices: $devices.id"
	//def command = request.JSON?.command
    def param = params.param
    def state = params.state
    //let's create a toggle option here
	if (command)
    {
		def device = concord4.find { it.id == params.id }
		if (!device) {
			httpError(404, "Device not found")
		} else {
        	device.update(param,state)
		}
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
	subscribe(location, "alarmSystemStatus", alarmHandler)
    // Changed from 25 to 65
	for(int i=1; i<=65; i++ )
    {
		subscribe( concord4, "zone${i}.open", zoneOpen )
    	subscribe( concord4, "zone${i}.closed", zoneClose )
    }

    if (autoArmDoorLock != Null && whoIsAway != Null) {
        subscribe(autoArmDoorLock, "lock", doorHandler)
    }
}

def doorHandler(evt) {
    if (evt.value == "locked") {
        log.debug "door locked, waiting 10 minutes to check presence for ARMING"
        runIn(60*10, checkPresenceArm)
    } else if (evt.value == "unlocked") {
        // Check for manual unlock which we don't disarm for.
        def isManual = false
        if ((evt.data == "") || (evt.data == null)) {
            isManual = true
        }
        if (isManual == false) {
            log.debug "door unlocked with code, will disarm"
            sendLocationEvent(name: "alarmSystemStatus", value: "off")
        } else {
            log.debug "door unlocked manually, will NOT disarm"
        }
    }
}

def checkPresenceArm() {
    if (whoIsAway != Null) {
        log.debug "checking presence to ARM, currentPresence: ${whoIsAway.currentPresence}"
        def findAway = whoIsAway.findAll{it.currentPresence != "present"}
        if (findAway.size() == whoIsAway.size()) {
            sendLocationEvent(name: "alarmSystemStatus", value: "away")
        }
    }
}

def alarmHandler(evt) {
    log.debug "Alarm Handler evt.value: ${evt.value}"
    def device = concord4.find { it.id == state.concord_id }
    log.debug("alarmHandler device.lock: ${device.currentValue("lock")}")
    if(evt.value == "off")
    {
        if (device.currentValue("lock") != "unlocked")
        {
            device.unlock()
            device.update("armStatus","Off")
        }
    }
    else if(evt.value == "away")
    {
        if (device.currentValue("lock") != "locked")
        {
            device.armaway()
            device.update("armStatus","Away")
        }
    }
    else if(evt.value == "stay")
    {
        if (device.currentValue("lock") != "locked")
        {
            device.armstay()
            device.update("armStatus","Stay")
        }
    }
}
def zoneOpen(evt)
{
	log.debug "Setting Device Open"   
    switch( evt.name )
    {
        case "zone1":
        zone1.open( getZoneName(evt.name) )
        break;
        case "zone2":
        zone2.open( getZoneName(evt.name) )
        break;
        case "zone3":
        zone3.open( getZoneName(evt.name) )
        break;
        case "zone4":
        zone4.open( getZoneName(evt.name) )
        break;
        case "zone5":
        zone5.open( getZoneName(evt.name) )
        break;
        case "zone6":
        zone6.open( getZoneName(evt.name) )
        break;        
        case "zone7":
        zone7.open( getZoneName(evt.name) )
        break;
        case "zone8":
        zone8.open( getZoneName(evt.name) )
        break;
        case "zone9":
        zone9.open( getZoneName(evt.name) )
        break;
        case "zone10":
        zone10.open( getZoneName(evt.name) )
        break;    
        case "zone11":
        zone11.open( getZoneName(evt.name) )
        break;
        case "zone12":
        zone12.open( getZoneName(evt.name) )
        break;        
        case "zone13":
        zone13.open( getZoneName(evt.name) )
        break;
        case "zone14":
        zone14.open( getZoneName(evt.name) )
        break;        
        case "zone15":
        zone15.open( getZoneName(evt.name) )
        break;  
        case "zone16":
        zone16.open( getZoneName(evt.name) )
        break;        
        case "zone17":
        zone17.open( getZoneName(evt.name) )
        break;
        case "zone18":
        zone18.open( getZoneName(evt.name) )
        break;
        case "zone19":
        zone19.open( getZoneName(evt.name) )
        break;
        case "zone20":
        zone20.open( getZoneName(evt.name) )
        break;    
        case "zone21":
        zone21.open( getZoneName(evt.name) )
        break;
        case "zone22":
        zone22.open( getZoneName(evt.name) )
        break;
        case "zone23":
        zone23.open( getZoneName(evt.name) )
        break;
        case "zone24":
        zone24.open( getZoneName(evt.name) )
        break;
        case "zone25":
        zone25.open( getZoneName(evt.name) )
        break;
        case "zone26":
        zone26.open( getZoneName(evt.name) )
        break;        
        case "zone27":
        zone27.open( getZoneName(evt.name) )
        break;
        case "zone28":
        zone28.open( getZoneName(evt.name) )
        break;
        case "zone29":
        zone29.open( getZoneName(evt.name) )
        break;
        case "zone30":
        zone30.open( getZoneName(evt.name) )
        break;    
        case "zone31":
        zone31.open( getZoneName(evt.name) )
        break;
        case "zone32":
        zone32.open( getZoneName(evt.name) )
        break;
        case "zone33":
        zone33.open( getZoneName(evt.name) )
        break;
        case "zone34":
        zone34.open( getZoneName(evt.name) )
        break;
        case "zone35":
        zone35.open( getZoneName(evt.name) )
        break;
        case "zone36":
        zone36.open( getZoneName(evt.name) )
        break;        
        case "zone37":
        zone37.open( getZoneName(evt.name) )
        break;
        case "zone38":
        zone8.open( getZoneName(evt.name) )
        break;
        case "zone39":
        zone39.open( getZoneName(evt.name) )
        break;
        case "zone40":
        zone40.open( getZoneName(evt.name) )
        break;    
        case "zone41":
        zone41.open( getZoneName(evt.name) )
        break;
        case "zone42":
        zone42.open( getZoneName(evt.name) )
        break;
        case "zone43":
        zone43.open( getZoneName(evt.name) )
        break;
        case "zone44":
        zone44.open( getZoneName(evt.name) )
        break;
        case "zone45":
        zone45.open( getZoneName(evt.name) )
        break;
        case "zone46":
        zone46.open( getZoneName(evt.name) )
        break;        
        case "zone47":
        zone47.open( getZoneName(evt.name) )
        break;
        case "zone48":
        zone48.open( getZoneName(evt.name) )
        break;
        case "zone49":
        zone49.open( getZoneName(evt.name) )
        break;
        case "zone50":
        zone50.open( getZoneName(evt.name) )
        break;    
        case "zone51":
        zone51.open( getZoneName(evt.name) )
        break;
        case "zone52":
        zone52.open( getZoneName(evt.name) )
        break;
        case "zone53":
        zone53.open( getZoneName(evt.name) )
        break;
        case "zone54":
        zone54.open( getZoneName(evt.name) )
        break;
        case "zone55":
        zone55.open( getZoneName(evt.name) )
        break;
        case "zone56":
        zone56.open( getZoneName(evt.name) )
        break;        
        case "zone57":
        zone57.open( getZoneName(evt.name) )
        break;
        case "zone58":
        zone58.open( getZoneName(evt.name) )
        break;
        case "zone59":
        zone59.open( getZoneName(evt.name) )
        break;
        case "zone60":
        zone60.open( getZoneName(evt.name) )
        break;    
        case "zone61":
        zone61.open( getZoneName(evt.name) )
        break;
        case "zone62":
        zone62.open( getZoneName(evt.name) )
        break;
        case "zone63":
        zone63.open( getZoneName(evt.name) )
        break;
        case "zone64":
        zone64.open( getZoneName(evt.name) )
        break;
        case "zone65":
        zone65.open( getZoneName(evt.name) )
        break;
    }

    // If the alarm is armed, send a notification if required.
    def device = concord4.find { it.id == state.concord_id }
    if (device.currentValue("lock") != "unlocked") {
    	def message = "Alarm is ARMED and ''" + getZoneName(evt.name) + "'' has OPENED!"
        sendNotificationEvent(message)
        if (sendPushMessage == 'Yes') {
            sendPush(message)
        }
        if (phoneNumber != Null) {
            sendSms(phoneNumber, message)
        }
    }
}

def zoneClose(evt)
{
	log.debug "Setting Device Closed"
    switch( evt.name )
    {
        case "zone1":
        zone1.closed( getZoneName(evt.name) )
        break;
        case "zone2":
        zone2.closed( getZoneName(evt.name) )
        break;
        case "zone3":
        zone3.closed( getZoneName(evt.name) )
        break;
        case "zone4":
        zone4.closed( getZoneName(evt.name) )
        break;
        case "zone5":
        zone5.closed( getZoneName(evt.name) )
        break;
        case "zone6":
        zone6.closed( getZoneName(evt.name) )
        break;        
        case "zone7":
        zone7.closed( getZoneName(evt.name) )
        break;
        case "zone8":
        zone8.closed( getZoneName(evt.name) )
        break;
        case "zone9":
        zone9.closed( getZoneName(evt.name) )
        break;
        case "zone10":
        zone10.closed( getZoneName(evt.name) )
        break;
        case "zone11":
        zone11.closed( getZoneName(evt.name) )
        break;
        case "zone12":
        zone12.closed( getZoneName(evt.name) )
        break;
        case "zone13":
        zone13.closed( getZoneName(evt.name) )
        break;
        case "zone14":
        zone14.closed( getZoneName(evt.name) )
        break;        
        case "zone15":
        zone15.closed( getZoneName(evt.name) )
        break;
        case "zone16":
        zone16.closed( getZoneName(evt.name) )
        break;        
        case "zone17":
        zone17.closed( getZoneName(evt.name) )
        break;
        case "zone18":
        zone18.closed( getZoneName(evt.name) )
        break;
        case "zone19":
        zone19.closed( getZoneName(evt.name) )
        break;
        case "zone20":
        zone20.closed( getZoneName(evt.name) )
        break;
        case "zone21":
        zone21.closed( getZoneName(evt.name) )
        break;
        case "zone22":
        zone22.closed( getZoneName(evt.name) )
        break;
        case "zone23":
        zone23.closed( getZoneName(evt.name) )
        break;
        case "zone24":
        zone24.closed( getZoneName(evt.name) )
        break;
        case "zone25":
        zone25.closed( getZoneName(evt.name) )
        break;
        case "zone26":
        zone26.closed( getZoneName(evt.name) )
        break;        
        case "zone27":
        zone27.closed( getZoneName(evt.name) )
        break;
        case "zone28":
        zone28.closed( getZoneName(evt.name) )
        break;
        case "zone29":
        zone29.closed( getZoneName(evt.name) )
        break;
        case "zone30":
        zone30.closed( getZoneName(evt.name) )
        break;
        case "zone31":
        zone31.closed( getZoneName(evt.name) )
        break;
        case "zone32":
        zone32.closed( getZoneName(evt.name) )
        break;
        case "zone33":
        zone33.closed( getZoneName(evt.name) )
        break;
        case "zone34":
        zone34.closed( getZoneName(evt.name) )
        break;
        case "zone35":
        zone35.closed( getZoneName(evt.name) )
        break;
        case "zone36":
        zone36.closed( getZoneName(evt.name) )
        break;        
        case "zone37":
        zone37.closed( getZoneName(evt.name) )
        break;
        case "zone38":
        zone38.closed( getZoneName(evt.name) )
        break;
        case "zone39":
        zone39.closed( getZoneName(evt.name) )
        break;
        case "zone40":
        zone40.closed( getZoneName(evt.name) )
        break;
        case "zone41":
        zone41.closed( getZoneName(evt.name) )
        break;
        case "zone42":
        zone42.closed( getZoneName(evt.name) )
        break;
        case "zone43":
        zone43.closed( getZoneName(evt.name) )
        break;
        case "zone44":
        zone44.closed( getZoneName(evt.name) )
        break;
        case "zone45":
        zone45.closed( getZoneName(evt.name) )
        break;
        case "zone46":
        zone46.closed( getZoneName(evt.name) )
        break;        
        case "zone47":
        zone47.closed( getZoneName(evt.name) )
        break;
        case "zone48":
        zone48.closed( getZoneName(evt.name) )
        break;
        case "zone49":
        zone49.closed( getZoneName(evt.name) )
        break;
        case "zone50":
        zone50.closed( getZoneName(evt.name) )
        break;
        case "zone51":
        zone51.closed( getZoneName(evt.name) )
        break;
        case "zone52":
        zone52.closed( getZoneName(evt.name) )
        break;
        case "zone53":
        zone53.closed( getZoneName(evt.name) )
        break;
        case "zone54":
        zone54.closed( getZoneName(evt.name) )
        break;
        case "zone55":
        zone55.closed( getZoneName(evt.name) )
        break;
        case "zone56":
        zone56.closed( getZoneName(evt.name) )
        break;        
        case "zone57":
        zone57.closed( getZoneName(evt.name) )
        break;
        case "zone58":
        zone58.closed( getZoneName(evt.name) )
        break;
        case "zone59":
        zone59.closed( getZoneName(evt.name) )
        break;
        case "zone60":
        zone60.closed( getZoneName(evt.name) )
        break;
        case "zone61":
        zone61.closed( getZoneName(evt.name) )
        break;
        case "zone62":
        zone62.closed( getZoneName(evt.name) )
        break;
        case "zone63":
        zone63.closed( getZoneName(evt.name) )
        break;
        case "zone64":
        zone64.closed( getZoneName(evt.name) )
        break;
        case "zone65":
        zone65.closed( getZoneName(evt.name) )
        break;
    }

    // If the alarm is armed, send a notification if required.
    def device = concord4.find { it.id == state.concord_id }
    if (device.currentValue("lock") != "unlocked") {
    	def message = "Alarm is ARMED and ''" + getZoneName(evt.name) + "'' has OPENED!"
        sendNotificationEvent(message)
        if (sendPushMessage == 'Yes') {
            sendPush(message)
        }
        if (phoneNumber != Null) {
            sendSms(phoneNumber, message)
        }
    }
}

def getZoneName(name) {
    switch (name) 
    {
        case "zone1":
        return "Zone 1"
        break;
        case "zone2":
        return "Zone 2"
        break;
        case "zone3":
        return "Zone 3"
        break;
        case "zone4":
        return "Zone 4"
        break;
        case "zone5":
        return "Zone 5"
        break;
        case "zone6":
        return "Zone 6"
        break;        
        case "zone7":
        return "Zone 7"
        break;
        case "zone8":
        return "Zone 8"
        break;
        case "zone9":
        return "Zone 9"
        break;
        case "zone10":
        return "Zone 10"
        break;
        case "zone11":
        return "Zone 11"
        break;
        case "zone12":
        return "Zone 12"
        break;
        case "zone13":
        return "Zone 13"
        break;
        case "zone14":
        return "Zone 14"
        break;
        case "zone15":
        return "Zone 15"
        break;
        case "zone16":
        return "Zone 16"
        break;        
        case "zone17":
        return "Zone 17"
        break;
        case "zone18":
        return "Zone 18"
        break;
        case "zone19":
        return "Zone 19"
        break;
        case "zone20":
        return "Zone 20"
        break;
        case "zone21":
        return "Zone 21"
        break;
        case "zone22":
        return "Zone 22"
        break;
        case "zone23":
        return "Zone 23"
        break;
        case "zone24":
        return "Zone 24"
        break;
        case "zone25":
        return "Zone 25"
        break;
        case "zone26":
        return "Zone 26"
        break;        
        case "zone27":
        return "Zone 27"
        break;
        case "zone28":
        return "Zone 28"
        break;
        case "zone29":
        return "Zone 29"
        break;
        case "zone30":
        return "Zone 30"
        break;
        case "zone31":
        return "Zone 31"
        break;
        case "zone32":
        return "Zone 32"
        break;
        case "zone33":
        return "Zone 33"
        break;
        case "zone34":
        return "Zone 34"
        break;
        case "zone35":
        return "Zone 35"
        break;
        case "zone36":
        return "Zone 36"
        break;        
        case "zone37":
        return "Zone 37"
        break;
        case "zone38":
        return "Zone 38"
        break;
        case "zone39":
        return "Zone 39"
        break;
        case "zone40":
        return "Zone 40"
        break;
        case "zone41":
        return "Zone 41"
        break;
        case "zone42":
        return "Zone 42"
        break;
        case "zone43":
        return "Zone 43"
        break;
        case "zone44":
        return "Zone 44"
        break;
        case "zone45":
        return "Zone 45"
        break;
        case "zone46":
        return "Zone 46"
        break;        
        case "zone47":
        return "Zone 47"
        break;
        case "zone48":
        return "Zone 48"
        break;
        case "zone49":
        return "Zone 49"
        break;
        case "zone50":
        return "Zone 50"
        break;
        case "zone51":
        return "Zone 51"
        break;
        case "zone52":
        return "Zone 52"
        break;
        case "zone53":
        return "Zone 53"
        break;
        case "zone54":
        return "Zone 54"
        break;
        case "zone55":
        return "Zone 55"
        break;
        case "zone56":
        return "Zone 56"
        break;        
        case "zone57":
        return "Zone 57"
        break;
        case "zone58":
        return "Zone 58"
        break;
        case "zone59":
        return "Zone 59"
        break;
        case "zone60":
        return "Zone 60"
        break;
        case "zone61":
        return "Zone 61"
        break;
        case "zone62":
        return "Zone 62"
        break;
        case "zone63":
        return "Zone 63"
        break;
        case "zone64":
        return "Zone 64"
        break;
        case "zone65":
        return "Zone 65"
        break;
    }

    return "Unknown!"
}