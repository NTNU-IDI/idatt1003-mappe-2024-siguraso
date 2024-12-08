package edu.ntnu.idi.idatt.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class that plays small sound effects to spice up the user experience in the FoodWasteApp. The
 * sounds are stored in the main/resources/sounds directory. The sounds are played when the user
 * confirms an action or does a smaller input (e.g. selecting entering a menu).
 */
public class SoundPlayer {

  // more or less what is used to play the sound.
  private Clip clip;

  // defines the AutioInputStream object:
  private AudioInputStream audioInputStream;

  private boolean muteSound = false;

  /**
   * Class that allows the use of sound effects in the FoodWasteApp. Sounds that are used throughout
   * the application are stored in the main/resources/sounds directory. The sounds played in this
   * class are small menu sounds that only serve to enhance the user experience by giving audio
   * feedback.
   */
  public SoundPlayer() {
  }

  /**
   * Plays the confirmation sound, a sound that is played when the user defines a variable or
   * confirms an action. Sounds more or less like a small "click" sound.
   */
  public void playConfirmSound() {
    if (muteSound) {
      // If the sound is muted, return without playing the sound.
      return;
    }

    try {
      clip = AudioSystem.getClip();

      // creates an InputStream for the confirmSound, more or less used to get the file location of
      // the sound.
      InputStream confirmSound = getClass().getResourceAsStream("/sounds/confirmSound.wav");

      // If the file is not found, throw an IOException
      if (confirmSound == null) {
        throw new IOException();
      }

      // Here, we need to create a wrapper for the confirmsound inputstream, as instances of
      // InputStream don't support mark and reset, something that is needed when playing the sound
      // in a .jar file.
      BufferedInputStream confirmSoundWrapper = new BufferedInputStream(confirmSound);

      // sets the audioInputStream to the confirmSoundWrapper.
      audioInputStream = AudioSystem.getAudioInputStream(confirmSoundWrapper);

      // opens the audio input stream to the clip
      clip.open(audioInputStream);

      // plays the sound
      clip.start();

    } catch (UnsupportedAudioFileException e) {
      // If the file is not supported, throw an IllegalArgumentException with a message that
      // explains the problem.
      throw new IllegalArgumentException(
          "UnsupportedAudioException: Could not play the sound, unsupported file type");
    } catch (IOException e) {
      // If the sound file is not available, throw an IllegalArgumentException with a message that
      // explains the problem.
      throw new IllegalArgumentException(
          "IOException: Could not play the sound, sound file not found");
    } catch (LineUnavailableException e) {
      // If the line is not available, throw an IllegalArgumentException with a message that
      // explains the problem.
      throw new IllegalArgumentException("LineUnavailableException: Could not play the sound, "
          + "line is unavailable");
    }

    // the above crashed should not be an issue when running the .jar file, as the sounds are
    // included in the .jar file, hence these errors should not occur, however it is good to throw
    // these errors when testing the program.
  }

  /**
   * Plays the input sound, a sound that is played when the user does an input action, for example,
   * selecting what menu to go to or selecting a specific item. Sounds more or less like a small,
   * and light "ding" sound.
   */
  public void playInputSound() {
    if (muteSound) {
      // If the sound is muted, return without playing the sound.
      return;
    }

    try {
      clip = AudioSystem.getClip();

      // creates an InputStream for the inputSound, more or less used to get the file location of
      // the sound.
      InputStream inputSound = getClass().getResourceAsStream("/sounds/inputSound.wav");

      // If the file is not found, throw an IOException
      if (inputSound == null) {
        throw new IOException();
      }

      // Here, we need to create a wrapper for the inputsound inputstream, as instances of
      // InputStream don't support mark and reset, something that is needed when playing the sound
      // in a .jar file.
      BufferedInputStream inputSoundWrapper = new BufferedInputStream(inputSound);

      // sets the audioInputStream to the inputSoundWrapper,
      audioInputStream = AudioSystem.getAudioInputStream(inputSoundWrapper);

      clip.open(audioInputStream);
      clip.start();

    } catch (UnsupportedAudioFileException e) {
      throw new IllegalArgumentException(
          "UnsupportedAudioException: Could not play the sound, unsupported file type");
    } catch (IOException e) {
      throw new IllegalArgumentException(
          "IOException: Could not play the sound, sound file not found");
    } catch (LineUnavailableException e) {
      throw new IllegalArgumentException("LineUnavailableException: Could not play the sound, "
          + "line is unavailable");
    }
  }

