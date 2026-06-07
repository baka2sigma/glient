/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package meteordevelopment.meteorclient.gui.themes.meteor.widgets;

import meteordevelopment.meteorclient.gui.renderer.GuiRenderer;
import meteordevelopment.meteorclient.gui.renderer.packer.GuiTexture;
import meteordevelopment.meteorclient.gui.themes.meteor.MeteorWidget;
import meteordevelopment.meteorclient.gui.widgets.WWidget;
import meteordevelopment.meteorclient.gui.widgets.containers.WSection;
import meteordevelopment.meteorclient.gui.widgets.pressable.WTriangle;

public class WMeteorSection extends WSection {
    public WMeteorSection(String title, boolean expanded, WWidget headerWidget) {
        super(title, expanded, headerWidget);
    }

    public GuiTexture ttex;

    @Override
    protected WHeader createHeader() {
        return new WMeteorHeader(title);
    }

    protected class WMeteorHeader extends WHeader {
        private WTriangle triangle;

        public WMeteorHeader(String title) {
            super(title);
        }

        @Override
        public void init() {
            add(theme.horizontalSeparator(title)).expandX();

            if (headerWidget != null) add(headerWidget);

            triangle = new WHeaderTriangle();
            triangle.theme = theme;
            triangle.action = this::onClick;

            add(triangle);
        }

        @Override
        protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
            triangle.texture = (animProgress == 1) ? GuiRenderer.MINUS : GuiRenderer.PLUS;
            ttex = triangle.texture;
        }
    }

    protected class WHeaderTriangle extends WTriangle implements MeteorWidget {
        protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
            renderer.rotatedQuad(x, y, width, height, rotation, ttex, theme().textColor.get());
        }
    }
}
