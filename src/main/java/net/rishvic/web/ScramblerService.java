package net.rishvic.web;

import jakarta.enterprise.context.ApplicationScoped;
import org.worldcubeassociation.tnoodle.scrambles.Puzzle;
import org.worldcubeassociation.tnoodle.scrambles.PuzzleRegistry;
import org.worldcubeassociation.tnoodle.scrambles.InvalidScrambleException;
import org.worldcubeassociation.tnoodle.svglite.Svg;

@ApplicationScoped
public class ScramblerService {

  private final Puzzle scrambler3x3 = PuzzleRegistry.THREE.getScrambler();

  ScramblerService() {}

  public String getScramble3x3(String seed) {
    return seed == null
        ? scrambler3x3.generateScramble()
        : scrambler3x3.generateSeededScramble(seed);
  }

  public Svg drawScramble(String scramble) {
    try {
      return scrambler3x3.drawScramble(scramble, null);
    } catch (InvalidScrambleException e) {
      return null;
    }
  }
}
