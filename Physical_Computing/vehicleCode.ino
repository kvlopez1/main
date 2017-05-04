//Setting up global variables.
//Defining left motor pins
const int motor1PinPWM = 3; 
const int motor1Pin1Y = 11;
const int motor1Pin2Y = 12;
//Defining right motor pins
const int motor2PinPWM = 4; 
const int motor2Pin1Y = 13;
const int motor2Pin2Y = 14;
//Defining light sensor pins
const int lightSensorLeft = A1; 
const int lightSensorRight = A2;
//Setting up variables for AnalogRead
int leftSensorValue;
int rightSensorValue; 
//Setting up variables for motors speeds
int leftMotorSpeed = 0;
int rightMotorSpeed = 0;

void setup() {
  Serial.begin(9600);
  //setting up output pins.
  pinMode(motor1PinPWM, OUTPUT); 
  pinMode(motor1Pin1Y, OUTPUT);
  pinMode(motor1Pin2Y, OUTPUT);
  pinMode(motor2PinPWM, OUTPUT);
  pinMode(motor2Pin1Y, OUTPUT);
  pinMode(motor2Pin2Y, OUTPUT);
  driveStop(); //stopping motors during setup
  enableMotors(); //enabling motos
}

void loop() {
  getSensorValues();
  setMotorSpeed();
  runsMotorsForward();
}

/*this method, as the name predicts, uses the method analogRead to get 
sensor values and assigns them to global variables.*/
void getSensorValues()
{
  //getting sensor values and assigning to variables
  leftSensorValue = analogRead(lightSensorLeft);
  rightSensorValue = analogRead(lightSensorRight);
  //for testing purposes
  Serial.print("left sensor = ");
  Serial.println(leftSensorValue);
  Serial.print("right sensor = ");
  Serial.println(rightSensorValue);
  delay(10);
}

/*this is needed for setup in order for the wheels to not turn*/
void driveStop()
{
  digitalWrite(motor1Pin1Y, LOW); //stop left
  digitalWrite(motor1Pin2Y, LOW);
  digitalWrite(motor2Pin1Y, LOW); //stop right
  digitalWrite(motor2Pin2Y, LOW);
}

/*setting up motors*/
void enableMotors()
{
  digitalWrite(motor1PinPWM, HIGH); //enable left wheel driver
  digitalWrite(motor2PinPWM, HIGH); //enable right wheel driver
}

/*This method will work so that when there's very little/no light
the motors will run slow and when there's a lot of light (high value reading)
the motors will speed up*/
void setMotorSpeed()
{
  if(leftSensorValue < 600) //then light's minimal
  {
    leftMotorSpeed = map(leftSensorValue, 100, 600, 0, 100);
  }
  else //light reading's high (up to 1023)
  {
    leftMotorSpeed = map(leftSensorValue, 600, 1023, 300, 255);
  }

  if(rightSensorValue < 600) //then light's minimal
  {
    rightMotorSpeed = map(rightSensorValue, 100, 600, 0, 100);
  }
  else //light reading's high (up to 1023)
  {
    rightMotorSpeed = map(rightSensorValue, 600, 1023, 300, 255);
  }
  
  leftMotorSpeed = constrain(leftMotorSpeed, 0, 300);
  rightMotorSpeed = constrain(rightMotorSpeed, 0, 300);
}

/*this method is the last to be called and uses the speeds defined on the 
setMotorSpeed method.*/
void runsMotorsForward()
{
  digitalWrite(motor1Pin1Y, LOW); // left wheel forward
  digitalWrite(motor1Pin2Y, HIGH);
  digitalWrite(motor2Pin1Y, LOW); // right wheel forward
  digitalWrite(motor2Pin2Y, HIGH);

  analogWrite(motor1PinPWM, leftMotorSpeed);
  analogWrite(motor2PinPWM, rightMotorSpeed);
}
