package client;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//////////////////////退出后信息要删除
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
		
		_first = new JLabel("您当前的执棋先后：");
		_first.setForeground(Color.RED);
		_first.setFont(new Font("宋体", Font.BOLD, 18));
		_first.setBounds(400, 140, 200, 50);
		jf.add(_first);
		
		first = new JLabel("先手");
		first.setFont(new Font("宋体", Font.BOLD, 20));
		first.setBounds(450, 180, 200, 50);
		jf.add(first);
		
		newGame = new JButton("开始新的游戏");
		newGame.setFont(new Font("宋体", Font.BOLD, 20));
		newGame.setBounds(390, 240, 180, 30);
		newGame.setEnabled(false);
		jf.add(newGame);
		
		exit = new JButton("退出");
		exit.setFont(new Font("宋体", Font.BOLD, 20));
		exit.setBounds(430, 290, 90, 30);
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
		
		// 需要一个label显示当前是否能下棋
		
		jf.setTitle("游戏界面");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(600, 400);
		jf.setLocation(400, 200);
		jf.setResizable(false);
		jf.setVisible(true);

		chess = new int[10];
		playGame();
		
	}
	
	public void playGame() throws RemoteException, InterruptedException {
		while(true) {
			for(int i = 1; i <= 9; i++) chess[i] = -1;
			for(int i = 1; i <= 9; i++) change(b[i]);
			// 测试
			System.out.println("我的ID：" + lg.player.getId());
			System.out.println("我的Name：" + lg.player.getName());
			System.out.println("我的EnemyID：" + lg.player.getEnemyId());
			System.out.println("我的Type：" + lg.player.getType());
			System.out.println("我的Flag：" + lg.player.getFlag());
//			playAGame();
		}
	}
	
	public void playAGame() throws RemoteException, InterruptedException {
		
		while(true) {
			setNotClickable(); // 不可点击
			lg.player = lg.ttt.getPlayer(lg.player.getName()); // 更新
			
			if(lg.player.getFlag()) {
				// 下之前先接收棋盘
				lg.player.setChess(lg.ttt.receiveMove(lg.player.getName()));
				chess = lg.player.getChess();
				
				refresh(); // 刷新
				
				// 判断自己是不是输了
				if(checkIsLose(lg.player.getChess(), lg.player.getType())) {
					System.out.println("You lose!");
					return ;
				}
				
				// 输出棋盘
				for(int i = 1; i <= 9; i++) {
					System.out.print(lg.player.getChess()[i] + " ");
				}
				System.out.println();
				
				// 下棋，读入需要合格所以才要while(true)
				while(true) {
					// 轮到他才能输入
					lg.player = lg.ttt.getPlayer(lg.player.getName()); //更新
					System.out.println("我的Flag：" + lg.player.getFlag());
					if(lg.player.getFlag()) {
						
						int _chess[] = chess;
						
						setClickable(); // 可点击
						int i = 1;
						// 只能点击一个按钮
						while(true) {
							boolean flag = false;
							for(i = 1; i <= 9; i++) {
								if(_chess[i] != chess[i]) {
									flag = true;
									break;
								}
							}
							if(flag) break;
						}
						setNotClickable(); // 不可点击
						
						if(checkOK(i)) {
							// 判断自己是不是赢了，在send出去之前判断，但是不能break，要发送给那个失败的人
							boolean flag = checkIsWin(lg.player.getChess(), lg.player.getType(), i); // 赢了就要退出
							chess = lg.ttt.getPlayer(lg.player.getName()).getChess();
							if(flag) {
								System.out.println("You Win!");
							}
							lg.ttt.sendMove(lg.player.getName(), i);
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
		}
	}

//	public static void main(String[] args) {
//		new GameWindow();
//	}

}
