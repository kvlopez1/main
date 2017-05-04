/*I used the files AnalogReadLEDOutMulti02 to test the sensors before hand and 
ledArrayExampled01 as a reference to use the arrays for the sensors and leds*/

const int sensorPin[4] = {A0, A1, A2, A3}; //defining sensor pins
const int ledPins[4] = {3, 4, 5, 6}; //defining led pins
int sensorValue[4]; //setting up array for analogRead
int thresholdValue = 0; //variable to  set threshold
int pinsOn = 0; //variable to keep track of how many pins are on
int lowestValue = 0; //variable used to increment threshold

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  for(int i = 0; i < 4; i++)
  {
    pinMode(ledPins[i], OUTPUT); //using ledPins array to define outputs
  }
}

void loop() {
  // put your main code here, to run repeatedly:
  if(thresholdValue <= 1023)
  {
    Serial.print("Threshold: "); // for testing purposes
    Serial.println(thresholdValue);
    getSensorValues(); //calling method to get sensor values on array.
    for(int i = 0; i < 4; i++)
    {
      /*since I'm starting the thresholdValue at 0 if the sensorValue if less 
      or equal then I'm turning on the light.*/
      if(sensorValue[i] <= thresholdValue)
      {
        digitalWrite(ledPins[i], HIGH);
      }

      /*this if statement checks which is the lowest value out of all the 
      sensors and stores it in the lowestValue variable which is the one
      I'm using to increment the threshold value. I'll explain why I decided 
      to do it like this on the documentation*/
      // i needs to be greater than 0 in order to have something to compare
      if(i > 0) 
      {
        if(sensorValue[i] <= sensorValue[i - 1])
        {
          lowestValue = sensorValue[i];
        }
        else
        {
          lowestValue = sensorValue[i - 1];
        }
      }
    }
    thresholdValue += lowestValue; //increment threshold with lowestValue
    delay(50);
  }
  else
  {
    /*this for loop checks how many leds are on and storing the count on the
    variable pinsOn. I'm using that variable to turn the lights off*/
    for(int i = 0; i < 4; i++)
    {
      if(digitalRead(ledPins[i]) == HIGH)
      {
        pinsOn += 1;
      }
    }
    thresholdValue = 0; //resetting thresholdValue
    if(pinsOn >= 4) //turning all lights off only if all leds are on
    {
      turnAllLightOFF();
    }
    delay(500); //adding delay
  }
}

/*this method calls the analogRead function and stores the value on the 
sensorValue array*/
void getSensorValues()
{
  for(int i = 0; i < 4; i++)
  {
    sensorValue[i] = analogRead(sensorPin[i]);
    Serial.print("Sensor "); // for testing purposes
    Serial.print(i);
    Serial.print(" = ");
    Serial.println(sensorValue[i]);
  } 
}

/*this method loops through all 4 leds and calls digitalWrite to turn them off*/
void turnAllLightOFF()
{
  for(int i = 0; i < 4; i++)
  {
    digitalWrite(ledPins[i], LOW);
  }
}

/*this method loops through all 4 leds and calls digitalWrite to turn them on*/
void turnAllLightON()
{
  for(int i = 0; i < 4; i++)
  {
    digitalWrite(ledPins[i], HIGH);
  }
}

