/* ScramblerPage.java -- Template renderer for the page.
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

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.worldcubeassociation.tnoodle.svglite.Svg;

import static java.util.Objects.requireNonNull;

@Path("/")
public class ScramblerPage {

  private final Template page;
  private final ScramblerService scramblerService;

  public ScramblerPage(Template page, ScramblerService scramblerService) {
    this.page = requireNonNull(page, "page is required");
    this.scramblerService = scramblerService;
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  public TemplateInstance get(@QueryParam("seed") String seed) {
    String scramble = scramblerService.getScramble3x3(seed);
    Svg scrambleSvg = scramblerService.drawScramble(scramble);
    return page.data("scramble", scramble)
        .data("scrambleSvg", scrambleSvg == null ? "Invalid scramble" : scrambleSvg.toString());
  }
}
