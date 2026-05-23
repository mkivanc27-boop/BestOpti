package com.mustafa.optibest;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class BestOptiConfigScreen extends Screen {

    private final Screen parent;
    private final BestOptiConfig config = BestOptiConfig.get();

    public BestOptiConfigScreen(Screen parent) {
        super(Text.literal("BestOpti Ayarları"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int startY = 40;
        int spacing = 28;

        // Render Distance Slider
        this.addDrawableChild(new SliderWidget(
                centerX - 150, startY, 300, 20,
                Text.literal("Render Mesafesi: " + config.renderDistance),
                (config.renderDistance - 2) / 30.0
        ) {
            @Override
            protected void updateMessage() {
                int val = (int) (this.value * 30) + 2;
                setMessage(Text.literal("Render Mesafesi: " + val));
            }

            @Override
            protected void applyValue() {
                config.renderDistance = (int) (this.value * 30) + 2;
            }
        });

        // Entity Mesafesi Slider
        this.addDrawableChild(new SliderWidget(
                centerX - 150, startY + spacing, 300, 20,
                Text.literal("Entity Mesafesi: " + config.entityDistance + " blok"),
                (config.entityDistance - 16) / 112.0
        ) {
            @Override
            protected void updateMessage() {
                int val = (int) (this.value * 112) + 16;
                setMessage(Text.literal("Entity Mesafesi: " + val + " blok"));
            }

            @Override
            protected void applyValue() {
                config.entityDistance = (int) (this.value * 112) + 16;
            }
        });

        // Max Parçacık Slider
        this.addDrawableChild(new SliderWidget(
                centerX - 150, startY + spacing * 2, 300, 20,
                Text.literal("Max Parçacık: " + config.maxParticles),
                config.maxParticles / 500.0
        ) {
            @Override
            protected void updateMessage() {
                int val = (int) (this.value * 500);
                setMessage(Text.literal("Max Parçacık: " + val));
            }

            @Override
            protected void applyValue() {
                config.maxParticles = (int) (this.value * 500);
                config.limitParticles = config.maxParticles > 0;
            }
        });

        // FPS Boost Modu Butonu
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("FPS Boost Modu: " + (config.fpsBoostMode ? "AÇIK ✓" : "KAPALI ✗")),
                btn -> {
                    config.fpsBoostMode = !config.fpsBoostMode;
                    btn.setMessage(Text.literal(
                        "FPS Boost Modu: " + (config.fpsBoostMode ? "AÇIK ✓" : "KAPALI ✗")
                    ));
                    if (config.fpsBoostMode) {
                        // FPS Boost: her şeyi minimuma al
                        config.renderDistance = 4;
                        config.entityDistance = 24;
                        config.maxParticles = 20;
                    } else {
                        // Normal: varsayılana dön
                        config.renderDistance = 8;
                        config.entityDistance = 48;
                        config.maxParticles = 100;
                    }
                    // Yeniden init et slider'ları güncellemek için
                    clearChildren();
                    init();
                }
        ).dimensions(centerX - 150, startY + spacing * 3, 300, 20).build());

        // Kaydet ve Çık
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Kaydet ve Çık"),
                btn -> {
                    BestOptiConfig.save();
                    BestOptiConfig.apply();
                    this.client.setScreen(parent);
                }
        ).dimensions(centerX - 150, startY + spacing * 4, 300, 20).build());

        // İptal
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("İptal"),
                btn -> this.client.setScreen(parent)
        ).dimensions(centerX - 150, startY + spacing * 5, 300, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(
            this.textRenderer,
            this.title,
            this.width / 2, 15, 0xFFFFFF
        );
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        this.client.setScreen(parent);
    }
}
