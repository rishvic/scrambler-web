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
