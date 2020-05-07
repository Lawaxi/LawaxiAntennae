package net.lawaxi.antennae;

import net.fabricmc.api.ModInitializer;

public class Antennae implements ModInitializer {
	@Override
	public void onInitialize() {

		System.out.println("啦哇嘻十分高兴！你让他的小触角得以显示！");

		//噢~你打开了这个mod的代码~你点开了你看到的第一个文件~
		//允许我来讲述一下这个mod的思路和实现方法

		//思路：
		//2020.5.7 一个特别的日子，御花园群在讨论啦哇嘻的皮肤
		//有人说啦哇嘻的触角躺在头上很不好看
		//噢我的天哪
		//Mojang给了一个叫Deadmau5的人一对老鼠耳朵
		//他竟然忘记了给我，Lawaxi一双触角
		//那么我只好通过mod来实现这一点

		//实现方法：
		//玩家模型的构建在PlayerEntityRenderer中
		//官方给了一个Feature的方法用来实现他想要实现的一些小彩蛋
		//像Deadmau5的老鼠耳朵，一些玩家的披风都是通过这个方法实现的
		//感谢SpongeMixin，我可以偷偷将LawaxiFeatureRenderer夹杂在其中
		//非常好 你可以在PlayerEntityRendererMixin中看到它的实现
		//然后我仿照Deadmau5FeatureRenderer把这个Feature写好 大功告成~
	}
}
