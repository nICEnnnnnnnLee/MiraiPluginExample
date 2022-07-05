package top.nicelee.mirai.plugin;

import net.mamoe.mirai.console.command.CommandExecuteResult;
import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.plugin.PluginManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MusicKind;
import net.mamoe.mirai.message.data.MusicShare;
import top.nicelee.mirai.plugin.PluginData;

public class MyPlugin extends JavaPlugin {
	public static final MyPlugin INSTANCE = new MyPlugin();
	
	private MyPlugin() {
		super(new JvmPluginDescriptionBuilder(PluginData.id, PluginData.version)
				.name(PluginData.name)
				.author(PluginData.author)
				.info(PluginData.info)
				.build()
		);
	}

	@Override
	public void onEnable() {
		// 注册指令
		boolean r = CommandManager.INSTANCE.registerCommand(MySimpleCommand.INSTANCE, false);
		getLogger().info(String.format("命令行加载成功：%s", r));
		
		// 注册事件
		EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this);
//        eventChannel.subscribeAlways(GroupMessageEvent.class, g -> {
//            //监听群消息
//            getLogger().info(g.getMessage().contentToString());
//
//        });
        eventChannel.subscribeAlways(FriendMessageEvent.class, f -> {
        	//监听好友消息
        	String msg = f.getMessage().contentToString();
        	if(msg.startsWith("/test")) {
        		CommandExecuteResult result = CommandManager.INSTANCE.executeCommand(CommandSender.from(f), f.getMessage(), false);
        		String rstr = (result instanceof CommandExecuteResult.Success) ? "成功": "失败";
        		getLogger().info("执行result： " + rstr);
        		f.getSender().sendMessage("消息发送" + rstr);
        	}else if(msg.startsWith("/music")) {
        		MusicShare ms = new MusicShare(
        				MusicKind.NeteaseCloudMusic, 
        				"标题", 
        				"摘要", 
        				"https://www.baidu.com", // 跳转路径
        				"http://p1.music.126.net/KaYSb9oYQzhl2XBeJcj8Rg==/109951165125601702.jpg",  //封面路径
        				"http://music.163.com/song/media/outer/url?id=4940920.mp3", //音乐路径
        				"简介"
        		);
        		f.getSender().sendMessage(ms);
        	}
            getLogger().info(msg);
        });
		getLogger().info("插件加载完毕!");
	}

}


