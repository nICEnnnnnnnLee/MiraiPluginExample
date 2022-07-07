# mirai-console插件样例  
maven + java ~~gradle + kotlin~~  
备忘，防止一段时间后又要重新翻一遍文档。  
只需`mvn clean package`将生成的jar包放到mcl的`plugins`文件夹下即可。  
以下为测试过的核心依赖版本：  
mirai-core: 2.12.0    
mirai-console: 2.12.0    
Mirai Console Loader version 2.1.0-71ec418

# 实现功能  
+ 好友输入命令/music，将返回固定写死的音乐分享
+ 控制台，或者QQ好友输入命令
```
/test <fQQ号码> <msg>
向某QQ好友发送消息msg

/test <g群号> <msg>
向某QQ群发送消息msg

/test <m群号.QQ号码> <msg>
向某QQ群的某位成员发送消息msg

e.g. 向QQ好友10000发送消息Hello
/test f10000 Hello
```


# 食用方法  
该项目基于模板[khjxiaogu/MiraiPluginTemplate](https://github.com/khjxiaogu/MiraiPluginTemplate)改造。  
