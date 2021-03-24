package chess;

public class Main {


    public static void main(String[] args) {

    	Pieces pieces = new Pieces();
        pieces.setGUIGame(true);
		new GUIBoard(pieces);
		
		/*
		JFrame window = new JFrame("Chess");
		window.setVisible(true);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(new GUIBoard(pieces));
		window.pack();*/




    }

}
