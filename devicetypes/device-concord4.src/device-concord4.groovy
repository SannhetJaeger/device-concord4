/**
 *  Concord 4 Device Handler
 *	Enhanced 2020 SannhetJaeger
 *  Scott Dozier 4/1/2016
 */

metadata {
	// Automatically generated. Make future change here.
	definition (name: "Concord4", author: "SannhetJaeger", namespace: "SannhetJaeger") {
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
        standardTile("Zone 1", "device.zone1", width: 2, height: 2, canChangeIcon: true, inactiveLabel: false, decoration: "flat") {
        	state "closed", label: 'Front Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Front Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 2", "device.zone2",  width: 2, height: 2,inactiveLabel: false, decoration: "flat") {			
			state "closed", label: 'Interior Garage', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Interior Garage', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 3", "device.zone3", width: 2, height: 2, inactiveLabel: false,decoration: "flat") {
        	state "closed", label: 'Car Door 1', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Car Door 1', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 4", "device.zone4",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Car Door 2', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Car Door 2', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 5", "device.zone5",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Garage Back Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Garage Back Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 6", "device.zone6", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Office Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Office Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 7", "device.zone7", width: 2, height: 2, inactiveLabel: false) {
        	state "closed", label: 'Unused', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Unused', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 8", "device.zone8",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Living Room North Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Living Room North Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 9", "device.zone9",  width: 2, height: 2,inactiveLabel: false) {
        	state "closed", label: 'Bedroom Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Bedroom Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 10", "device.zone10", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Den North Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Den North Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("Zone 11", "device.zone11", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Kitchen Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Kitchen Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("Zone 12", "device.zone12", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Living Room South Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Living Room South Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("Zone 13", "device.zone13",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Garage Motion', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Garage Motion', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 14", "device.zone14", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Office Motion', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Office Motion', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 15", "device.zone15",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Downstairs Hallway Motion', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Downstairs Hallway Motion', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 16", "device.zone16", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Family Room Motion', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Family Room Motion', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 17", "device.zone17", width: 2, height: 2, inactiveLabel: false) {
        	state "closed", label: 'Den Motion', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Den Motion', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 18", "device.zone18",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Kitchen Motion', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Kitchen Motion', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 19", "device.zone19",  width: 2, height: 2,inactiveLabel: false) {
        	state "closed", label: 'Living Room Motion', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Living Room Motion', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 20", "device.zone20", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Keypad', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Keypad', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("Zone 21", "device.zone21", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
        	state "closed", label: 'Garage Window 21', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Garage Window 21', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 22", "device.zone22",  width: 2, height: 2,inactiveLabel: false, decoration: "flat") {			
			state "closed", label: 'Garage Window 22', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Garage Window 22', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 23", "device.zone23", width: 2, height: 2, inactiveLabel: false,decoration: "flat") {
        	state "closed", label: 'Office Window 23', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Office Window 23', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 24", "device.zone24",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Office Window 24', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Office Window 24', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 25", "device.zone25",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Office Window 25', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Office Window 25', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 26", "device.zone26", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Utility Room Window', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Utility Room Window', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 27", "device.zone27", width: 2, height: 2, inactiveLabel: false) {
        	state "closed", label: 'Sons Bedroom Back Window 27', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Sons Bedroom Back Window 27', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 28", "device.zone28",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Sons Bedroom Side Window 28', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Sons Bedroom Side Window 28', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 29", "device.zone29",  width: 2, height: 2,inactiveLabel: false) {
        	state "closed", label: 'Downstairs Daughters Bedroom Side Window 29', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Downstairs Daughters Bedroom Side Window 29', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 30", "device.zone30", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Downstairs Daughters Bedroom Front Window 30', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Downstairs Daughters Bedroom Front Window 30', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("Zone 31", "device.zone31", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
        	state "closed", label: 'Downstairs Daughters Bedroom Front Window 31', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Downstairs Daughters Bedroom Front Window 31', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 32", "device.zone32",  width: 2, height: 2,inactiveLabel: false, decoration: "flat") {			
			state "closed", label: 'Downstairs Daughters Bedroom Front Window 32', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Downstairs Daughters Bedroom Front Window 32', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 33", "device.zone33", width: 2, height: 2, inactiveLabel: false,decoration: "flat") {
        	state "closed", label: 'Downstairs Stay Room Window 33', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Downstairs Stay Room Window 33 ', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 34", "device.zone34",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Family Room Window 34', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Family Room Window 34', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 35", "device.zone35",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Family Room Window 35', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Family Room Window 35', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 36", "device.zone36", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Family Room Window 36', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Family Room Window 36', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 37", "device.zone37", width: 2, height: 2, inactiveLabel: false) {
        	state "closed", label: 'Bedroom Window 37', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Bedroom Window 37', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 38", "device.zone38",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Bedroom Window 38', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Bedroom Window 38', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 39", "device.zone39",  width: 2, height: 2,inactiveLabel: false) {
        	state "closed", label: 'Bedroom Window 39', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Bedroom Window 39', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 40", "device.zone40", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Bedroom Window 40', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Bedroom Window 40', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("Zone 41", "device.zone41", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
        	state "closed", label: 'Bedroom Window 41', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Bedroom Window 41', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 42", "device.zone42",  width: 2, height: 2,inactiveLabel: false, decoration: "flat") {			
			state "closed", label: 'Bedroom Window 42', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Bedroom Window 42', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 43", "device.zone43", width: 2, height: 2, inactiveLabel: false,decoration: "flat") {
        	state "closed", label: 'Bedroom Side Window', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Bedroom Side Window', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 44", "device.zone44",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Bedroom Bathroom Window 44', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Bedroom Bathroom Window 44', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 45", "device.zone45",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Bedroom Bathroom Window 45', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Bedroom Bathroom Window 45', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 46", "device.zone46", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Den Window 46', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Den Window 46', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 47", "device.zone47", width: 2, height: 2, inactiveLabel: false) {
        	state "closed", label: 'Den Window 47', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Den Window 47', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 48", "device.zone48",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Den Window 48', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Den Window 48', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 49", "device.zone49",  width: 2, height: 2,inactiveLabel: false) {
        	state "closed", label: 'Den Window 49', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Den Window 49', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 50", "device.zone50", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Up Stairs Daughters Bedroom Window', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Up Stairs Daughters Bedroom Window', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("Zone 51", "device.zone51", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
        	state "closed", label: 'Kitchen Back Window 51', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Kitchen Back Window 51', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 52", "device.zone52",  width: 2, height: 2,inactiveLabel: false, decoration: "flat") {			
			state "closed", label: 'Kitchen Back Window 52', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Kitchen Back Window 52', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 53", "device.zone53", width: 2, height: 2, inactiveLabel: false,decoration: "flat") {
        	state "closed", label: 'Kitchen Side Window 53', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Kitchen Side Window 53', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 54", "device.zone54",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Kitchen Side Window 54', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Kitchen Side Window 54', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 55", "device.zone55",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Kitchen Front Window 55', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Kitchen Front Window 55', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 56", "device.zone56", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Kitchen Front Window 56', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Kitchen Front Window 56', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 57", "device.zone57", width: 2, height: 2, inactiveLabel: false) {
        	state "closed", label: 'Dining Room Window 57', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Dining Room Window 57', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 58", "device.zone58",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Dining Room Window 58', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Dining Room Window 58', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 59", "device.zone59",  width: 2, height: 2,inactiveLabel: false) {
        	state "closed", label: 'Living Room Window 59', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Living Room Window 59', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 60", "device.zone60", width: 2, height: 2, inactiveLabel: false) {			
			state "closed", label: 'Living Room Window 60', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Living Room Window 60', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}
        standardTile("Zone 61", "device.zone61", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
        	state "closed", label: 'Living Room Window 61', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Living Room Window 61', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 62", "device.zone62",  width: 2, height: 2,inactiveLabel: false, decoration: "flat") {			
			state "closed", label: 'Living Room Window 62', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Living Room Window 62', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 63", "device.zone63", width: 2, height: 2, inactiveLabel: false,decoration: "flat") {
        	state "closed", label: 'Den South Door', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
			state "open", label: 'Den South Door', icon: "st.contact.contact.open", backgroundColor: "#e86d13"			
		}  
        standardTile("Zone 64", "device.zone64",  width: 2, height: 2,inactiveLabel: false) {			
			state "closed", label: 'Unused', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
            state "open", label: 'Unused', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		}  
        standardTile("Zone 65", "device.zone65",  width: 2, height: 2,inactiveLabel: false) {			
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
    details(["ArmTile","Zone 1", "Zone 2", "Zone 3", "Zone 4", "Zone 5", "Zone 6", "Zone 7", "Zone 8", "Zone 9", "Zone 10",
    "Zone 11", "Zone 12", "Zone 13","Zone 14","Zone 15", "Zone 16", "Zone 17", "Zone 18", "Zone 19", 
    "Zone 21", "Zone 22", "Zone 23","Zone 24","Zone 25", "Zone 26", "Zone 27", "Zone 28", "Zone 29", 
    "Zone 31", "Zone 32", "Zone 33","Zone 34","Zone 35", "Zone 36", "Zone 37", "Zone 38", "Zone 39", 
    "Zone 41", "Zone 42", "Zone 43","Zone 44","Zone 45", "Zone 46", "Zone 47", "Zone 48", "Zone 49", 
    "Zone 51", "Zone 52", "Zone 53","Zone 54","Zone 55", "Zone 56", "Zone 57", "Zone 58", "Zone 59", 
    "Zone 61", "Zone 62", "Zone 63","Zone 64","Zone 65", "refresh", "silent","setZonesClosedTile" ])
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
