int RED = 13;
int YELLOW = 12;
int GREEN = 11;
int BUTTON_ON = 6;
int BUTTON_OFF = 2;

int button_on_state = 0; 
int button_off_state = 0; 
void setup()
    {
    pinMode(RED, OUTPUT);
    pinMode(YELLOW, OUTPUT);
    pinMode(GREEN, OUTPUT);
    pinMode(BUTTON_ON, INPUT);
    pinMode(BUTTON_OFF, INPUT);
}

void loop()
{
    button_on_state = digitalRead(BUTTON_ON);
    button_off_state = digitalRead(BUTTON_OFF);
    digitalWrite(13, HIGH);
    delay(1000); // Wait for 1000 millisecond(s)
    digitalWrite(13, LOW);
    delay(1000); // Wait for 1000 millisecond(s)
    
    if (button_on_state == HIGH) {
    // turn LED on:
    digitalWrite(GREEN, HIGH);
  } else {
    // turn LED off:
    digitalWrite(GREEN, LOW);
  }
}