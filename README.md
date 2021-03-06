# TicTacToe(Author: Jian Zhang)
### 使用了Java RMI和Java Swing框架。
#### （完全独立开发，没有参考网上任何代码（也找不到。。。））
### 运行步骤：
1. 进入目录：`cd TicTacToe/bin`，然后打开一个命令行窗口，执行命令：`rmic server.TTTImpl`，生成stub的class文件。再次执行命令：`java server.TTTServer`，这个命令开启了一个服务器，这个窗口就代表一个服务器，不能关闭。
2. 在当前目录再打开一个命令行窗口，执行命令：`java client.TTTClient`，这个命令打开了客户端的登陆界面。
3. 再打开一个新窗口，重复步骤2，代表另一个客户端。（可以开启很多窗口，代表不同的客户端）
4. 输入用户名，并点击注册按钮，系统会检测此用户名是否被使用过。（用户名是唯一的）
5. 点击查找对手按钮，系统按先后顺序匹配。
6. 当匹配成功就进入了游戏界面，于是就可以进行游戏了。界面会提示用户执的棋、当前是谁下棋、谁赢谁输以及当前的比分。
7. 当一局结束，可以点击开始新的游戏按钮，当两个用户都愿意进行下一局游戏（即都点击了这个按钮），就可以开始下一局游戏了。
8. 用户随时都可以退出，当自己退出时，对手也无法继续进行游戏，也会退出。
### 注意事项！！！
1. 图片文件夹img保存在bin目录内，src目录内无图片文件夹
2. 在登录界面，用户如果在点击了注册之后直接关闭了界面，则直接结束程序，他的信息也不会存入服务器;用户如果在点击了查找对手之后关闭了界面，界面只是隐藏了，当查找到对手之后，还会进行游戏（为了不让用户逃避对局）。
3. \*对局速度不能太快！！！！！！！！！！！！！！！！！！！！\*（否则有一定几率出现bug）
4. 用户可以在任何时候退出游戏界面，当自己退出之后，对手也无法进行游戏！（这里有两种情况：（1）对局进行到一半，退出的话两个人直接退出;（2）对局结束后如果有一个人退出，对手不会马上退出！但是之后无论点击哪个按钮都会退出游戏界面。）
5. 用户退出游戏界面之后，他的帐号信息会从服务器被清除！（但是有一种情况不会：一局正常结束了之后，对手退出了）
6. 服务器的searchFor（查找对手的方法）中有一行Thread.sleep(500);不加这一行就很容易出现bug，涉及多线程相关。（bug是当b去匹配a，可以;当a去匹配b，a被动，a就会卡死，它不知道自己已经被匹配了）
7. 本程序因为频繁需要更新按钮的状态，但是因为Swing的界面刷新问题不支持，所以需要多线程，详情可见我的博客：<http://blog.csdn.net/qq_33765907/article/details/72742267>
8. 可以更改`rmi://localhost:8888/TTT`中的localhost和端口号。
9. JavaBean-Player这个类必须序列化（即实现Serializable接口），否则无法在服务器和客户端之间传输。
10. 两个重要的操作：sendMove和receiveMove，为了使它们正常操作互不影响，给两个用户加一把锁（类似于进程的互斥）。
11. 所有代码在JDK8在运行成功。
