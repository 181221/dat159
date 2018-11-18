#include <WiFi.h>
#include <PubSubClient.h>
#include <OneWire.h>
#include <DallasTemperature.h>
#define ONE_WIRE_BUS 23

const char *ssid = "MotoG";
const char *password = "heaton27";
const char *mqtt_server = "m23.cloudmqtt.com";
const int   mqtt_port = 10113;
const char *mqtt_user = "rlmlvquc";
const char *mqtt_password = "ck_xPRfR8TaT";

long lastMsg = 0;
char msg[50];
int value = 0;
const char *TOPIC_LIGHT_SWITCH = "room/living/lightSwitch";
const char *TOPIC_LIGHT_STATUS = "room/living/lightStatus";
const char *TOPIC_TEMPERATURE = "room/living/temperature";
bool LIGHT_ON = 0;

WiFiClient espClient;
PubSubClient client(espClient);
OneWire oneWire(ONE_WIRE_BUS);
DallasTemperature sensors(&oneWire);

void setup()
{
  pinMode(LED_BUILTIN, OUTPUT);
  digitalWrite(LED_BUILTIN, LOW);
  Serial.begin(115200);
  WiFi.begin(ssid, password);
  sensors.begin();

  while (WiFi.status() != WL_CONNECTED)
  {
    delay(1000);
    Serial.println("Connecting to WiFi..");
  }

  Serial.println("Connected to the WiFi network");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

  client.setServer(mqtt_server, mqtt_port);
  client.setCallback(callback);
  while (!client.connected())
  {
    Serial.println("Connecting to MQTT...");

    if (client.connect("ESP32Client", mqtt_user, mqtt_password))
    {

      Serial.println("connected");
    }
    else
    {

      Serial.print("failed with state ");
      Serial.print(client.state());
      delay(20000);
    }
  }
  client.publish("room/esp/started", "Hello from living room");
  client.subscribe(TOPIC_LIGHT_SWITCH);
}

void callback(char *topic, byte *message, unsigned int length)
{
  Serial.print("Message arrived on topic: ");
  Serial.print(topic);
  Serial.print(". Message: ");
  String messageTemp;

  for (int i = 0; i < length; i++)
  {
    Serial.print((char)message[i]);
    messageTemp += (char)message[i];
  }
  Serial.println();
  if(String(topic) == TOPIC_LIGHT_SWITCH){
     if (messageTemp == "on")
      {
        Serial.println("on");
        LIGHT_ON = 1;
        digitalWrite(LED_BUILTIN, LIGHT_ON);
        client.publish(TOPIC_LIGHT_STATUS, "On");
      }
      else if (messageTemp == "off")
      {
        Serial.println("off");
        LIGHT_ON = 0;
        digitalWrite(LED_BUILTIN, LIGHT_ON);
        client.publish(TOPIC_LIGHT_STATUS, "Off");
      }
  }
}

void reconnect()
{
  // Loop until we're reconnected
  while (!client.connected())
  {
    Serial.print("Attempting MQTT connection...");
    // Attempt to connect
    if (client.connect("ESP32Client", mqtt_user, mqtt_password))
    {
      Serial.println("connected");
      // Subscribe
      client.subscribe(TOPIC_LIGHT_SWITCH);
    }
    else
    {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}

void loop()
{
  if (!client.connected())
  {
    reconnect();
  }
  client.loop();

  long now = millis();

  if (now - lastMsg > 5000)
  {
    lastMsg = now;
    char fbuff[10];
    sensors.requestTemperatures();
    float temp = sensors.getTempCByIndex(0);
    if(sensors.getTempCByIndex(0) != -127){
      dtostrf(sensors.getTempCByIndex(0),2,2,fbuff);
      client.publish(TOPIC_TEMPERATURE, fbuff);
    }
  }
}
