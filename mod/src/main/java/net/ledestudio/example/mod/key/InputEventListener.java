package net.ledestudio.example.mod.key;

import net.ledestudio.example.mod.client.Client;
import net.ledestudio.example.mod.data.User;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import static com.mojang.text2speech.Narrator.LOGGER;
import static net.ledestudio.example.mod.ExampleMod.MODID;


@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class InputEventListener {

    private static boolean isDown = false;
    public static boolean canJumping = false;
    static Integer count = 0;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onClientTick(ClientTickEvent.Post event) {

        Minecraft mc = Minecraft.getInstance();

        Player player = mc.player;
        while (InputMapping.SuperJump.get().consumeClick() && count < 100) {
            isDown = true;
            count++; // 1초에 30 올라감
            LOGGER.info(count.toString());
            if (count % 20 == 0 && count < 100) showTitle(mc, "차징중", count + "%");
            if (count == 100) {
                LOGGER.info("차징 완료");
                showTitle(mc, "차징완료", "100%");

                Client client = new Client("localhost", 1234);

                User user = new User("SE0K", 3);
                client.sendPacket(user.toByteBuf());

                canJumping = true;
            }
        }
        if (!isDown) {
            assert player != null;
            if (count < 100) {
                player.sendSystemMessage(Component.nullToEmpty("초기화 됨"));
            }
            else {
                player.sendSystemMessage(Component.nullToEmpty("슈퍼점프"));
            }
            count = 0;
        }
    }
    private static void showTitle(Minecraft mc, String title, String subTitle) {
        if (mc.player != null) {
            mc.gui.setTitle(Component.literal(title));
            mc.gui.setSubtitle(Component.literal(subTitle));
        }
    }
}

