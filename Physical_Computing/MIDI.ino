#include <Audio.h>
#include <Wire.h>
#include <SPI.h>
#include <SD.h>
#include <SerialFlash.h>

// GUItool: begin automatically generated code
AudioPlaySdWav           playSdWav1;     //xy=147,225
AudioPlaySdWav           playSdWav7;     //xy=149,606
AudioPlaySdWav           playSdWav5;     //xy=150,495
AudioPlaySdWav           playSdWav4;     //xy=153,386
AudioPlaySdWav           playSdWav6;     //xy=153,555
AudioPlaySdWav           playSdWav2;     //xy=155,277
AudioPlaySdWav           playSdWav3;     //xy=162,332
AudioPlaySdWav           playSdWav10;    //xy=171,793
AudioPlaySdWav           playSdWav9;     //xy=181,739
AudioPlaySdWav           playSdWav8;     //xy=185,654
AudioMixer4              mixer1;         //xy=377,306
AudioMixer4              mixer2;         //xy=412,513
AudioMixer4              mixer3;         //xy=415,746
AudioMixer4              mixer4;         //xy=633,570
AudioOutputAnalogStereo  dacs1;          //xy=835,574
AudioConnection          patchCord1(playSdWav1, 0, mixer1, 0);
AudioConnection          patchCord2(playSdWav7, 0, mixer2, 2);
AudioConnection          patchCord3(playSdWav5, 0, mixer2, 0);
AudioConnection          patchCord4(playSdWav4, 0, mixer1, 3);
AudioConnection          patchCord5(playSdWav6, 0, mixer2, 1);
AudioConnection          patchCord6(playSdWav2, 0, mixer1, 1);
AudioConnection          patchCord7(playSdWav3, 0, mixer1, 2);
AudioConnection          patchCord8(playSdWav10, 0, mixer3, 1);
AudioConnection          patchCord9(playSdWav9, 0, mixer3, 0);
AudioConnection          patchCord10(playSdWav8, 0, mixer2, 3);
AudioConnection          patchCord11(mixer1, 0, mixer4, 0);
AudioConnection          patchCord12(mixer2, 0, mixer4, 1);
AudioConnection          patchCord13(mixer3, 0, mixer4, 2);
AudioConnection          patchCord14(mixer4, 0, dacs1, 0);
// GUItool: end automatically generated code

#include <Bounce.h>

const int num_buttons = 10;
const int buttons[num_buttons] = {22, 2, 20, 4, 18, 6, 16, 8, 14, 10};
const int potentiometers[5] = {33, 34, 35, 36, 37}; //defining potentiometers pins
const char* songs[num_buttons] = {"Faded.wav", "HowDeep.wav", "Chords.wav", "Chords2.wav", "Piano.wav", 
  "Sax.wav", "FX.wav", "Claps.wav", "Bass.wav", "Kicks.wav"};
const int debounce_time = 15;

Bounce buttons_bounced[num_buttons] =
{
  Bounce (22, debounce_time),
  Bounce (2, debounce_time),
  Bounce (20, debounce_time),
  Bounce (4, debounce_time),
  Bounce (18, debounce_time),
  Bounce (6, debounce_time),
  Bounce (16, debounce_time),
  Bounce (8, debounce_time),
  Bounce (14, debounce_time),
  Bounce (10, debounce_time)
};


void setup() {
//  You must allocate memory for your audio. 
//  The amount of memory needed depends on what you are doing. Start with 20.
//  Increase this number if the compiler complains or you don't hear sound when you expect it.
  Serial.begin(9600);
  while (!Serial && millis() < 2500) ; // wait
  Serial.println("Stereo DAC test");
  AudioMemory(20);
  if (!(SD.begin(BUILTIN_SDCARD))) {
    // stop here, but print a message repetitively
    while (1) {
      Serial.println("Unable to access the SD card");
      delay(500);
    }
  }
  for(int i = 0; i < num_buttons; i++)
  {
    pinMode(buttons[i], INPUT_PULLUP); //using buttons array to initialize buttons
  }

/*setting gain of mixer4 to 1 because at all times the output of mixer1 is max .4, 
mixer2 is max .4 and mixer3 is .2 which equal to 1*/
  mixer4.gain(0, 1); 
  mixer4.gain(1, 1);
  mixer4.gain(2, 1);
}

void loop() {
  // put your main code here, to run repeatedly:
  //first update all buttons
  for(int i = 0; i < num_buttons; i++)
  {
    buttons_bounced[i].update();
  }
  //then check for songs to play
  checkButtons();
  //finally, check potentiometers for volume
  checkVolume();
}

