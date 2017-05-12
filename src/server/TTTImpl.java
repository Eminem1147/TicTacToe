package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class TTTImpl extends UnicastRemoteObject implements TTT {

	private static final long serialVersionUID = -1374211975249548843L;
	private LinkedList<Player> players;
	private int playerID;
	
	protected TTTImpl() throws RemoteException {
		super();
		players = new LinkedList<Player>();
		playerID = 0;
	}

	@Override
	public void setPlayerInfo(String name) throws RemoteException {
		Player player = new Player();
		player.setName(name);
		player.setId(++playerID);
		player.setType(-1);
		player.setEnemyId(-1);
		player.setFlag(false);
		int chess[] = new int[10];
		for(int i = 1; i <= 9; i++) chess[i] = -1;
		player.setChess(chess);
		players.add(player);
	}
	
	public int getIndexById(int id) {
		int size = players.size();
		for(int i = 0; i < size; i++) {
			if(players.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	// 根据名字得到下标
	public int getIndex(String name) {
		int size = players.size();
		for(int i = 0; i < size; i++) {
			if(players.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	// 根据名字得到对象
	@Override
	public Player getPlayer(String name) throws RemoteException {
		return players.get(getIndex(name));
	}

	// 寻找对手
	@Override
	public void searchFor(String name) throws RemoteException, InterruptedException {
		while(true) {
			int index = getIndex(name);
//			System.out.println("链表现在的长度：" + players.size());
//			System.out.println("链表里现在的元素：");

			// 1.如果被动匹配到对手
//			System.out.println(players.get(0).getEnemyId());
			if(players.get(index).getEnemyId() != -1) return ;
			
			// 遍历链表
			int size = players.size();
			// players.get(i)是遍历到的
			// players.get(index)是当前的
			Thread.sleep(500); // 不加就不对，玄学
			for(int i = 0; i < size; i++) {
//				System.out.println(players.get(0).getEnemyId());
				if(i == index) {
					continue;
				// 2.如果主动匹配到对手
				} else if(players.get(i).getEnemyId() == -1) {
					// 互相设置
					players.get(index).setEnemyId(players.get(i).getId());
					players.get(index).setType(2);
					players.get(index).setFlag(false);
					players.get(i).setEnemyId(players.get(index).getId());
					players.get(i).setType(1);
					players.get(i).setFlag(true);
//					System.out.println(players.get(0).getEnemyId());
					System.out.println("匹配到两个对手：" + players.get(i).getName() + " 和 " + players.get(index).getName());
					return ;
				}
			}
		}
	}

	@Override
	public int[] receiveMove(String name) throws RemoteException, InterruptedException {
		int index = getIndex(name);
		while(true) {
			if(players.get(index).getFlag()) {
				return players.get(index).getChess();
			}
		}
	}

	@Override
	public void sendMove(String name, int move) throws RemoteException, InterruptedException {
		int index = getIndex(name);
		while(true) {
			if(players.get(index).getFlag()) {
				int chess[] = players.get(index).getChess();
				chess[move] = players.get(index).getType();
				players.get(index).setChess(chess);
				int enemy = getIndexById(players.get(index).getEnemyId());
				players.get(enemy).setChess(chess);
				// 释放锁
				players.get(index).setFlag(false);
				players.get(enemy).setFlag(true);
				
				// 输出棋盘
				for(int i = 1; i <= 9; i++) {
					System.out.print(chess[i] + " ");
				}
				System.out.println();
				
				break;
			}
		}
	}
	
}
