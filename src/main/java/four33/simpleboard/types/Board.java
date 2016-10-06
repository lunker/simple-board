package four33.simpleboard.types;

public class Board {
	private int boardId;
	private String boardName;
	private int boardType;
	
	public Board(){}
	
	public Board(int boardId, String boardName, int boardType) {
	
		this.boardId = boardId;
		this.boardName = boardName;
		this.boardType = boardType;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public int getBoardType() {
		return boardType;
	}
	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}
	
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("\nboard id : " + boardId);
		sb.append("\nboard name : " + boardName);
		sb.append("\nboard type : " + boardType);
		
		return sb.toString();
	}
	
	
	
}
