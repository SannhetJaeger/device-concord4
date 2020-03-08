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
		input "zone1", "capability.contactSensor", title:"CZ01-Front Door"
        input "zone2", "capability.contactSensor", title:"CZ02-Interior Garage"
        input "zone3", "capability.contactSensor", title:"CZ03-Car Door 1"
        input "zone4", "capability.contactSensor", title:"CZ04-Car Door 2"
        input "zone5", "capability.contactSensor", title:"CZ05-Garage Back Door"
        input "zone6", "capability.contactSensor", title:"CZ06-Office Door"
 		input "zone7", "capability.contactSensor", title:"CZ07-Unused"
        input "zone8", "capability.contactSensor", title:"CZ08-Living Room North Door"
        input "zone9", "capability.contactSensor", title:"CZ09-Bedroom Door"
        input "zone10", "capability.contactSensor", title:"CZ10-Den North Door"
        input "zone11", "capability.contactSensor", title:"CZ11-Kitchen Door"
        input "zone12", "capability.contactSensor", title:"CZ12-Living Room South Door"
        input "zone13", "capability.contactSensor", title:"CZ13-Garage Motion"
        input "zone14", "capability.contactSensor", title:"CZ14-Office Motion"
        input "zone15", "capability.contactSensor", title:"CZ15-Downstairs Hallway Motion"
        input "zone16", "capability.contactSensor", title:"CZ16-Family Room Motion"
        input "zone17", "capability.contactSensor", title:"CZ17-Den Motion"
        input "zone18", "capability.contactSensor", title:"CZ18-Kitchen Motion"
        input "zone19", "capability.contactSensor", title:"CZ19-Living Room Motion"
        input "zone20", "capability.contactSensor", title:"CZ20-Keypad"
		input "zone21", "capability.contactSensor", title:"CZ21-Garage Window"
        input "zone22", "capability.contactSensor", title:"CZ22-Garage Window"
        input "zone23", "capability.contactSensor", title:"CZ23-Office Window"
        input "zone24", "capability.contactSensor", title:"CZ24-Office Window"
        input "zone25", "capability.contactSensor", title:"CZ25-Office Window"
        input "zone26", "capability.contactSensor", title:"CZ26-Utility Room Window"
 		input "zone27", "capability.contactSensor", title:"CZ27-Joseph Back Window"
        input "zone28", "capability.contactSensor", title:"CZ28-Joseph Side Window"
        input "zone29", "capability.contactSensor", title:"CZ29-Nicole Side Window"
        input "zone30", "capability.contactSensor", title:"CZ30-Nicole Front Window"
        input "zone31", "capability.contactSensor", title:"CZ31-Nicole Front Window"
        input "zone32", "capability.contactSensor", title:"CZ32-Sophie Front Window"
        input "zone33", "capability.contactSensor", title:"CZ33-Sophie Front Window"
        input "zone34", "capability.contactSensor", title:"CZ34-Family Room Window"
        input "zone35", "capability.contactSensor", title:"CZ35-Family Room Window"
        input "zone36", "capability.contactSensor", title:"CZ36-Family Room Window"
 		input "zone37", "capability.contactSensor", title:"CZ37-Bedroom Window"
        input "zone38", "capability.contactSensor", title:"CZ38-Bedroom Window"
        input "zone39", "capability.contactSensor", title:"CZ39-Bedroom Window"
        input "zone40", "capability.contactSensor", title:"CZ40-Bedroom Window"
		input "zone41", "capability.contactSensor", title:"CZ41-Bedroom Window"
        input "zone42", "capability.contactSensor", title:"CZ42-Bedroom Window"
        input "zone43", "capability.contactSensor", title:"CZ43-Bedroom Side Window"
        input "zone44", "capability.contactSensor", title:"CZ44-Bathroom Window"
        input "zone45", "capability.contactSensor", title:"CZ45-Bathroom Window"
        input "zone46", "capability.contactSensor", title:"CZ46-Den Window"
 		input "zone47", "capability.contactSensor", title:"CZ47-Den Window"
        input "zone48", "capability.contactSensor", title:"CZ48-Den Window"
        input "zone49", "capability.contactSensor", title:"CZ49-Den Window"
        input "zone50", "capability.contactSensor", title:"CZ50-Stephanie Window"
		input "zone51", "capability.contactSensor", title:"CZ51-Kitchen Back Window"
        input "zone52", "capability.contactSensor", title:"CZ52-Kitchen Back Window"
        input "zone53", "capability.contactSensor", title:"CZ53-Kitchen Side Window"
        input "zone54", "capability.contactSensor", title:"CZ54-Kitchen Side Window"
        input "zone55", "capability.contactSensor", title:"CZ55-Kitchen Front Window"
        input "zone56", "capability.contactSensor", title:"CZ56-Kitchen Front Window"
 		input "zone57", "capability.contactSensor", title:"CZ57-Dining Room Window"
        input "zone58", "capability.contactSensor", title:"CZ58-Dining Room Window"
        input "zone59", "capability.contactSensor", title:"CZ59-Living Room Window"
        input "zone60", "capability.contactSensor", title:"CZ60-Living Room Window"
		input "zone61", "capability.contactSensor", title:"CZ61-Living Room Window"
        input "zone62", "capability.contactSensor", title:"CZ62-Living Room Window"
        input "zone63", "capability.contactSensor", title:"CZ63-Den South Door"
        input "zone64", "capability.contactSensor", title:"CZ64-Unused"
        input "zone65", "capability.contactSensor", title:"CZ65-Unused"
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
        return "CZ01-Front Door"
        break;
        case "zone2":
        return "CZ02-Interior Garage"
        break;
        case "zone3":
        return "CZ03-Car Door 1"
        break;
        case "zone4":
        return "CZ04-Car Door 2"
        break;
        case "zone5":
        return "CZ05-Garage Back Door"
        break;
        case "zone6":
        return "CZ06-Office Door"
        break;        
        case "zone7":
        return "CZ07-Unused"
        break;
        case "zone8":
        return "CZ08-Living Room North Door"
        break;
        case "zone9":
        return "CZ09-Bedroom Door"
        break;
        case "zone10":
        return "CZ10-Den North Door"
        break;
        case "zone11":
        return "CZ11-Kitchen Door"
        break;
        case "zone12":
        return "CZ12-Living Room South Door"
        break;
        case "zone13":
        return "CZ13-Garage Motion"
        break;
        case "zone14":
        return "CZ14-Office Motion"
        break;
        case "zone15":
        return "CZ15-Downstairs Hallway Motion"
        break;
        case "zone16":
        return "CZ16-Family Room Motion"
        break;        
        case "zone17":
        return "CZ17-Den Motion"
        break;
        case "zone18":
        return "CZ18-Kitchen Motion"
        break;
        case "zone19":
        return "CZ19-Living Room Motion"
        break;
        case "zone20":
        return "CZ20-Keypad"
        break;
        case "zone21":
        return "CZ21-Garage Window"
        break;
        case "zone22":
        return "CZ22-Garage Window"
        break;
        case "zone23":
        return "CZ23-Office Window"
        break;
        case "zone24":
        return "CZ24-Office Window"
        break;
        case "zone25":
        return "CZ25-Office Window"
        break;
        case "zone26":
        return "CZ26-Utility Room Window"
        break;        
        case "zone27":
        return "CZ27-Joseph Back Window"
        break;
        case "zone28":
        return "CZ28-Joseph Side Window"
        break;
        case "zone29":
        return "CZ29-Nicole Side Window"
        break;
        case "zone30":
        return "CZ30-Nicole Front Window"
        break;
        case "zone31":
        return "CZ31-Nicole Front Window"
        break;
        case "zone32":
        return "CZ32-Sophie Front Window"
        break;
        case "zone33":
        return "CZ33-Sophie Front Window"
        break;
        case "zone34":
        return "CZ34-Family Room Window"
        break;
        case "zone35":
        return "CZ35-Family Room Window"
        break;
        case "zone36":
        return "CZ36-Family Room Window"
        break;        
        case "zone37":
        return "CZ37-Bedroom Window"
        break;
        case "zone38":
        return "CZ38-Bedroom Window"
        break;
        case "zone39":
        return "CZ39-Bedroom Window"
        break;
        case "zone40":
        return "CZ40-Bedroom Window"
        break;
        case "zone41":
        return "CZ41-Bedroom Window"
        break;
        case "zone42":
        return "CZ42-Bedroom Window"
        break;
        case "zone43":
        return "CZ43-Bedroom Side Window"
        break;
        case "zone44":
        return "CZ44-Bathroom Window"
        break;
        case "zone45":
        return "CZ45-Bathroom Window"
        break;
        case "zone46":
        return "CZ46-Den Window"
        break;        
        case "zone47":
        return "CZ47-Den Window"
        break;
        case "zone48":
        return "CZ48-Den Window"
        break;
        case "zone49":
        return "CZ49-Den Window"
        break;
        case "zone50":
        return "CZ50-Stephanie Window"
        break;
        case "zone51":
        return "CZ51-Kitchen Back Window"
        break;
        case "zone52":
        return "CZ52-Kitchen Back Window"
        break;
        case "zone53":
        return "CZ53-Kitchen Side Window"
        break;
        case "zone54":
        return "CZ54-Kitchen Side Window"
        break;
        case "zone55":
        return "CZ55-Kitchen Front Window"
        break;
        case "zone56":
        return "CZ56-Kitchen Front Window"
        break;        
        case "zone57":
        return "CZ57-Dining Room Window"
        break;
        case "zone58":
        return "CZ58-Dining Room Window"
        break;
        case "zone59":
        return "CZ59-Living Room Window"
        break;
        case "zone60":
        return "CZ60-Living Room Window"
        break;
        case "zone61":
        return "CZ61-Living Room Window"
        break;
        case "zone62":
        return "CZ62-Living Room Window"
        break;
        case "zone63":
        return "CZ63-Den South Door"
        break;
        case "zone64":
        return "CZ64-Unused"
        break;
        case "zone65":
        return "CZ65-Unused"
        break;
    }

    return "Unknown!"
}