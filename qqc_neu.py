#Importieren von Bibliotheken
import paho.mqtt.client as mqtt
import RPi.GPIO as GPIO
import time
import _thread

#ServerDaten
url = "kassiopeia.mt.haw-hamburg.de"
#url = "diginet.mt.haw-hamburg.de"
topic = "haw/dmi/mt/its/ss17/qqc/apptest"
user = 'haw'
pw = 'schuh+-0'

#Connection
connected = False
def on_connect(client, userdata, flags, rc):
    print("Connected with result code "+str(rc))
    global connected
    client.subscribe(topic)
    connected = True

#Daten auslesen
def on_message(client, userdata, msg):
    payload = msg.payload.decode('utf-8')
    
    global zaehler1
    global zaehler2
    global abstand
    global speedMotor1min
    global speedMotor1max
    global speedMotor1
    global normalSpeed
       
    if payload == 'ende':
        print("Schalte LED's und Motoren aus")
        p.start(0)
        q.start(0)
        time.sleep(sleepTime)
        GPIO.output(13, True)
        abstand = distanz("Einmessen Player1")
        while abstand > 30:
            p.ChangeDutyCycle(speedMotor1min)
            abstand = distanz("Einmessen Player1")
        p.ChangeDutyCycle(0)
        q.ChangeDutyCycle(0)
        GPIO.output(13, False)
        zaehler1 = 0
        zaehler2 = 0
        abstand = 0
        print("Motor1 (QQC) Reset OK")
        print("Motor2 (Konfetti) Reset OK")
        print("zaehler Reset OK, %.0f" % zaehler1)
        print("zaehler Reset OK, %.0f" % zaehler2)
        print("Abstand Reset OK, %.1f" % abstand)
        client.publish(topic, 'resetOK')
        
    elif payload == 'start':
        p.start(0)
        GPIO.output(13, False)
        einpegeln()
        p.ChangeDutyCycle(normalSpeed)
        client.publish(topic, 'go')
        
    elif payload == 'tie':
        time.sleep(1)
        client.publish(topic, 'go')
            
         

    elif payload == 'Player1':
        if distanz("Einmessen Player1") < 95:
            abstand = distanz("Einmessen Player1")
            if abstand > 80:
                q.start(0)
                print("konfetti Player1 START")
                konfetti()
                print("konfetti Player1 ENDE")
            else:
                p.start(0)
                abstand = distanz("Messung Player1")
                print ("Gemessene Entfernung = %.1f cm" % abstand)
                abstand = abstand + 10
                if abstand > 80:
                    abstand = 80
                speedMotor1 = speedMotor1max
                GPIO.output(13, False)
                fahrenHoch(speedMotor1)
                p.ChangeDutyCycle(normalSpeed)
                zaehler1 = zaehler1 + 1
                print(zaehler1)
                print(abstand)
                client.publish(topic, 'go')
        else:
            print("Fehlerhafter Wert P1")
        
    elif payload == 'Player2':
        if distanz("Einmessen Player2") < 95:
            abstand = distanz("Einmessen Player1")
            if abstand < 20:
                q.start(0)
                print("konfetti Player2 START")
                konfetti()
                print("konfetti Player2 ENDE")
            else:
                p.start(0)
                abstand = distanz("Messung Player2")
                print ("Gemessene Entfernung = %.1f cm" % abstand)
                #time.sleep(5)
                abstand = abstand - 10
                print(abstand)
                if abstand < 20:
                    abstand = 20
                speedMotor1 = speedMotor1min
                print(speedMotor1)
                GPIO.output(13, True)
                fahrenRunter(speedMotor1)
                GPIO.output(13, False)
                time.sleep(0.25)
                print("wechsel")
                p.ChangeDutyCycle(normalSpeed)
                zaehler2 = zaehler2 - 1
                print(zaehler2)
                print(abstand)
                print(distanz("...dh..."))
                client.publish(topic, 'go')
        else:
            print("Fehlerhafter Wert P2")
    
    
#distanz messen 
def distanz( threadName, ):
    #Zeitstempel
    print ("%s: %s" % ( threadName, time.ctime(time.time()) ))  
    # setze Trigger auf HIGH
    GPIO.output(GPIO_TRIGGER, True)

    # setze Trigger nach 0.01ms aus LOW
    time.sleep(0.00001)
    GPIO.output(GPIO_TRIGGER, False)

    StartZeit = time.time()
    StopZeit = time.time()

    # speichere Startzeit
    while GPIO.input(GPIO_ECHO) == 0:
        StartZeit = time.time()

    # speichere Ankunftszeit
    while GPIO.input(GPIO_ECHO) == 1:
        StopZeit = time.time()

    # Zeit Differenz zwischen Start und Ankunft
    TimeElapsed = StopZeit - StartZeit
    # mit der Schallgeschwindigkeit (34300 cm/s) multiplizieren
    # und durch 2 teilen, da hin und zurueck
    distanz = (TimeElapsed * 34300) / 2
    return distanz    

    
#einpegeln
def einpegeln():
    global abstand
    while abstand < 45:
        abstand = distanz("Messung Player1")
        p.ChangeDutyCycle(speedMotor1max)
        time.sleep(sleepTime)
        print ("Gemessene Entfernung = %.1f cm" % abstand)
        
def fahrenHoch(speedMotor1):
    global abstand
    #global speedMotor1
    while distanz("FahrtHoch") < abstand:
        p.ChangeDutyCycle(speedMotor1)
        print (speedMotor1)
        print (distanz("FahrtHoch"))
        time.sleep(sleepTime)
        
def fahrenRunter(speedMotor1):
    global abstand
    #global speedMotor1
    time.sleep(0.25)
    while distanz("FahrtRunter") > abstand:
        print("Abstand %.1f" % abstand)
        print("distanz %.1f" % distanz("..."))
        p.ChangeDutyCycle(speedMotor1)
        #print (speedMotor1)
        #print (distanz("FahrtRunter"))
        time.sleep(sleepTime)
    

def konfetti():
    q.ChangeDutyCycle(100)
    client.publish(topic, 'ende')
    time.sleep(5)
    q.ChangeDutyCycle(0)
    

        
#GPIO definieren
GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)
GPIO_TRIGGER = 16
GPIO_ECHO = 18

GPIO.setup(13, GPIO.OUT)
GPIO.setup(26, GPIO.OUT)
GPIO.setup(38, GPIO.OUT)
GPIO.setup(GPIO_TRIGGER, GPIO.OUT)
GPIO.setup(GPIO_ECHO, GPIO.IN)
GPIO.setwarnings(True)

#Steuerung und Variablen
p = GPIO.PWM(26,5000)
q = GPIO.PWM(38,5000)
sleepTime = 0.2                #globale SleepTime
speedMotor1max = 95           #Höchstgeschwindigkeit Motor1
speedMotor1min = 100           #Mindestgeschwindigkeit Motor1
normalSpeed = 5               #Geschwindigkeit in Ruhelage
zaehler1 = 0
zaehler2 = 0
abstand = 0

#client
client = mqtt.Client()         #Client object
client.on_connect = on_connect #Callbacks registrieren
client.on_message = on_message
client.username_pw_set(user,password=pw)
client.connect(url, 1883, 60)  #Connect
       
client.loop_forever()          #Abarbeiten von Paketen

p.stop()
q.stop()

GPIO.cleanup()




    
    