  /**
   * Plays the add sound, a sound that is played when the user adds an item to the food storage. It
   * contains a funky man saying the phrase "Yeah!".
   */
  public void playAddSound() {
    if (muteSound) {
      // If the sound is muted, return without playing the sound.
      return;
    }

    try {
      clip = AudioSystem.getClip();

      // creates an InputStream for the inputSound, more or less used to get the file location of
      // the sound.
      InputStream addSound = getClass().getResourceAsStream("/sounds/addSound.wav");

      // If the file is not found, throw an IOException
      if (addSound == null) {
        throw new IOException();
      }

      // Here, we need to create a wrapper for the inputsound inputstream, as instances of
      // InputStream don't support mark and reset, something that is needed when playing the sound
      // in a .jar file.
      BufferedInputStream addSoundWrapper = new BufferedInputStream(addSound);

      // sets the audioInputStream to the inputSoundWrapper,
      audioInputStream = AudioSystem.getAudioInputStream(addSoundWrapper);

      clip.open(audioInputStream);
      clip.start();

    } catch (UnsupportedAudioFileException e) {
      throw new IllegalArgumentException(
          "UnsupportedAudioException: Could not play the sound, unsupported file type");
    } catch (IOException e) {
      throw new IllegalArgumentException(
          "IOException: Could not play the sound, sound file not found");
    } catch (LineUnavailableException e) {
      throw new IllegalArgumentException("LineUnavailableException: Could not play the sound, "
          + "line is unavailable");
    }
  }

  /**
   * Plays the remove sound, a sound that is played when the user removes an item from the food
   * storage. It contains a man screaming (NOT LOUD!).
   */
  public void playRemoveSound() {
    if (muteSound) {
      // If the sound is muted, return without playing the sound.
      return;
    }

    try {
      clip = AudioSystem.getClip();

      // creates an InputStream for the inputSound, more or less used to get the file location of
      // the sound.
      InputStream removeSound = getClass().getResourceAsStream("/sounds/removeSound.wav");

      // If the file is not found, throw an IOException
      if (removeSound == null) {
        throw new IOException();
      }

      // Here, we need to create a wrapper for the inputsound inputstream, as instances of
      // InputStream don't support mark and reset, something that is needed when playing the sound
      // in a .jar file.
      BufferedInputStream removeSoundWrapper = new BufferedInputStream(removeSound);

      // sets the audioInputStream to the inputSoundWrapper,
      audioInputStream = AudioSystem.getAudioInputStream(removeSoundWrapper);

      clip.open(audioInputStream);
      clip.start();

    } catch (UnsupportedAudioFileException e) {
      throw new IllegalArgumentException(
          "UnsupportedAudioException: Could not play the sound, unsupported file type");
    } catch (IOException e) {
      throw new IllegalArgumentException(
          "IOException: Could not play the sound, sound file not found");
    } catch (LineUnavailableException e) {
      throw new IllegalArgumentException("LineUnavailableException: Could not play the sound, "
          + "line is unavailable");
    }
  }

  /**
   * Toggles weather or not the sound is muted. All menu sounds are muted when the sound is muted.
   */
  public void toggleSoundMute() {
    muteSound = !muteSound;
  }

  /**
   * Checks weather or not the sound is muted.
   *
   * @return {@link Boolean} true if the sound is muted, false if it is not.
   */
  public boolean isMuted() {
    return muteSound;
  }
}
