import javax.swing.*;
import java.awt.*;

/**
 * 可视化界面
 *
 * @author 11D_Beyonder
 * @date 2021 Apr. 19th
 */
public class PlanDisplay {
	public static void main(String[] args) {
		//输入框输入
		int n = Integer.valueOf(JOptionPane.showInputDialog(null, "皇后个数")).intValue();
		//创建窗口
		JFrame frame = new JFrame("n皇后");
		frame.setBounds(0, 0, 700, 700);
		frame.setLayout(new GridLayout(n, n));
		long startTime = System.currentTimeMillis();
		//获得答案
		boolean[][] ans = (new PlanFinder(n)).findAnswer();
		long endTime = System.currentTimeMillis();
		//输出运行时间
		JOptionPane.showMessageDialog(null, "运行" + (endTime - startTime) + "ms得到方案");
		//输出摆放方案
		if (n <= 100) {
			JButton[][] panels = new JButton[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					//黑色为皇后
					panels[i][j] = new JButton();
					panels[i][j].setBackground(ans[i][j] ? Color.BLACK : Color.WHITE);
					panels[i][j].setVisible(true);
					frame.add(panels[i][j]);
				}
			}
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} else {
			JOptionPane.showMessageDialog(null, "皇后数量过多，不显示。");
		}
	}
}
