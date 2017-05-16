package client;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameWindow {
	
	// Swing变量
	private JFrame jf;
	private Container con;
	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;
	private JLabel _name, name;
	private JLabel _score, myScore, hisScore, vs;
	private JLabel _first, first;
	private JButton newGame;
	private JButton exit; ///////////////////一个退出，另一个怎么办
	private JLabel dtv;
	
	public GameWindow() {
		
		jf = new JFrame();
		
		// 设置布局
		con = jf.getContentPane();
		con.setLayout(null);
		
		_name = new JLabel("用户名：");
		_name.setFont(new Font("宋体", Font.BOLD, 18));
		_name.setBounds(400, 21, 80, 50);
		jf.add(_name);
		
		name = new JLabel("zhangjian1"); ///////////////////改
		name.setFont(new Font("宋体", Font.BOLD, 18));
		name.setBounds(470, 21, 120, 50);
		jf.add(name);
		
		_score = new JLabel("当前比分：");
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
		
		System.out.println(System.getProperty("java.class.path")); 
		ImageIcon icon = new ImageIcon(getClass().getResource("/image/wrong.jpg"));
		icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        b1.setIcon(icon);
		b1.setBounds(20, 20, 100, 100);
		// 设置无边框
//		b1.setBorderPainted(false);
//		b1.setBackground(Color.WHITE);
		jf.add(b1);
		
		jf.setTitle("游戏界面");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(600, 400);
		jf.setLocation(400, 200);
		jf.setResizable(false);
		jf.setVisible(true);
		
	}

	public static void main(String[] args) {
		new GameWindow();
	}

}
