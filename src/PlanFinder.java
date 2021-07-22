import java.util.Random;

/**
 * @author 11D_Beyonder
 * @date 2021 Apr. 19th
 */
public class PlanFinder {
	/**
	 * pos[i]表示i行皇后位置
	 */
	private int[] pos;
	/**
	 * 列皇后数量
	 */
	private int[] columnNum;
	/**
	 * 副对角线皇后数量
	 */
	private int[] subDiagonalNum;
	/**
	 * 主对角线皇后数量
	 */
	private int[] mainDiagonalNum;
	/**
	 * 皇后个数
	 */
	private int n;

	/**
	 * 分配内存
	 *
	 * @param n 皇后个数
	 */
	public PlanFinder(int n) {
		this.n = n;
		this.pos = new int[n];
		this.columnNum = new int[n];
		this.mainDiagonalNum = new int[2 * n - 1];
		this.subDiagonalNum = new int[2 * n - 1];
	}

	/**
	 * 判断是否有冲突
	 *
	 * @return 无冲突的真值
	 */
	private boolean judge() {
		for (int x : columnNum) {
			if (x > 1) {
				return false;
			}
		}
		for (int x : mainDiagonalNum) {
			if (x > 1) {
				return false;
			}
		}
		for (int x : subDiagonalNum) {
			if (x > 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 寻找答案
	 *
	 * @return 皇后放置的数组表示
	 */
	public boolean[][] findAnswer() {
		Random random = new Random();
		for (int r = 0; r < n; r++) {
			int c = Math.abs(random.nextInt()) % n;
			pos[r] = c;
			++columnNum[c];
			++mainDiagonalNum[r - c + n - 1];
			++subDiagonalNum[r + c];
		}
		while (!judge()) {
			for (int r = 0; r < n; r++) {
				int minCollision = 0x3f3f3f3f;
				int minCollisionPos = -1;
				//将r行的皇后拿走 找到最小冲突列
				--columnNum[pos[r]];
				--mainDiagonalNum[r - pos[r] + n - 1];
				--subDiagonalNum[r + pos[r]];
				for (int c = 0; c < n; c++) {
					int collision = columnNum[c] + mainDiagonalNum[r - c + n - 1] + subDiagonalNum[r + c];
					if (collision < minCollision) {
						minCollision = collision;
						minCollisionPos = c;
					} else if (collision == minCollision) {
						//50%概率接受
						if ((random.nextInt() & 1) == 1) {
							minCollisionPos = c;
						}
					}
				}
				pos[r] = minCollisionPos;
				//更新每条线上的皇后数
				++columnNum[pos[r]];
				++mainDiagonalNum[r - pos[r] + n - 1];
				++subDiagonalNum[r + pos[r]];
			}
		}
		boolean[][] ans = new boolean[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				ans[r][c] = (c == pos[r]);
			}
		}
		return ans;
	}

}