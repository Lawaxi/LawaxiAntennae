package net.lawaxi.antennae.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.resource.metadata.TextureResourceMetadata;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.client.texture.TextureUtil;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CapeTexture extends ResourceTexture {

    private static final String url = "http://121.37.27.192:233/capes/%s.png";
    private NativeImage image;

    public CapeTexture(String uuid, Identifier location) {
        super(location);

        try {
            System.out.println(String.format(this.url,uuid));
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(String.format(this.url,uuid)).openConnection();
            httpURLConnection.setDoInput(true);
            InputStream a = httpURLConnection.getInputStream();
            System.out.println("responseCode is:" + httpURLConnection.getResponseCode());

            if(a!=null && httpURLConnection.getResponseCode()==200){
                image = NativeImage.read(a);
            }else{
                image=null;
            }
            httpURLConnection.disconnect();

        }catch (IOException e){
            image = null;
        }
    }



    @Override
    protected ResourceTexture.TextureData loadTextureData(ResourceManager resourceManager) {

        if(image!=null) {
            //System.out.println(1);

            try {
                //出错位置
                Resource resource = resourceManager.getResource(location);
                TextureResourceMetadata textureResourceMetadata = resource.getMetadata(TextureResourceMetadata.READER);
                TextureData a = new ResourceTexture.TextureData(textureResourceMetadata, image);
                resource.close();

                return a;

            } catch (IOException e) {

                //System.out.println(2);
                return new ResourceTexture.TextureData(null,image);
            }
        }else{
            //System.out.println(3);
            return ResourceTexture.TextureData.load(resourceManager, this.location);
        }

    }

    public boolean has(){
        return image!=null;
    }
}
