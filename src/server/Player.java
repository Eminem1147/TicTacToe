package server;

import java.io.Serializable;

// 必须序列化，否则会报错（无法在服务器与客户端之间传输）
public class Player implements Serializable{

	private static final long serialVersionUID = 2180605647710675847L;
	
	private int id;
	private String name;
	// 标记画圈还是画叉，1圈2叉
	private int type;
	// 敌方ID
	private int enemyId;
	// 棋盘
	private int chess[];
	// 正要下true，等待下false
	private boolean flag;
	// 判断用户是否想要开始新的一局
	private boolean newGame;
	// 比分
	private int score;
	// 用户是否还存在
	private boolean exist;

	public Player() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	// 用name互相区分
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



	public Player(int id, String name, int type, int enemyId, int chess[], boolean flag, boolean newGame, int score, boolean exist) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.enemyId = enemyId;
		this.chess = chess;
		this.flag = flag;
		this.newGame = newGame;
		this.score = score;
		this.exist = exist;
	}
	
	public boolean getExist() {
		return exist;
	}
	
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public boolean getNewGame() {
		return newGame;
	}
	
	public void setNewGame(boolean newGame) {
		this.newGame = newGame;
	}
	
	public boolean getFlag() {
		return flag;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public int[] getChess() {
		return chess;
	}
	
	public void setChess(int chess[]) {
		this.chess = chess;
	}
	
	public int getEnemyId() {
		return enemyId;
	}
	
	public void setEnemyId(int enemyId) {
		this.enemyId = enemyId;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
