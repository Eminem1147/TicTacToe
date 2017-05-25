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

	// 检查用户名是否用过
	@Override
	public boolean checkName(String name) {
		int size = players.size();
		for(int i = 0; i < size; i++) {
			if(players.get(i).getName().equals(name)) {
				return false;
			}
		}
		System.out.println("有用户注册：" + name);
		return true;
	}

	@Override
	public boolean setPlayerInfo(String name) throws RemoteException {
		Player player = new Player();
		player.setName(name);
		player.setId(++playerID);
		player.setType(-1);
		player.setEnemyId(-1);
		player.setFlag(false);
		int chess[] = new int[10];
		for(int i = 1; i <= 9; i++) chess[i] = -1;
		player.setChess(chess);
		player.setNewGame(false);
		player.setScore(0);
		player.setExist(true);
		players.add(player);
		return true;
	}
	
	// 为了能正确使用get方法，需要覆盖Player的equals方法
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

	// 寻找对手，服务器端最重要的两个函数
	@Override
	public void searchFor(String name) throws RemoteException, InterruptedException {
		while(true) {
			int index = getIndex(name);
			
			// 1.如果被动匹配到对手			
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
				break;
			}
		}
	}

	@Override
	public void sendNewGame(String name) throws RemoteException {
		int index = getIndex(name);
		players.get(index).setNewGame(true);
	}

	// 交换先后手，顺便全部重新初始化
	// 谁先点击谁就是先手
	@Override
	public void changeFirst(String name) throws RemoteException {
		// 一个人发送了，另一个人怎么办
		while(true) {
			int index = getIndex(name);
			int enemyIndex = getIndexById(players.get(index).getEnemyId());
			// 要等待另一个人也点击开始新的游戏按钮
			if(players.get(enemyIndex).getNewGame()) {
				players.get(index).setFlag(false);
				players.get(enemyIndex).setFlag(true);
					
				// 棋盘初始化
				int _chess[] = new int[10];
				for(int i = 1; i <= 9; i++) {
					_chess[i] = -1;
				}
				players.get(index).setChess(_chess);
				players.get(enemyIndex).setChess(_chess);
				
				return ;
			}
		}
	}

	// 注销用户(如果有对手的话还要注销对手)
	// remove操作不能放在这里，不然客户端就正常执行下去了
	// 把remove操作放在checkExist里面，也方便通知另一个客户端
	@Override
	public void unregister(String name) throws RemoteException {
		int index = getIndex(name);
		players.get(index).setExist(false);
		if(players.get(index).getEnemyId() != -1) {
			int enemyIndex = getIndexById(players.get(index).getEnemyId());
			players.get(enemyIndex).setExist(false);
		}
	}

	// 用户每一个操作之前都要先执行这个方法
	@Override
	public boolean checkExist(String name) throws RemoteException {
		int index = getIndex(name);
		if(players.get(index).getExist()) {
			return true;
		} else {
			players.remove(index);
			return false;
		}
	}
	
}
