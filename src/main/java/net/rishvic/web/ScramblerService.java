/* ScramblerService.java -- Service to generate scrambles.
 * Copyright (C) 2023  Rishvic Pushpakaran
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.  */

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
