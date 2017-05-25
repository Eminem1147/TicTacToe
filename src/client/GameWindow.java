package client;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * 游戏界面
 * 下棋速度不能太快！！！！！！！！！不然就有bug
 */
public class GameWindow implements ActionListener {
	
	// RMI
	private int chess[];
	
	// Swing变量
	private LoginWindow lg;
	private JFrame jf;
	private Container con;
	private JButton b[];
	private JLabel _name, name;
	private JLabel _score, myScore, hisScore, vs;
	private JLabel _first, first;
	private JButton newGame;
	private JButton exit; ///////////////////一个退出，另一个怎么办!!!!!!!
	private JLabel dtv;
	private JLabel _yourTurn, yourTurn;
	private JLabel win;
	
	public GameWindow(LoginWindow lg) throws RemoteException, InterruptedException {
		
		// 为了获取ttt和player！！！！！！！！！
		this.lg = lg;
		
		jf = new JFrame();
		
		// 设置布局
		con = jf.getContentPane();
		con.setLayout(null);
		
		_name = new JLabel("用户名：");
		_name.setFont(new Font("宋体", Font.BOLD, 18));
		_name.setForeground(Color.RED);
		_name.setBounds(400, 21, 80, 50);
		jf.add(_name);
		
		name = new JLabel(lg.player.getName());
		name.setFont(new Font("宋体", Font.BOLD, 18));
		name.setBounds(470, 21, 120, 50);
		jf.add(name);
		
		_score = new JLabel("当前比分：");
		_score.setForeground(Color.RED);
		_score.setFont(new Font("宋体", Font.BOLD, 18));
		_score.setBounds(400, 60, 120, 50);
		jf.add(_score);
		
		myScore = new JLabel("0");
		myScore.setFont(new Font("宋体", Font.BOLD, 30));
		myScore.setBounds(430, 100, 120, 50);
		jf.add(myScore);
		
		vs = new JLabel(":");
		vs.setFont(new Font("宋体", Font.BOLD, 30));
		vs.setBounds(470, 100, 50, 50);
		jf.add(vs);
		
		hisScore = new JLabel("0");
		hisScore.setFont(new Font("宋体", Font.BOLD, 30));
		hisScore.setBounds(510, 100, 120, 50);
		jf.add(hisScore);
		
		_first = new JLabel("您当前执的棋是：");
		_first.setForeground(Color.RED);
		_first.setFont(new Font("宋体", Font.BOLD, 18));
		_first.setBounds(400, 140, 200, 50);
		jf.add(_first);
		
		// 显示圈还是叉
		first = new JLabel();
		first.setBounds(450, 180, 200, 50);
		jf.add(first);
		
		newGame = new JButton("开始新的游戏");
		newGame.setFont(new Font("宋体", Font.BOLD, 20));
		newGame.setBounds(390, 240, 180, 30);
		newGame.setEnabled(false);
		newGame.addActionListener(this);
		jf.add(newGame);
		
		exit = new JButton("退出");
		exit.setFont(new Font("宋体", Font.BOLD, 20));
		exit.setBounds(430, 290, 90, 30);
		exit.addActionListener(this);
		jf.add(exit);
		
		dtv = new JLabel("Desktop Version");
		dtv.setFont(new Font("宋体", Font.BOLD, 10));
		dtv.setBounds(500, 340, 120, 50);
		jf.add(dtv);
		
		b = new JButton[10];
		for(int i = 1; i <= 9; i++) {
			b[i] = new JButton();
		}
		
		b[1].setBounds(20, 20, 100, 100);
		// 设置无边框
		b[1].setBorderPainted(false);
		b[1].setBackground(Color.WHITE);
		jf.add(b[1]);
		
		b[2].setBounds(130, 20, 100, 100);
		b[2].setBorderPainted(false);
		b[2].setBackground(Color.WHITE);
		jf.add(b[2]);
		
		b[3].setBounds(240, 20, 100, 100);
		b[3].setBorderPainted(false);
		b[3].setBackground(Color.WHITE);
		jf.add(b[3]);
		
		b[4].setBounds(20, 130, 100, 100);
		b[4].setBorderPainted(false);
		b[4].setBackground(Color.WHITE);
		jf.add(b[4]);
		
		b[5].setBounds(130, 130, 100, 100);
		b[5].setBorderPainted(false);
		b[5].setBackground(Color.WHITE);
		jf.add(b[5]);
		
		b[6].setBounds(240, 130, 100, 100);
		b[6].setBorderPainted(false);
		b[6].setBackground(Color.WHITE);
		jf.add(b[6]);
		
		b[7].setBounds(20, 240, 100, 100);
		b[7].setBorderPainted(false);
		b[7].setBackground(Color.WHITE);
		jf.add(b[7]);
		
		b[8].setBounds(130, 240, 100, 100);
		b[8].setBorderPainted(false);
		b[8].setBackground(Color.WHITE);
		jf.add(b[8]);
		
		b[9].setBounds(240, 240, 100, 100);
		b[9].setBorderPainted(false);
		b[9].setBackground(Color.WHITE);
		jf.add(b[9]);
		
		// 监听事件
		for(int i = 1; i <= 9; i++) {
			b[i].addActionListener(this);
		}
		
		_yourTurn = new JLabel();
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/lightning.png"));
		icon.setImage(icon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        _yourTurn.setIcon(icon);
        _yourTurn.setBounds(26, 342, 40, 40);
		jf.add(_yourTurn);
		// 需要一个label显示当前是否能下棋
		yourTurn = new JLabel();
		yourTurn.setFont(new Font("宋体", Font.BOLD, 18));
		yourTurn.setForeground(Color.RED);
		yourTurn.setBounds(50, 340, 120, 50);
		jf.add(yourTurn);
		
		win = new JLabel();
		win.setFont(new Font("宋体", Font.BOLD, 18));
		win.setForeground(Color.RED);
		win.setBounds(240, 340, 120, 50);
		jf.add(win);
		
		jf.setTitle("游戏界面");
		jf.setSize(600, 400);
		jf.setLocation(400, 200);
		jf.setResizable(false);
		jf.setVisible(true);
		
		// 重要的方法：用户关闭界面前必须要执行的方法
		jf.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				try {
					// 注销这个用户
					lg.ttt.unregister(lg.player.getName());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	// 查询当前用户是否存在，不存在直接退出游戏
	public void checkIsExist() throws RemoteException {
		if(!lg.ttt.checkExist(lg.player.getName())) {
			
			System.exit(0);
		}
	}
	
	// 核心代码!!!!!!!!!!!!
	// 在每个地方checkIsExist，检查对手是不是退出了
	public void playAGame() throws RemoteException, InterruptedException {
		// 初始化代码
		chess = new int[10];
		for(int i = 1; i <= 9; i++) chess[i] = -1;
		for(int i = 1; i <= 9; i++) change(b[i]);
		win.setText("");
		newGame.setEnabled(false);
		
		while(true) {
			setNotClickable(); // 不可点击
			checkIsExist();
			lg.player = lg.ttt.getPlayer(lg.player.getName()); // 更新
			// 初始化
			if(lg.player.getFlag()) yourTurn.setText("轮到您下棋");
			else yourTurn.setText("轮到对方下棋");
			
			// 初始化：圈还是叉
			if(lg.player.getType() == 1) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/img/o.png"));
				icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		        first.setIcon(icon);
		        first.setBackground(Color.WHITE);
			} else {
				ImageIcon icon = new ImageIcon(getClass().getResource("/img/x.png"));
				icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		        first.setIcon(icon);
		        first.setBackground(Color.WHITE);
			}
			
			checkIsExist();
			if(lg.player.getFlag()) {
				yourTurn.setText("轮到您下棋");
				checkIsExist();
				// 下之前先接收棋盘
				lg.player.setChess(lg.ttt.receiveMove(lg.player.getName()));
				chess = lg.player.getChess();
				// 不能int _chess[] = chess，一个改变，另一个也改变了
				int _chess[] = new int[10];
				for(int i = 1; i <= 9; i++) _chess[i] = chess[i];
				
				refresh(); // 刷新
				
				checkIsExist();
				// 1.判断自己是不是输了
				if(checkIsLose(lg.player.getChess(), lg.player.getType())) {
					// 输了！！！！！！！！！！！！！！
					newGame.setEnabled(true);
					win.setText("您输了！");
					yourTurn.setText("游戏结束！");
					// 不让他下棋了
					setNotClickable();
					// 设置比分
					int score = Integer.parseInt(hisScore.getText());
					score++;
					hisScore.setText(score + "");
					return ;
				}
				checkIsExist();
				// 2.判断是否平局
				if(checkIsDraw(lg.player.getChess())) {
					newGame.setEnabled(true);
					win.setText("平局了！");
					yourTurn.setText("游戏结束！");
					// 不让他下棋了
					setNotClickable();
					return ;
				}
				
				// 下棋，读入需要合格所以才要while(true)
				while(true) {
					checkIsExist();
					// 轮到他才能输入
					lg.player = lg.ttt.getPlayer(lg.player.getName()); //更新
					if(lg.player.getFlag()) {
						
						// 很重要的一步，检测用户按了一个按钮，只能是一个按钮
						setClickable(); // 可点击
						int move = 1;
						// 只能点击一个按钮
						while(true) {
							checkIsExist();
							boolean flag = false;
							for(move = 1; move <= 9; move++) {
								if(_chess[move] != chess[move]) {
									flag = true;
									break;
								}
							}
							if(flag) break;
						}
						setNotClickable(); // 不可点击

						yourTurn.setText("轮到对方下棋");

						checkIsExist();
						if(checkOK(move)) {
							// 1.判断自己是不是赢了，在send出去之前判断，但是不能break，要发送给那个失败的人
							boolean flag = checkIsWin(lg.player.getChess(), lg.player.getType(), move); // 赢了就要退出
							chess = lg.ttt.getPlayer(lg.player.getName()).getChess();
							if(flag) {
								newGame.setEnabled(true);
								win.setText("您赢了！");
								// 设置比分
								int score = Integer.parseInt(myScore.getText());
								score++;
								myScore.setText(score + "");
								yourTurn.setText("游戏结束！");
								checkIsExist();
								lg.ttt.sendMove(lg.player.getName(), move);
								// 不让他下棋了
								setNotClickable();
								return ;
							}
							// 2.判断是否平局
							if(checkIsDrawMove(chess, move)) {
								newGame.setEnabled(true);
								win.setText("平局了！");
								yourTurn.setText("游戏结束！");
								checkIsExist();
								lg.ttt.sendMove(lg.player.getName(), move);
								// 不让他下棋了
								setNotClickable();
								return ;
							}
							checkIsExist();
							lg.ttt.sendMove(lg.player.getName(), move);
							
							break;
						}
					}
				}
			}
		}
		
	}
	
	// 九宫格刷新
	public void refresh() {
		for(int i = 1; i <= 9; i++) {
			if(chess[i] == 1) {
				changeO(b[i]);
			}else if(chess[i] == 2) {
				changeX(b[i]);
			}
			for(int j = 1;j <= 9; j++) {
				if(chess[j] == -1) {
					b[j].setEnabled(true);
				} else {
					b[j].setEnabled(false);
				}
			}
		}
	}
	
	// 九宫格可点击与不可点击
	public void setClickable() {
		for(int i = 1;i <= 9; i++) {
			if(chess[i] == -1) {
				b[i].setEnabled(true);
			} else {
				b[i].setEnabled(false);
			}
		}
	}
	public void setNotClickable() {
		for(int i = 1; i <= 9; i++) {
			b[i].setEnabled(false);
		}
	}
	
	public boolean checkOK(int move) {
		if(lg.player.getChess()[move] == -1) return true;
		else return false;
	}
	
	// 检查是否平局
	public boolean checkIsDrawMove(int chess[], int move) {
		for(int i = 1; i <= 9; i++) {
			if(i == move) continue;
			if(chess[i] == -1) return false;
		}
		return true;
	}
	public boolean checkIsDraw(int chess[]) {
		for(int i = 1; i <= 9; i++) {
			if(chess[i] == -1) return false;
		}
		return true;
	}
	
	// 检查是否赢了
	public boolean checkIsWin(int chess[], int type, int move) {
		chess[move] = type; //当前的步骤
		if((chess[1]==type&&chess[2]==type&&chess[3]==type)||
		   (chess[4]==type&&chess[5]==type&&chess[6]==type)||
		   (chess[7]==type&&chess[8]==type&&chess[9]==type)||
		   (chess[1]==type&&chess[4]==type&&chess[7]==type)||
		   (chess[2]==type&&chess[5]==type&&chess[8]==type)||
		   (chess[3]==type&&chess[6]==type&&chess[9]==type)||
		   (chess[1]==type&&chess[5]==type&&chess[9]==type)||
		   (chess[3]==type&&chess[5]==type&&chess[7]==type))
			return true;
		else return false;
	}
	
	// 检查是否输了
	public boolean checkIsLose(int chess[], int type) {
		if(type == 1) type = 2;
		else type = 1;
		if((chess[1]==type&&chess[2]==type&&chess[3]==type)||
		   (chess[4]==type&&chess[5]==type&&chess[6]==type)||
		   (chess[7]==type&&chess[8]==type&&chess[9]==type)||
		   (chess[1]==type&&chess[4]==type&&chess[7]==type)||
		   (chess[2]==type&&chess[5]==type&&chess[8]==type)||
		   (chess[3]==type&&chess[6]==type&&chess[9]==type)||
		   (chess[1]==type&&chess[5]==type&&chess[9]==type)||
		   (chess[3]==type&&chess[5]==type&&chess[7]==type))
			return true;
		else return false;
	}
	
	// JButton改变显示
	public void change(JButton button) {
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/white.png"));
		icon.setImage(icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        button.setIcon(icon);
        button.setBackground(Color.WHITE);
	}
	public void changeO(JButton button) {
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/o.png"));
		icon.setImage(icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        button.setIcon(icon);
        button.setBackground(Color.WHITE);
	}
	public void changeX(JButton button) {
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/x.png"));
		icon.setImage(icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        button.setIcon(icon);
        button.setBackground(Color.WHITE);
	}

	// 九宫格的监听事件
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b[1]) {
			// 1圈2叉
			if(lg.player.getType() == 1) {
				changeO(b[1]);
			} else {
				changeX(b[1]);
			}
			b[1].setEnabled(false);
			chess[1] = lg.player.getType();
		} else if(e.getSource() == b[2]) {
			if(lg.player.getType() == 1) {
				changeO(b[2]);
			} else {
				changeX(b[2]);
			}
			b[2].setEnabled(false);
			chess[2] = lg.player.getType();
		} else if(e.getSource() == b[3]) {
			if(lg.player.getType() == 1) {
				changeO(b[3]);
			} else {
				changeX(b[3]);
			}
			b[3].setEnabled(false);
			chess[3] = lg.player.getType();
		} else if(e.getSource() == b[4]) {
			if(lg.player.getType() == 1) {
				changeO(b[4]);
			} else {
				changeX(b[4]);
			}
			b[4].setEnabled(false);
			chess[4] = lg.player.getType();
		} else if(e.getSource() == b[5]) {
			if(lg.player.getType() == 1) {
				changeO(b[5]);
			} else {
				changeX(b[5]);
			}
			b[5].setEnabled(false);
			chess[5] = lg.player.getType();
		} else if(e.getSource() == b[6]) {
			if(lg.player.getType() == 1) {
				changeO(b[6]);
			} else {
				changeX(b[6]);
			}
			b[6].setEnabled(false);
			chess[6] = lg.player.getType();
		} else if(e.getSource() == b[7]) {
			if(lg.player.getType() == 1) {
				changeO(b[7]);
			} else {
				changeX(b[7]);
			}
			b[7].setEnabled(false);
			chess[7] = lg.player.getType();
		} else if(e.getSource() == b[8]) {
			if(lg.player.getType() == 1) {
				changeO(b[8]);
			} else {
				changeX(b[8]);
			}
			b[8].setEnabled(false);
			chess[8] = lg.player.getType();
		} else if(e.getSource() == b[9]) {
			if(lg.player.getType() == 1) {
				changeO(b[9]);
			} else {
				changeX(b[9]);
			}
			b[9].setEnabled(false);
			chess[9] = lg.player.getType();
		} else if(e.getSource() == newGame) {
			newGame.setEnabled(false);
			// 界面刷新问题，需要启动一个线程
			new Thread(new Runnable(){
				@Override
				public void run() {
					// 开始了新的游戏
					// 首先交换先后手顺序
					try {
						checkIsExist();
						lg.ttt.sendNewGame(lg.player.getName());
						checkIsExist();
						// 交换先后手，谁比分低谁下一把先手
						lg.ttt.changeFirst(lg.player.getName());
						playAGame();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}).start();
		} else if(e.getSource() == exit) {
			try {
				lg.ttt.unregister(lg.player.getName());
				System.exit(0);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}

}
