/**
 *  Concord 4 Device Handler
 *	Enhanced 2020 SannhetJaeger
 *  Scott Dozier 4/1/2016
 */

metadata {
	// Automatically generated. Make future change here.
	definition (name: "Concord 4 Device Handler", namespace: "sannhetjaeger", author: "Sannhet Jaeger") {
        capability "Polling"
        capability "Lock"
        capability "Refresh"
        command "armstay"
        command "armaway"
        command "Disarm"
        command "armSilent"
        command "armLoud"
        command "armBypass"
        command "armRegular"
        command "update"
        command "setZonesClosed"
        
        attribute "armStatus", "string"
	}

	simulator {
		// TODO: define status and reply messages here
	}
    preferences {
        input("concord_server_ip_address", "text", title: "IP", description: "Concord 4 Server IP Address",defaultValue: "8.8.8.8")
        input("concord_server_port", "number", title: "Port", description: "Concord 4 Server Port Number (8066)",defaultValue: 8066)
        input("concord_server_api_password", "text", title: "API Password", description: "Concord 4 Server API PW",defaultValue: "")
    }
	tiles (scale: 2){
      multiAttributeTile(name:"ArmTile", type:"generic", width:6, height:4) {
        tileAttribute("device.lock", key: "PRIMARY_CONTROL") {
            attributeState("unlocked", label: 'DISARMED', action: "armstay", icon: "st.security.alarm.off", backgroundColor: "#cccccc", nextState: "locking")
 			attributeState("locking", label: 'ARMING', action: "armstay", icon: "st.security.alarm.partial", backgroundColor: "#e86d13")
 			attributeState("unlocking", label: 'DISARMING', action: "armstay", icon: "st.security.alarm.partial", backgroundColor: "#e86d13")
            attributeState("locked", label: 'ARMED', action: "Disarm", icon: "st.security.alarm.on", backgroundColor: "#bc2323")
        }
   			tileAttribute("device.armStatus", key: "SECONDARY_CONTROL") {
    			attributeState("default", label:'${currentValue}')
  			}
        }
        standardTile("silent", "device.silent", width: 2, height: 2,canChangeIcon: true, inactiveLabel: false) {        						
			state "silent", label: 'silent', action:"armLoud", icon: "st.custom.sonos.muted", backgroundColor: "#ffffff" , nextState : "loud"   
            state "loud", label: 'loud', action: "armSilent", icon: "st.custom.sonos.unmuted", backgroundColor: "#00a0dc" , nextState: "silent"           
		}        
        standardTile("bypass", "device.bypass", width: 2, height: 2, canChangeIcon: true,inactiveLabel: false) {        						
			state "disable", label: 'Disabled', action: "armBypass", icon: "st.secondary.tools", backgroundColor: "#ffffff" , nextState: "enable"
			state "enable", label: 'Enabled', action:"armRegular", icon: "st.secondary.tools", backgroundColor: "#00a0dc" , nextState: "disable"  
		}                
        standardTile("CZ01-Front Door", "device.zone1", width: 2, height: 2, canChangeIcon: true, inactiveLabel: false, decoration: "flat") {
        	state "closed", label: 'Front Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Front Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ02-Interior Garage", "device.zone2",  width: 2, height: 2,inactiveLabel: false, decoration: "flat") {			
			state "closed", label: 'Interior Garage', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Interior Garage', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ03-Car Door 1", "device.zone3", width: 2, height: 2, inactiveLabel: false,decoration: "flat") {
        	state "closed", label: 'Car Door 1', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Car Door 1', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ04-Car Door 2", "device.zone4",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Car Door 2', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Car Door 2', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ05-Garage Back Door", "device.zone5",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Garage Back Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Garage Back Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ06-Office Door", "device.zone6", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Office Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Office Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ07-Unused", "device.zone7", width: 2, height: 2, inactiveLabel: false) {
        	state "closed", label: 'Unused', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Unused', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ08-Living Room North Door", "device.zone8",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Living Room North Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Living Room North Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ09-Bedroom Door", "device.zone9",  width: 2, height: 2,inactiveLabel: false) {
        	state "closed", label: 'Bedroom Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Bedroom Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ10-Den North Door", "device.zone10", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Den North Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Den North Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("CZ11-Kitchen Door", "device.zone11", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Kitchen Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Kitchen Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("CZ12-Living Room South Door", "device.zone12", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Living Room South Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Living Room South Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("CZ13-Garage Motion", "device.zone13",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Garage Motion', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Garage Motion', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ14-Office Motion", "device.zone14", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Office Motion', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Office Motion', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ15-Downstairs Hallway Motion", "device.zone15",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Downstairs Hallway Motion', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Downstairs Hallway Motion', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ16-Family Room Motion", "device.zone16", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Family Room Motion', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Family Room Motion', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ17-Den Motion", "device.zone17", width: 2, height: 2, inactiveLabel: false) {
        	state "closed", label: 'Den Motion', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Den Motion', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ18-Kitchen Motion", "device.zone18",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Kitchen Motion', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Kitchen Motion', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ19-Living Room Motion", "device.zone19",  width: 2, height: 2,inactiveLabel: false) {
        	state "closed", label: 'Living Room Motion', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Living Room Motion', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ20-Keypad", "device.zone20", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Keypad', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Keypad', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("CZ21-Garage Window", "device.zone21", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
        	state "closed", label: 'Garage Window 21', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Garage Window 21', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ22-Garage Window", "device.zone22",  width: 2, height: 2,inactiveLabel: false, decoration: "flat") {			
			state "closed", label: 'Garage Window 22', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Garage Window 22', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ23-Office Window", "device.zone23", width: 2, height: 2, inactiveLabel: false,decoration: "flat") {
        	state "closed", label: 'Office Window 23', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Office Window 23', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ24-Office Window", "device.zone24",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Office Window 24', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Office Window 24', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ25-Office Window", "device.zone25",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Office Window 25', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Office Window 25', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ26-Utility Room Window", "device.zone26", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Utility Room Window', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Utility Room Window', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ27-Joseph Back Window", "device.zone27", width: 2, height: 2, inactiveLabel: false) {
        	state "closed", label: 'Sons Bedroom Back Window 27', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Sons Bedroom Back Window 27', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ28-Joseph Side Window", "device.zone28",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Sons Bedroom Side Window 28', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Sons Bedroom Side Window 28', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ29-Nicole Side Window", "device.zone29",  width: 2, height: 2,inactiveLabel: false) {
        	state "closed", label: 'Downstairs Daughters Bedroom Side Window 29', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Downstairs Daughters Bedroom Side Window 29', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ30-Nicole Front Window", "device.zone30", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Downstairs Daughters Bedroom Front Window 30', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Downstairs Daughters Bedroom Front Window 30', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("CZ31-Nicole Front Window", "device.zone31", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
        	state "closed", label: 'Downstairs Daughters Bedroom Front Window 31', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Downstairs Daughters Bedroom Front Window 31', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ32-Sophie Front Window", "device.zone32",  width: 2, height: 2,inactiveLabel: false, decoration: "flat") {			
			state "closed", label: 'Downstairs Daughters Bedroom Front Window 32', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Downstairs Daughters Bedroom Front Window 32', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ33-Sophie Front Window", "device.zone33", width: 2, height: 2, inactiveLabel: false,decoration: "flat") {
        	state "closed", label: 'Downstairs Stay Room Window 33', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Downstairs Stay Room Window 33 ', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ34-Family Room Window", "device.zone34",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Family Room Window 34', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Family Room Window 34', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ35-Family Room Window", "device.zone35",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Family Room Window 35', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Family Room Window 35', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ36-Family Room Window", "device.zone36", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Family Room Window 36', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Family Room Window 36', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ37-Bedroom Window", "device.zone37", width: 2, height: 2, inactiveLabel: false) {
        	state "closed", label: 'Bedroom Window 37', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Bedroom Window 37', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ38-Bedroom Window", "device.zone38",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Bedroom Window 38', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Bedroom Window 38', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ39-Bedroom Window", "device.zone39",  width: 2, height: 2,inactiveLabel: false) {
        	state "closed", label: 'Bedroom Window 39', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Bedroom Window 39', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ40-Bedroom Window", "device.zone40", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Bedroom Window 40', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Bedroom Window 40', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("CZ41-Bedroom Window", "device.zone41", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
        	state "closed", label: 'Bedroom Window 41', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Bedroom Window 41', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ42-Bedroom Window", "device.zone42",  width: 2, height: 2,inactiveLabel: false, decoration: "flat") {			
			state "closed", label: 'Bedroom Window 42', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Bedroom Window 42', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ43-Bedroom Side Window", "device.zone43", width: 2, height: 2, inactiveLabel: false,decoration: "flat") {
        	state "closed", label: 'Bedroom Side Window', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Bedroom Side Window', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ44-Bathroom Window", "device.zone44",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Bedroom Bathroom Window 44', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Bedroom Bathroom Window 44', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ45-Bathroom Window", "device.zone45",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Bedroom Bathroom Window 45', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Bedroom Bathroom Window 45', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ46-Den Window", "device.zone46", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Den Window 46', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Den Window 46', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ47-Den Window", "device.zone47", width: 2, height: 2, inactiveLabel: false) {
        	state "closed", label: 'Den Window 47', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Den Window 47', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ48-Den Window", "device.zone48",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Den Window 48', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Den Window 48', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ49-Den Window", "device.zone49",  width: 2, height: 2,inactiveLabel: false) {
        	state "closed", label: 'Den Window 49', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Den Window 49', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ50-Stephanie Window", "device.zone50", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Up Stairs Daughters Bedroom Window', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Up Stairs Daughters Bedroom Window', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("CZ51-Kitchen Back Window", "device.zone51", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
        	state "closed", label: 'Kitchen Back Window 51', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Kitchen Back Window 51', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ52-Kitchen Back Window", "device.zone52",  width: 2, height: 2,inactiveLabel: false, decoration: "flat") {			
			state "closed", label: 'Kitchen Back Window 52', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Kitchen Back Window 52', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ53-Kitchen Side Window", "device.zone53", width: 2, height: 2, inactiveLabel: false,decoration: "flat") {
        	state "closed", label: 'Kitchen Side Window 53', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Kitchen Side Window 53', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ54-Kitchen Side Window", "device.zone54",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Kitchen Side Window 54', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Kitchen Side Window 54', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ55-Kitchen Front Window", "device.zone55",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Kitchen Front Window 55', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Kitchen Front Window 55', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ56-Kitchen Front Window", "device.zone56", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Kitchen Front Window 56', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Kitchen Front Window 56', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ57-Dining Room Window", "device.zone57", width: 2, height: 2, inactiveLabel: false) {
        	state "closed", label: 'Dining Room Window 57', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Dining Room Window 57', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ58-Dining Room Window", "device.zone58",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Dining Room Window 58', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Dining Room Window 58', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ59-Living Room Window", "device.zone59",  width: 2, height: 2,inactiveLabel: false) {
        	state "closed", label: 'Living Room Window 59', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Living Room Window 59', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ60-Living Room Window", "device.zone60", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Living Room Window 60', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Living Room Window 60', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("CZ61-Living Room Window", "device.zone61", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
        	state "closed", label: 'Living Room Window 61', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Living Room Window 61', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ62-Living Room Window", "device.zone62",  width: 2, height: 2,inactiveLabel: false, decoration: "flat") {			
			state "closed", label: 'Living Room Window 62', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Living Room Window 62', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ63-Den South Door", "device.zone63", width: 2, height: 2, inactiveLabel: false,decoration: "flat") {
        	state "closed", label: 'Den South Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Den South Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("CZ64-Unused", "device.zone64",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Unused', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Unused', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("CZ65-Unused", "device.zone65",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Unused', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Unused', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
		standardTile("refresh", "device.alarmMode", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", action:"polling.poll", icon:"st.secondary.refresh"
        	}
	standardTile("setZonesClosedTile", "device.alarmMode", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label: 'Zones Closed', action:"setZonesClosed", icon:"st.secondary.refresh"
        	}
	}
    
    main "ArmTile"
    details(["ArmTile","CZ01-Front Door", "CZ02-Interior Garage", "CZ03-Car Door 1", "CZ04-Car Door 2", "CZ05-Garage Back Door", "CZ06-Office Door", "CZ07-Unused", "CZ08-Living Room North Door", "CZ09-Bedroom Door",
    "CZ10-Den North Door", "CZ11-Kitchen Door", "CZ12-Living Room South Door", "CZ13-Garage Motion","CZ14-Office Motion","CZ15-Downstairs Hallway Motion", "CZ16-Family Room Motion", "CZ17-Den Motion", "CZ18-Kitchen Motion", "CZ19-Living Room Motion", 
    "CZ20-Keypad", "CZ21-Garage Window", "CZ22-Garage Window", "CZ23-Office Window", "CZ24-Office Window","CZ25-Office Window", "CZ26-Utility Room Window", "CZ27-Joseph Back Window", "CZ28-Joseph Side Window", "CZ29-Nicole Side Window", 
    "CZ30-Nicole Front Window", "CZ31-Nicole Front Window", "CZ32-Sophie Front Window", "CZ33-Sophie Front Window", "CZ34-Family Room Window", "CZ35-Family Room Window", "CZ36-Family Room Window", "CZ37-Bedroom Window", "CZ38-Bedroom Window", "CZ39-Bedroom Window", 
    "CZ40-Bedroom Window", "CZ41-Bedroom Window", "CZ42-Bedroom Window", "CZ43-Bedroom Side Window","CZ44-Bathroom Window","CZ45-Bathroom Window", "CZ46-Den Window", "CZ47-Den Window", "CZ48-Den Window", "CZ49-Den Window", 
    "CZ50-Stephanie Window", "CZ51-Kitchen Back Window", "CZ52-Kitchen Back Window", "CZ53-Kitchen Side Window","CZ54-Kitchen Side Window","CZ55-Kitchen Front Window", "CZ56-Kitchen Front Window", "CZ57-Dining Room Window", "CZ58-Dining Room Window", "CZ59-Living Room Window", 
	"CZ60-Living Room Window",  "CZ61-Living Room Window", "CZ62-Living Room Window", "CZ63-Den South Door","CZ64-Unused","CZ65-Unused", "refresh", "silent","setZonesClosedTile" ])
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
 
}

def setZonesClosed()
{
	sendEvent(name: "zone1", value: "closed")
	sendEvent(name: "zone2", value: "closed")
	sendEvent(name: "zone3", value: "closed")
	sendEvent(name: "zone4", value: "closed")
	sendEvent(name: "zone5", value: "closed")
	sendEvent(name: "zone6", value: "closed")
	sendEvent(name: "zone7", value: "closed")
	sendEvent(name: "zone8", value: "closed")
	sendEvent(name: "zone9", value: "closed")
	sendEvent(name: "zone10", value: "closed")
	sendEvent(name: "zone11", value: "closed")
	sendEvent(name: "zone12", value: "closed")
	sendEvent(name: "zone13", value: "closed")
	sendEvent(name: "zone14", value: "closed")
	sendEvent(name: "zone15", value: "closed")
	sendEvent(name: "zone16", value: "closed")
	sendEvent(name: "zone17", value: "closed")
	sendEvent(name: "zone18", value: "closed")
	sendEvent(name: "zone19", value: "closed")
	sendEvent(name: "zone20", value: "closed")
	sendEvent(name: "zone21", value: "closed")
	sendEvent(name: "zone22", value: "closed")
	sendEvent(name: "zone23", value: "closed")
	sendEvent(name: "zone24", value: "closed")
	sendEvent(name: "zone25", value: "closed")
	sendEvent(name: "zone26", value: "closed")
	sendEvent(name: "zone27", value: "closed")
	sendEvent(name: "zone28", value: "closed")
	sendEvent(name: "zone29", value: "closed")
	sendEvent(name: "zone30", value: "closed")
	sendEvent(name: "zone31", value: "closed")
	sendEvent(name: "zone32", value: "closed")
	sendEvent(name: "zone33", value: "closed")
	sendEvent(name: "zone34", value: "closed")
	sendEvent(name: "zone35", value: "closed")
	sendEvent(name: "zone36", value: "closed")
	sendEvent(name: "zone37", value: "closed")
	sendEvent(name: "zone38", value: "closed")
	sendEvent(name: "zone39", value: "closed")
	sendEvent(name: "zone40", value: "closed")
	sendEvent(name: "zone41", value: "closed")
	sendEvent(name: "zone42", value: "closed")
	sendEvent(name: "zone43", value: "closed")
	sendEvent(name: "zone44", value: "closed")
	sendEvent(name: "zone45", value: "closed")
	sendEvent(name: "zone46", value: "closed")
	sendEvent(name: "zone47", value: "closed")
	sendEvent(name: "zone48", value: "closed")
	sendEvent(name: "zone49", value: "closed")
	sendEvent(name: "zone50", value: "closed")
	sendEvent(name: "zone51", value: "closed")
	sendEvent(name: "zone52", value: "closed")
	sendEvent(name: "zone53", value: "closed")
	sendEvent(name: "zone54", value: "closed")
	sendEvent(name: "zone55", value: "closed")
	sendEvent(name: "zone56", value: "closed")
	sendEvent(name: "zone57", value: "closed")
	sendEvent(name: "zone58", value: "closed")
	sendEvent(name: "zone59", value: "closed")
	sendEvent(name: "zone60", value: "closed")
	sendEvent(name: "zone61", value: "closed")
	sendEvent(name: "zone62", value: "closed")
	sendEvent(name: "zone63", value: "closed")
	sendEvent(name: "zone64", value: "closed")
	sendEvent(name: "zone65", value: "closed")

	sendEvent(name: "zone1", value: "inactive")
	sendEvent(name: "zone2", value: "inactive")
	sendEvent(name: "zone3", value: "inactive")
	sendEvent(name: "zone4", value: "inactive")
	sendEvent(name: "zone5", value: "inactive")
	sendEvent(name: "zone6", value: "inactive")
	sendEvent(name: "zone7", value: "inactive")
	sendEvent(name: "zone8", value: "inactive")
	sendEvent(name: "zone9", value: "inactive")
	sendEvent(name: "zone10", value: "inactive")
	sendEvent(name: "zone11", value: "inactive")
	sendEvent(name: "zone12", value: "inactive")
	sendEvent(name: "zone13", value: "inactive")
	sendEvent(name: "zone14", value: "inactive")
	sendEvent(name: "zone15", value: "inactive")
	sendEvent(name: "zone16", value: "inactive")
	sendEvent(name: "zone17", value: "inactive")
	sendEvent(name: "zone18", value: "inactive")
	sendEvent(name: "zone19", value: "inactive")
	sendEvent(name: "zone20", value: "inactive")
	sendEvent(name: "zone21", value: "inactive")
	sendEvent(name: "zone22", value: "inactive")
	sendEvent(name: "zone23", value: "inactive")
	sendEvent(name: "zone24", value: "inactive")
	sendEvent(name: "zone25", value: "inactive")
	sendEvent(name: "zone26", value: "inactive")
	sendEvent(name: "zone27", value: "inactive")
	sendEvent(name: "zone28", value: "inactive")
	sendEvent(name: "zone29", value: "inactive")
	sendEvent(name: "zone30", value: "inactive")
	sendEvent(name: "zone31", value: "inactive")
	sendEvent(name: "zone32", value: "inactive")
	sendEvent(name: "zone33", value: "inactive")
	sendEvent(name: "zone34", value: "inactive")
	sendEvent(name: "zone35", value: "inactive")
	sendEvent(name: "zone36", value: "inactive")
	sendEvent(name: "zone37", value: "inactive")
	sendEvent(name: "zone38", value: "inactive")
	sendEvent(name: "zone39", value: "inactive")
	sendEvent(name: "zone40", value: "inactive")
	sendEvent(name: "zone41", value: "inactive")
	sendEvent(name: "zone42", value: "inactive")
	sendEvent(name: "zone43", value: "inactive")
	sendEvent(name: "zone44", value: "inactive")
	sendEvent(name: "zone45", value: "inactive")
	sendEvent(name: "zone46", value: "inactive")
	sendEvent(name: "zone47", value: "inactive")
	sendEvent(name: "zone48", value: "inactive")
	sendEvent(name: "zone49", value: "inactive")
	sendEvent(name: "zone50", value: "inactive")
	sendEvent(name: "zone51", value: "inactive")
	sendEvent(name: "zone52", value: "inactive")
	sendEvent(name: "zone53", value: "inactive")
	sendEvent(name: "zone54", value: "inactive")
	sendEvent(name: "zone55", value: "inactive")
	sendEvent(name: "zone56", value: "inactive")
	sendEvent(name: "zone57", value: "inactive")
	sendEvent(name: "zone58", value: "inactive")
	sendEvent(name: "zone59", value: "inactive")
	sendEvent(name: "zone60", value: "inactive")
	sendEvent(name: "zone61", value: "inactive")
	sendEvent(name: "zone62", value: "inactive")
	sendEvent(name: "zone63", value: "inactive")
	sendEvent(name: "zone64", value: "inactive")
	sendEvent(name: "zone65", value: "inactive")

	sendEvent(name: "zone1", value: "clear")
	sendEvent(name: "zone2", value: "clear")
	sendEvent(name: "zone3", value: "clear")
	sendEvent(name: "zone4", value: "clear")
	sendEvent(name: "zone5", value: "clear")
	sendEvent(name: "zone6", value: "clear")
	sendEvent(name: "zone7", value: "clear")
	sendEvent(name: "zone8", value: "clear")
	sendEvent(name: "zone9", value: "clear")
	sendEvent(name: "zone10", value: "clear")
	sendEvent(name: "zone11", value: "clear")
	sendEvent(name: "zone12", value: "clear")
	sendEvent(name: "zone13", value: "clear")
	sendEvent(name: "zone14", value: "clear")
	sendEvent(name: "zone15", value: "clear")
	sendEvent(name: "zone16", value: "clear")
	sendEvent(name: "zone17", value: "clear")
	sendEvent(name: "zone18", value: "clear")
	sendEvent(name: "zone19", value: "clear")
	sendEvent(name: "zone20", value: "clear")
	sendEvent(name: "zone21", value: "clear")
	sendEvent(name: "zone22", value: "clear")
	sendEvent(name: "zone23", value: "clear")
	sendEvent(name: "zone24", value: "clear")
	sendEvent(name: "zone25", value: "clear")
	sendEvent(name: "zone26", value: "clear")
	sendEvent(name: "zone27", value: "clear")
	sendEvent(name: "zone28", value: "clear")
	sendEvent(name: "zone29", value: "clear")
	sendEvent(name: "zone30", value: "clear")
	sendEvent(name: "zone31", value: "clear")
	sendEvent(name: "zone32", value: "clear")
	sendEvent(name: "zone33", value: "clear")
	sendEvent(name: "zone34", value: "clear")
	sendEvent(name: "zone35", value: "clear")
	sendEvent(name: "zone36", value: "clear")
	sendEvent(name: "zone37", value: "clear")
	sendEvent(name: "zone38", value: "clear")
	sendEvent(name: "zone39", value: "clear")
	sendEvent(name: "zone40", value: "clear")
	sendEvent(name: "zone41", value: "clear")
	sendEvent(name: "zone42", value: "clear")
	sendEvent(name: "zone43", value: "clear")
	sendEvent(name: "zone44", value: "clear")
	sendEvent(name: "zone45", value: "clear")
	sendEvent(name: "zone46", value: "clear")
	sendEvent(name: "zone47", value: "clear")
	sendEvent(name: "zone48", value: "clear")
	sendEvent(name: "zone49", value: "clear")
	sendEvent(name: "zone50", value: "clear")
	sendEvent(name: "zone51", value: "clear")
	sendEvent(name: "zone52", value: "clear")
	sendEvent(name: "zone53", value: "clear")
	sendEvent(name: "zone54", value: "clear")
	sendEvent(name: "zone55", value: "clear")
	sendEvent(name: "zone56", value: "clear")
	sendEvent(name: "zone57", value: "clear")
	sendEvent(name: "zone58", value: "clear")
	sendEvent(name: "zone59", value: "clear")
	sendEvent(name: "zone60", value: "clear")
	sendEvent(name: "zone61", value: "clear")
	sendEvent(name: "zone62", value: "clear")
	sendEvent(name: "zone63", value: "clear")
	sendEvent(name: "zone64", value: "clear")
	sendEvent(name: "zone65", value: "clear")

}

def update(attribute,state) {
    log.debug "update state, request: attribute: ${attribute}  state: ${state}"
    def currentValues = device.currentValue(attribute)
    if(state != currentValues as String) {
    	log.debug "changing state.."
    	sendEvent(name: attribute, value: state)
    }
    if(attribute == "armstatus") {
        	log.debug "changing armstatus.."
    	sendEvent(name: "currentState", value: state)
        if(state == "disarmed")
        {
        	sendEvent(name: "lock", value: "unlocked")
        }
        else if (state == "stay" )
        {
        	sendEvent(name: "lock", value: "locked")
        }
        else if (state == "away") 
        {
            sendEvent(name: "lock", value: "locked")

        }
        
    }
    if(attribute.startsWith("zone")) {
       	log.debug "changing zone staus.."
		sendEvent(name: attribute, value: state)
        }
        

    }

def installed()
{
	state.bLoud = "False"
}

def updated()
{

}


// handle commands
def poll()
{
    return request('/refresh')
}

def armSilent()
{
	state.bLoud = "False"
}

def armLoud()
{
	state.bLoud = "True"
}

def armBypass()
{
	state.bBypass = "True"
}

def armRegular()
{
	state.bBypass = "False"
}

def lock() {
	armstay()
}

def unlock(){
	Disarm()
}

def armstay() {
    if (device.currentValue("lock") == "unlocked")
    {
    	sendEvent(name: "lock", value: "locking")
		log.debug "Executing 'ArmStay'"
        if( state.bLoud == "False" )
        {
            return request('/arm/stay')
        }
        else 
        {

            return request('/arm/stay/loud')
        }
    }
}

def armaway() {
    if (device.currentValue("lock") == "unlocked")
    {
        log.debug "Executing 'ArmAway'"
     	sendEvent(name: "lock", value: "locking")
        if( state.bLoud == "False" )
        {
            return request('/arm/away')
        }
        else 
        {
            return request('/arm/away/loud')
        }
    }
}

def Disarm() {
    if (device.currentValue("lock") == "locked")
    {
        log.debug "Executing 'Disarm'"
    	sendEvent(name: "lock", value: "unlocking")
        if( state.bLoud == "False" )
        {
            return request('/disarm')
        }
        else
        {
            return request('/disarm/loud')
        }
    }
}

def request(request) {
	log.debug("Request:'${request}'")
	def userpassascii = "admin:${concord_server_api_password}"
    def userpass = "Basic " + userpassascii.encodeAsBase64().toString()
    def hosthex = convertIPtoHex(concord_server_ip_address)
    def porthex = convertPortToHex(concord_server_port)
    log.debug("${device.deviceNetworkId}")
    def hubAction = new physicalgraph.device.HubAction(
   	 		'method': 'POST',
    		'path': "/concord${request}"+"&apiserverurl="+java.net.URLEncoder.encode(apiServerUrl("/api/smartapps/installations"), "UTF-8"),
        	'body': '',
        	'headers': [ HOST: "${hosthex}:${porthex}" , Authorization:userpass]
		)

    log.debug hubAction
    return hubAction
}


private String convertIPtoHex(ipAddress) {
	log.debug('convertIPtoHex:'+"${ipAddress}")
    String hex = ipAddress.tokenize( '.' ).collect {  String.format( '%02X', it.toInteger() ) }.join()
    return hex
}

private String convertPortToHex(port) {
	log.debug('convertIPtoHex:'+"${port}")
	String hexport = port.toString().format( '%04X', port.toInteger() )
    return hexport
}
