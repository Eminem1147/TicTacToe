package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import server.Player;
import server.TTT;

public class TTTClient {
	
	private Player player;
	private TTT ttt;
	private Scanner sc;
	
	public TTTClient() throws InterruptedException {
		// 远程返回一个对象
		try {
			ttt = (TTT)Naming.lookup("rmi://localhost:8888/TTT"); ////////////改
			
			player = new Player();
			new LoginWindow(ttt);
			// 设置名字
			sc = new Scanner(System.in);
			player.setName(sc.next());
			ttt.setPlayerInfo(player.getName());
			
			// 寻找对手
			ttt.searchFor(player.getName());
			// 更新
			player = ttt.getPlayer(player.getName());
			
			// 测试
			System.out.println("我的ID：" + player.getId());
			System.out.println("我的Name：" + player.getName());
			System.out.println("我的EnemyID：" + player.getEnemyId());
			System.out.println("我的Type：" + player.getType());
			
			playGame();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public void playGame() throws RemoteException, InterruptedException {
		while(true) {
			player = ttt.getPlayer(player.getName());
			if(player.getFlag()) {
				// 下之前先接收棋盘
				player.setChess(ttt.receiveMove(player.getName()));
				
				// 判断自己是不是输了
				if(checkIsLose(player.getChess(), player.getType())) {
					System.out.println("You lose!");
					return ;
				}
				
				// 输出棋盘
				for(int i = 1; i <= 9; i++) {
					System.out.print(player.getChess()[i] + " ");
				}
				System.out.println();
				// 下棋，读入需要合格所以才要while(true)
				while(true) {
					// 轮到他才能输入
					player = ttt.getPlayer(player.getName()); //更新
//					System.out.println("我的Flag：" + player.getFlag());
					if(player.getFlag()) {
						int move = sc.nextInt();
						if(checkOK(move)) {
							// 判断自己是不是赢了，在send出去之前判断，但是不能break，要发送给那个失败的人
							boolean flag = checkIsWin(player.getChess(), player.getType(), move); // 赢了就要退出
							if(flag) {
								System.out.println("You Win!");
							}
							ttt.sendMove(player.getName(), move);
							if(flag) return ;
							break;
						} else {
							System.out.println("读入不合法！");
						}
					}
				}
			}
		}
	}
	
	public boolean checkIsWin(int chess[], int type, int move) {
		chess[move] = type; //当前的步骤
		if((chess[1]==type&&chess[2]==type&&chess[3]==type)||
		   (chess[4]==type&&chess[5]==type&&chess[6]==type)||
		   (chess[7]==type&&chess[8]==type&&chess[9]==type)||
		   (chess[1]==type&&chess[4]==type&&chess[7]==type)||
		   (chess[2]==type&&chess[5]==type&&chess[8]==type)||
		   (chess[3]==type&&chess[6]==type&&chess[9]==type))
			return true;
		else return false;
	}
	
	public boolean checkIsLose(int chess[], int type) {
		if(type == 1) type = 2;
		else type = 1;
		if((chess[1]==type&&chess[2]==type&&chess[3]==type)||
		   (chess[4]==type&&chess[5]==type&&chess[6]==type)||
		   (chess[7]==type&&chess[8]==type&&chess[9]==type)||
		   (chess[1]==type&&chess[4]==type&&chess[7]==type)||
		   (chess[2]==type&&chess[5]==type&&chess[8]==type)||
		   (chess[3]==type&&chess[6]==type&&chess[9]==type))
			return true;
		else return false;
	}
	
	public boolean checkOK(int move) {
		if(player.getChess()[move] == -1) return true;
		else return false;
	}
	
	// 入口函数
	public static void main(String[] args) throws InterruptedException {
		new TTTClient();
	}
}
