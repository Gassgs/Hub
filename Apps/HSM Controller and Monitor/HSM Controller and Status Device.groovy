/**
 *  HSM Controller and Status Device
 *
 *  Virtual Driver for Home  Security Control and Status
 *
 *  Copyright 2021 Gassgs/ Gary Gassmann
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  Change History:
 *
 *  V1.0.0  1-27-2021   -       First run - Gassgs
 *  V1.2.0  1-28-2021   -       Added arm and disarm away with presence       
 *  V1.3.0  1-29-2021   -       Added Water sensor custom Handler    
 * 
 */

metadata {
    definition (name: "HSM Controller and Status Device", namespace: "Gassgs", author: "Gary G") {
        capability"Actuator"
        capability "Switch"
        capability "Presence Sensor"
        capability "WaterSensor"
        
        command"hsmUpdate",[[name:"status",type:"STRING"],[name:"text",type:"STRING"]]
        command"arm"
        command"disarm"
        command"clearAlert"
      
        attribute"status","string"
        attribute"hsmStatus","string"
        attribute"alert","string"
        attribute"currentAlert","string"
        attribute"currentMode","string"
        attribute"Home","string"
        attribute"Leak","string" 
    }
}

def on(){
     sendEvent(name:"switch",value:"on")
}

def off(){
    sendEvent(name:"switch",value:"off")
}

def arm(){
    on()
}

def disarm(){
    off()
}

//current event will send :"alert","active" / when cancelled will send: "alert", "ok"
def clearAlert(){
    sendEvent(name:"alert",value:"clearing")
    runIn(10,resetAlert)
}

def resetAlert(){
    sendEvent(name:"alert",value:"ok")
}

def hsmUpdate(String status,String value){
    textValue=value
    statusValue=status
    sendEvent(name:statusValue, value: textValue)
}

def refresh(){
    //does nothing.used for google home
}
