package net.lawaxi.antennae.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Mixin(InetAddress.class)
public class InetAddressMixin {

    @Overwrite
    public static InetAddress getByName(String host)
            throws UnknownHostException {

        if (host.equalsIgnoreCase("lawaxi"))
            return InetAddress.getAllByName("mc.lawaxi.net")[0];
        else
            return InetAddress.getAllByName(host)[0];
    }
}
