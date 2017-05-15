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

import server.TTT;

/*
 * 登录界面
 */
public class LoginWindow implements ActionListener {
	
	private JFrame jf;
	private Container con;
	private TextField name;
	private JButton register, login;
	private TTT ttt;
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
		
		name = new TextField(20);
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
		login = new JButton("登录系统");
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
		jf.setResizable(true);
		jf.setVisible(true);
		
		jf.setSize(600, 400);
		jf.setLocation(400, 200);
		
	}

	// 监听事件
	@Override
	public void actionPerformed(ActionEvent e) {
		String source = e.getActionCommand();
		if(source.equals("注册")) {
			try {
				// 名字可用
				if(ttt.checkName(name.getText())) {
					login.setEnabled(true);
					ImageIcon icon = new ImageIcon(getClass().getResource("/img/correct.jpg"));
					icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
			        judge.setIcon(icon);
				} else {
					name.setText("");
					// 有问题！！！！！！！！！
					ImageIcon icon = new ImageIcon(getClass().getResource("/img/wrong.jpg"));
					icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
			        judge.setIcon(icon);
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		} else if(source.equals("登陆系统")) {
			
		}
	}
	
//	public static void main(String[] args) {
//		new LoginWindow();
//	}

}
