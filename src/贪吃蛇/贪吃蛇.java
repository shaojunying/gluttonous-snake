package 贪吃蛇;

import javax.swing.JFrame;

public class 贪吃蛇 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
			JFrame frame =new JFrame();
			frame.setBounds(10,10,900,720);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			SnackPanel panel=new SnackPanel();
			frame.add(panel);
			frame.setVisible(true);
	}


}
