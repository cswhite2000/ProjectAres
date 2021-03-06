package tc.oc.pgm.modules;

import java.util.logging.Logger;

import org.jdom2.Document;
import org.jdom2.Element;
import tc.oc.pgm.map.MapModule;
import tc.oc.pgm.map.MapModuleContext;
import tc.oc.pgm.match.Match;
import tc.oc.pgm.match.MatchModuleFactory;
import tc.oc.pgm.module.ModuleDescription;
import tc.oc.pgm.utils.XMLUtils;
import tc.oc.pgm.xml.InvalidXMLException;

@ModuleDescription(name="Max Build Height")
public class MaxBuildHeightModule implements MapModule, MatchModuleFactory<MaxBuildHeightMatchModule> {
    private final int buildHeight;

    public MaxBuildHeightModule(int buildHeight) {
        this.buildHeight = buildHeight;
    }

    @Override
    public MaxBuildHeightMatchModule createMatchModule(Match match) {
        return new MaxBuildHeightMatchModule(match, this.buildHeight);
    }

    public int getBuildHeight() {
        return buildHeight;
    }

    // ---------------------
    // ---- XML Parsing ----
    // ---------------------

    public static MaxBuildHeightModule parse(MapModuleContext context, Logger logger, Document doc) throws InvalidXMLException {
        Element maxBuildHeightEl = XMLUtils.getUniqueChild(doc.getRootElement(), "maxbuildheight");
        if(maxBuildHeightEl == null) {
            return null;
        } else {
            return new MaxBuildHeightModule(XMLUtils.parseNumber(maxBuildHeightEl, Integer.class));
        }
    }
}
