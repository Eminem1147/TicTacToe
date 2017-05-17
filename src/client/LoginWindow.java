package client;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import server.Player;
import server.TTT;

/*
 * 登录界面//////////////////////注册完直接关
 */
public class LoginWindow implements ActionListener {
	
	// RMI的各种变量
	public Player player;
	public TTT ttt;
	
	// Swing的各种变量
	private JFrame jf;
	private Container con;
	private TextField name;
	private JButton register, login;
	private JLabel judge;

	public LoginWindow(TTT ttt) {
		// 传递服务器对象
		this.ttt = ttt;
		
		jf = new JFrame();
		
		con = jf.getContentPane();
		con.setLayout(new GridLayout(5, 1));
		
		JLabel empty1 = new JLabel();
		con.add(empty1);
		
		JPanel panel1 = new JPanel();
		JLabel title = new JLabel("欢迎登录三子棋系统");
		title.setFont(new Font("宋体", Font.BOLD, 30));
		panel1.add(title);
		con.add(panel1);
		
		JPanel panel2 = new JPanel();
		JLabel _name = new JLabel("用户名");
		_name.setFont(new Font("宋体", Font.BOLD, 20));
		panel2.add(_name);
		
		// 限制十个字符
		name = new TextField(10);
		panel2.add(name);
		
		register = new JButton("注册");
		register.setFont(new Font("宋体", Font.BOLD, 15));
		panel2.add(register);
		register.addActionListener(this); // 监听事件
		con.add(panel2);
		
		JPanel panel3 = new JPanel();
		JLabel remind = new JLabel("友情提示：用户名不可重复，是您的唯一通行证");
		remind.setFont(new Font("宋体", Font.BOLD, 13));
		remind.setForeground(Color.RED);
		panel3.add(remind);
		con.add(panel3);
		
		JPanel panel4 = new JPanel();
		login = new JButton("搜寻对手");
		login.setFont(new Font("宋体", Font.BOLD, 22));
		panel4.add(login);
		// 一开始设置成不可以点击
		login.setEnabled(false);
		login.addActionListener(this); // 监听事件
		// 为了设置图片！！！！！！！！！！！！
		judge = new JLabel();
		panel4.add(judge);
		con.add(panel4);

		jf.setTitle("三子棋登录界面");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		jf.setSize(600, 400);
		jf.setLocation(400, 200);
		jf.setResizable(true);
		jf.setVisible(true);

		// ------------------RMI相关代码---------------------
		player = new Player();
	}

	// 监听事件
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == register) {
			try {
				// 检查名字是否冲突，调用远程checkName接口（要考虑为空的情况）
				// 同时要记得去除文本框前面和后面的空格，所以trim()
				if(name.getText().trim().equals("")||!ttt.checkName(name.getText().trim())) {
					name.setText("");
					ImageIcon icon = new ImageIcon(getClass().getResource("/img/wrong.jpg"));
					icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
			        judge.setIcon(icon);
				} else { // 用户名可用
					ImageIcon icon = new ImageIcon(getClass().getResource("/img/correct.jpg"));
					icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
			        judge.setIcon(icon);
			        // 成功了文本框和注册按钮就不可编辑!!!!!!!!!
			        register.setEnabled(false);
			        name.setEnabled(false);
					login.setEnabled(true);
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		} else if(e.getSource() == login) {
			
	        // 成功了之后设置player和远程对象
	        player.setName(name.getText().trim());
	        System.out.println(player.getName());
	        // 在这里才能向服务器申请!!!!!!!!!!!!!!
	        try {
				ttt.setPlayerInfo(player.getName());
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			// 有问题！！！！！！！！！！！！！！
			login.setText("正在搜寻对手");
			login.setEnabled(false);
			ImageIcon icon = new ImageIcon(getClass().getResource("/img/wait.png"));
			icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	        judge.setIcon(icon);
	        ////////////////////////////////////
	        
	        try {
	        	// 寻找对手
				ttt.searchFor(player.getName());
				// 更新player
				player = ttt.getPlayer(player.getName());
				
				// 测试
				System.out.println("我的ID：" + player.getId());
				System.out.println("我的Name：" + player.getName());
				System.out.println("我的EnemyID：" + player.getEnemyId());
				System.out.println("我的Type：" + player.getType());
				System.out.println("我的Flag：" + player.getFlag());
	        	
				// 当前界面隐藏
				jf.setVisible(false);
				
				new GameWindow(this);
				
			} catch (RemoteException | InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
	
//	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
//		new LoginWindow((TTT)Naming.lookup("rmi://localhost:8888/TTT"));
//	}

}
