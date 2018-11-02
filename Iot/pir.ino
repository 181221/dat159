int RED = 13;
int YELLOW = 12;
int GREEN = 11;
int BUTTON_ON = 6;
int BUTTON_OFF = 2;
int PIR_SENSOR = 4;

int button_on_state = LOW;
int button_off_state = LOW;

int last_button_on_state = LOW;
int last_button_off_state = LOW;

int PIR_STATE = LOW;

int val = 0;
int STATE = 1;

const int LOCKED = 0;
const int UNLOCKED = 1;
int WAITING = 1;
int buttonPushCounter = 0;
int code = 0;
int code1 = 0;

void setup()
{
  pinMode(RED, OUTPUT);
  pinMode(YELLOW, OUTPUT);
  pinMode(GREEN, OUTPUT);
  pinMode(BUTTON_ON, INPUT);
  pinMode(BUTTON_OFF, INPUT);
  pinMode(PIR_SENSOR, INPUT);
  Serial.begin(9600);
}

void loop()
{
  switch (PIR_STATE)
  {
  case LOCKED:
    digitalWrite(RED, HIGH);
    digitalWrite(YELLOW, LOW);
    break;
  case UNLOCKED:
    digitalWrite(RED, LOW);
    digitalWrite(YELLOW, HIGH);
    while (WAITING)
    {
      button_on_state = digitalRead(BUTTON_ON);

      while(button_on_state != last_button_on_state){
        if(button_on_state == HIGH){
          button_off_state = digitalRead(BUTTON_OFF);
          if(button_off_state != last_button_off_state){
            if(button_off_state == HIGH){
              digitalWrite(GREEN, HIGH)
            }
          }
        }
      }
      last_button_on_state = button_on_state;
      last_button_off_state = last_button_off_state;
    }
    break;
  }
  PIR_STATE = digitalRead(PIR_SENSOR);
}
