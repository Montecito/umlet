package com.baselet.elementnew.element.uml;

import java.util.Arrays;
import java.util.List;

import com.baselet.diagram.draw.BaseDrawHandler;
import com.baselet.diagram.draw.geom.Rectangle;
import com.baselet.element.sticking.StickingPolygon;
import com.baselet.element.sticking.StickingPolygonGenerator;
import com.baselet.elementnew.ElementId;
import com.baselet.elementnew.NewGridElement;
import com.baselet.elementnew.PropertiesConfig;
import com.baselet.elementnew.facet.Facet;
import com.baselet.elementnew.facet.common.SeparatorLineFacet;
import com.baselet.elementnew.settings.Settings;
import com.baselet.elementnew.settings.SettingsAutoresize;

public class Interface extends NewGridElement {

	private int TOP_DISTANCE = 10;
	private int CIRCLE_SIZE = 20;

	private final StickingPolygonGenerator interfacePolygonGenerator = new StickingPolygonGenerator() {
		@Override
		public StickingPolygon generateStickingBorder(Rectangle rect) {
			StickingPolygon p = new StickingPolygon(rect.x, rect.y);
			p.addRectangle(circleRect());
			return p;
		}
	};
	
	@Override
	public ElementId getId() {
		return ElementId.UMLInterface;
	}

	@Override
	protected void drawCommonContent(BaseDrawHandler drawer, PropertiesConfig propCfg) {
		propCfg.addToYPos(TOP_DISTANCE + CIRCLE_SIZE);// space reserved for the top circle
		Rectangle circleRect = circleRect();
		drawer.drawCircle(circleRect.x + CIRCLE_SIZE/2, circleRect.y + CIRCLE_SIZE/2, CIRCLE_SIZE/2);
		
		propCfg.setStickingPolygonGenerator(interfacePolygonGenerator);
	}

	private Rectangle circleRect() {
		int middlePos = getRealSize().getWidth() / 2 - CIRCLE_SIZE/2;
		return new Rectangle(middlePos, TOP_DISTANCE, CIRCLE_SIZE, CIRCLE_SIZE);
	}

	@Override
	protected Settings createSettings() {
		return new SettingsAutoresize() {
			@Override
			public List<? extends Facet> createFacets() {
				return Arrays.asList(SeparatorLineFacet.INSTANCE);
			}
		};
	}
}