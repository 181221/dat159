int RED = 13;
int YELLOW = 12;
int GREEN = 11;
int BUTTON_ON = 6;
int BUTTON_OFF = 2;
int PIR_SENSOR = 4;

int button_on_state;
int button_off_state;
int ledState = HIGH;

int last_button_on_state = LOW;
int last_button_off_state = LOW;

int PIR_STATE = LOW;

int val = 0;
int STATE = 1;

const int LOCKED = 0;
const int UNLOCKED = 1;
int WAITING = 1;

unsigned long lastDebounceTime = 0; // the last time the output pin was toggled
unsigned long debounceDelay = 50;   // the debounce time; increase if the output flickers

class Button
{
private:
  bool _state;
  uint8_t _pin;
  uint8_t _counter;

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
        _counter += 1;
        return true;
      }
    }
    return false;
  }

  int pressed()
  {
    return _counter;
  }
};

Button myButton(6);
Button myButton2(2);

void setup()
{
  pinMode(RED, OUTPUT);
  pinMode(YELLOW, OUTPUT);
  pinMode(GREEN, OUTPUT);
  myButton.begin();
  myButton2.begin();
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
    int rightCombination = 1;
    while (rightCombination)
    {
      if (myButton.isReleased())
      {
        while (!myButton.isReleased())
        {
          if (myButton2.isReleased())
          {
            digitalWrite(YELLOW, LOW);
            digitalWrite(GREEN, HIGH);
          }
        }
        rightCombination = 0;
      }else if(myButton2.isReleased()){
        rightCombination = 0;
      }
      delay(50);
    }

    
    break;
  }
  PIR_STATE = digitalRead(PIR_SENSOR);
}
