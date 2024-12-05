package edu.ntnu.idi.idatt.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.util.Scanner;

/**
 * Class that plays small sound effects to spice up the food waste application.
 */
public class SoundPlayer {

  // current position
  Clip clip;

  // current status of clip
  String status;

  // defines the AutioInputStream object:
  AudioInputStream audioInputStream;

  /**
   * Allows the use of sound effects in the application.
   */
  public SoundPlayer() {
  }

  public void playConfirmSound() {
    try {
      clip = AudioSystem.getClip();

      InputStream confirmSound = getClass().getResourceAsStream("/sounds/confirmSound.wav");

      audioInputStream = AudioSystem.getAudioInputStream(confirmSound);

      clip.open(audioInputStream);
      clip.start();

    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }

  public void playInputSound() {
    try {
      clip = AudioSystem.getClip();

      InputStream inputSound = getClass().getResourceAsStream("/sounds/inputSound.wav");

      audioInputStream = AudioSystem.getAudioInputStream(inputSound);

      clip.open(audioInputStream);
      clip.start();

    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }
}
