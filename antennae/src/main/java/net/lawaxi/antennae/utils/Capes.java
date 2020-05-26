package net.lawaxi.antennae.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.concurrent.Executor;

public class Capes {

    public static final HashMap<String,Boolean> has = new HashMap<>();

    public static Identifier get(String uuid){

        if(has.containsKey(uuid)){
            if(has.get(uuid)==false)
            {
                return null;
            }
        }


        Identifier id = new Identifier("lawaxi", "capes/" + uuid);
        if(has.containsKey(uuid)){
            return id;
        }
        else{
            TextureManager textureManager = MinecraftClient.getInstance().getTextureManager();
            ResourceTexture texture = new CapeTexture(uuid,id);
            if(((CapeTexture) texture).has()) {

                has.put(uuid,((CapeTexture) texture).has());
                textureManager.registerTexture(id, texture);
                return id;
            }
            else {
                has.put(uuid,false);
                return null;
            }
        }

    }
}