void checkButtons(){
  /*I thought about doing this with a for loop in order to optimize the code but since I have
  different playSdWav objects I endeed up with many if statements. 
  Every if statement follows the same pattern:
    -checks if playSdWav is not playing and if button is pressed
    -plays corresponding song on array
    -short delay for sd file to be read*/
  /*stop part of code was not implemented in video but if playSdWav is playing and that button
  is triggered then it stops the song.*/
  if(playSdWav1.isPlaying() == false && buttons_bounced[0].fallingEdge()) {
    Serial.print("Start playing 1");
    Serial.println(songs[0]); 
    playSdWav1.play(songs[0]); 
    delay(10);
  }
  else if(playSdWav1.isPlaying() && buttons_bounced[0].risingEdge()) {
    playSdWav1.stop();
  }
  
  if(playSdWav2.isPlaying() == false && buttons_bounced[1].fallingEdge()) {
    Serial.print("Start playing 2");
    Serial.println(songs[1]);
    playSdWav2.play(songs[1]);
    delay(10);
  }
  else if(playSdWav2.isPlaying() && buttons_bounced[1].risingEdge()) {
    playSdWav2.stop();
  }
  
  if(playSdWav3.isPlaying() == false && buttons_bounced[2].fallingEdge()) {
    Serial.print("Start playing 3");
    Serial.println(songs[2]);
    playSdWav3.play(songs[2]);
    delay(10);
  }
  else if(playSdWav3.isPlaying() && buttons_bounced[2].risingEdge()) {
    playSdWav3.stop();
  }
  
  if(playSdWav4.isPlaying() == false && buttons_bounced[3].fallingEdge()) {
    Serial.print("Start playing 4");
    Serial.println(songs[3]);
    playSdWav4.play(songs[3]);
    delay(10);
  }
  else if(playSdWav4.isPlaying() && buttons_bounced[3].risingEdge()) {
    playSdWav4.stop();
  }
  
  if(playSdWav5.isPlaying() == false && buttons_bounced[4].fallingEdge()) {
    Serial.print("Start playing 5");
    Serial.println(songs[4]);
    playSdWav5.play(songs[4]);
    delay(10);
  }
  else if(playSdWav5.isPlaying() && buttons_bounced[4].risingEdge()) {
    playSdWav5.stop();
  }
  
  if(playSdWav6.isPlaying() == false && buttons_bounced[5].fallingEdge()) {
    Serial.print("Start playing 6");
    Serial.println(songs[5]);
    playSdWav6.play(songs[5]);
    delay(10);
  }
  else if(playSdWav6.isPlaying() && buttons_bounced[5].risingEdge()) {
    playSdWav6.stop();
  }
  
  if(playSdWav7.isPlaying() == false && buttons_bounced[6].fallingEdge()) {
    Serial.print("Start playing 7");
    Serial.println(songs[6]);
    playSdWav7.play(songs[6]);
    delay(10);
  }
  else if(playSdWav7.isPlaying() && buttons_bounced[6].risingEdge()) {
    playSdWav7.stop();
  }
  
  if(playSdWav8.isPlaying() == false && buttons_bounced[7].fallingEdge()) {
    Serial.print("Start playing 8");
    Serial.println(songs[7]);
    playSdWav8.play(songs[7]);
    delay(10);
  }
  else if(playSdWav8.isPlaying() && buttons_bounced[7].risingEdge()) {
    playSdWav8.stop();
  }
  
  if(playSdWav9.isPlaying() == false && buttons_bounced[8].fallingEdge()) {
    Serial.print("Start playing 9");
    Serial.println(songs[8]);
    playSdWav9.play(songs[8]);
    delay(10);
  }
  else if(playSdWav9.isPlaying() && buttons_bounced[8].risingEdge()) {
    playSdWav9.stop();
  }
  
  if(playSdWav10.isPlaying() == false && buttons_bounced[9].fallingEdge()) {
    Serial.print("Start playing 10");
    Serial.println(songs[9]);
    playSdWav10.play(songs[9]);
    delay(10);
  }
  else if(playSdWav10.isPlaying() && buttons_bounced[9].risingEdge()) {
    playSdWav10.stop();
  }
}

void checkVolume(){
  /*First, I read all potentiometers values and divide by 1023 to convert to float. 
  Since the mixers must receive no greater than 1 and I have 4 inputs, I'm mutiplying that float
  value by 0.1 to use as gain*/
  float volumefloat = (analogRead(potentiometers[0])/1023)*0.1;
  float volumefloat2 = (analogRead(potentiometers[1])/1023)*0.1;
  float volumefloat3 = (analogRead(potentiometers[2])/1023)*0.1;
  float volumefloat4 = (analogRead(potentiometers[3])/1023)*0.1;
  float volumefloat5 = (analogRead(potentiometers[4])/1023)*0.1;
  /*Setting gain on mixers. Mixers 1 & 2 will output a max of .4 and mixer 3 a max .2*/
  mixer1.gain(0, volumefloat);
  mixer1.gain(1, volumefloat);
  mixer1.gain(2, volumefloat2);
  mixer1.gain(3, volumefloat2);
  mixer2.gain(0, volumefloat3);
  mixer2.gain(1, volumefloat3);
  mixer2.gain(2, volumefloat4);
  mixer2.gain(3, volumefloat4);
  mixer3.gain(0, volumefloat5);
  mixer3.gain(1, volumefloat5);
}

