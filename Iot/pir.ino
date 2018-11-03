
const int RED = 13;
const int YELLOW = 12;
const int GREEN = 11;
const int PIR_SENSOR = 4;
const int LOCKED = 0;
const int UNLOCKED = 2;
const int WAITING = 1; 
unsigned long Time = 10000;


int PIR_STATE = LOW;

class Button
{
private:
  bool _state;
  uint8_t _pin;

public:
  Button(uint8_t pin) : _pin(pin) {}

  void begin()
  {
    pinMode(_pin, INPUT_PULLUP);
    _state = digitalRead(_pin);
  }

  bool isReleased()
  {
    bool v = digitalRead(_pin);
    if (v != _state)
    {
      _state = v;
      if (_state)
      {
        return true;
      }
    }
    return false;
  }
  void resetState(){
    _state = 0;
  }
};

Button myButton(6);
Button myButton2(2);

int state = 0;
int lastState = state;
void setup()
{
  pinSetup();
  Serial.begin(9600);
}

void loop()
{
  
  PIR_STATE = digitalRead(PIR_SENSOR);
  if(state != lastState){
    PIR_STATE = UNLOCKED;
  }else {
    state = PIR_STATE;
  }

  switch (state)
  {
  case LOCKED:
    digitalWrite(RED, HIGH);
    digitalWrite(YELLOW, LOW);
    break;
  case UNLOCKED:
    digitalWrite(YELLOW, LOW);
    digitalWrite(GREEN, HIGH);
    delay(Time);
    digitalWrite(GREEN, LOW);
    myButton.resetState();
    myButton2.resetState();
    state = LOCKED;
    break;
  case WAITING:
    digitalWrite(RED, LOW);

    digitalWrite(YELLOW, HIGH);

    int rightCombination = 1;
    
    unsigned long lastDebounceTime = millis();
    unsigned long DebounceTime = lastDebounceTime;
    

    while (rightCombination && (DebounceTime - lastDebounceTime) < Time)
    {
      if (myButton.isReleased())
      {
        blink();
        while (!myButton.isReleased())
        {
          if (myButton2.isReleased())
          {
            blink();
            state = UNLOCKED;
            break;
          }
        }
        rightCombination = 0;
      }
      else if (myButton2.isReleased())
      {
        blink();
        rightCombination = 0;
      }
      DebounceTime = millis();
      delay(50);
    }
    break;
  }
}

void pinSetup()
{
  pinMode(RED, OUTPUT);
  pinMode(YELLOW, OUTPUT);
  pinMode(GREEN, OUTPUT);
  myButton.begin();
  myButton2.begin();
  pinMode(PIR_SENSOR, INPUT);
}


void blink()
{
  digitalWrite(YELLOW, LOW);
  delay(100);
  digitalWrite(YELLOW, HIGH);
}