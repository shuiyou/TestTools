package train;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import jxl.*;

public class SanZiQi {
	
	public static void main(String[] args) {
		SanZiQi.whoWin("data/sanziqi.xls");
		for (Object obj : result.keySet()) {
			String key = (String) obj;
			String value = (String) result.get(key);
			System.out.println(key + "=" + value);
		}
	}
	
	
	static final int CHESS_BOARD_SIZE = 3;
	static final int WIN_COUNT = 3;
	
	
	static Workbook wb = null;
	static int minR, minC, maxR, maxC;
	static Map<String, String> result = new HashMap<String, String>();
	static boolean isNullSheet = false;
	
	
	public static Map<String, String> whoWin(String fn) {
		// 1. 设置EXCEL文件
		setWorkbook(fn);
		// 3. 判断是否和规定 并且添加数组
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sheet = wb.getSheet(i);
			// 判断棋局合法性
			if (isChessBoardLegal(sheet)) {
				// 存放棋子数据
				ArrayList<String> list = saveChessmanData(sheet);
				// 判断棋子合法性
				if (isChessLegal(list)) {
					// 判断谁赢
					String winner = winner(list);
					result.put("GAME" + (i + 1), winner);
				} else {
					result.put("GAME" + (i + 1), "棋子不合法");
				}
			} else {
				result.put("GAME" + (i + 1), "未找到合理的棋盘");
			}
		}
		// 关闭资源??
		wb.close();
		return result;
	}
	
	
	static String winner(ArrayList<String> list) {
		// 判断横向
		String winner = "无人胜利";
		int sum = 1;
		for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
			for (int j = 0; j < CHESS_BOARD_SIZE - WIN_COUNT + 1; j++) {
				for (int k = 0; k < WIN_COUNT - 1; k++) {
					if (!(list.get(i * CHESS_BOARD_SIZE + j + k).equals(list.get(i * CHESS_BOARD_SIZE + j + k + 1)))
							|| list.get(i * CHESS_BOARD_SIZE + j + k).length() == 0) {
						sum = 1;
						break;
					}
					sum++;
					if (sum == WIN_COUNT) {
						winner = list.get(i * CHESS_BOARD_SIZE + j + k);
						return winner;
					}
				}
			}
		}
		// 判断竖向
		for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
			for (int j = 0; j < CHESS_BOARD_SIZE - WIN_COUNT + 1; j++) {
				for (int k = 0; k < WIN_COUNT - 1; k++) {
					if (!(list.get(i + j * CHESS_BOARD_SIZE + k * CHESS_BOARD_SIZE)
							.equals(list.get(i + j * CHESS_BOARD_SIZE + k * CHESS_BOARD_SIZE + CHESS_BOARD_SIZE)))
							|| list.get(i + j * CHESS_BOARD_SIZE + k * CHESS_BOARD_SIZE).length() == 0) {
						sum = 1;
						break;
					}
					sum++;
					if (sum == WIN_COUNT) {
						winner = list.get(i + j * CHESS_BOARD_SIZE + k * CHESS_BOARD_SIZE);
						return winner;
					}
				}
			}
		}
		// 判断两斜角
		for (int i = 0; i < CHESS_BOARD_SIZE - WIN_COUNT + 1; i++) {
			for (int j = 0; j < CHESS_BOARD_SIZE - WIN_COUNT + 1; j++) {
				for (int k = 0; k < WIN_COUNT - 1; k++) {
					if (!(list.get(i * CHESS_BOARD_SIZE + j + k * CHESS_BOARD_SIZE + k)
							.equals(list
									.get(i * CHESS_BOARD_SIZE + j + k * CHESS_BOARD_SIZE + k + CHESS_BOARD_SIZE + 1)))
							|| list.get(i * CHESS_BOARD_SIZE + j + k * CHESS_BOARD_SIZE + k).length() == 0) {
						sum = 1;
						break;
					}
					sum++;
					if (sum == WIN_COUNT) {
						winner = list.get(i * CHESS_BOARD_SIZE + j + k * CHESS_BOARD_SIZE + k);
						return winner;
					}
				}
			}
		}
		for (int i = 0; i < CHESS_BOARD_SIZE - WIN_COUNT + 1; i++) {
			for (int j = 0; j < CHESS_BOARD_SIZE - WIN_COUNT + 1; j++) {
				for (int k = 0; k < WIN_COUNT - 1; k++) {
					if (!(list.get(WIN_COUNT - 1 + i * CHESS_BOARD_SIZE + j + k * CHESS_BOARD_SIZE - k)
							.equals(list.get(WIN_COUNT - 1 + i * CHESS_BOARD_SIZE + j + k * CHESS_BOARD_SIZE - k
									+ CHESS_BOARD_SIZE - 1)))
							|| list.get(WIN_COUNT - 1 + i * CHESS_BOARD_SIZE + j + k * CHESS_BOARD_SIZE - k)
									.length() == 0) {
						sum = 1;
						break;
					}
					sum++;
					if (sum == WIN_COUNT) {
						winner = list.get(WIN_COUNT - 1 + i * CHESS_BOARD_SIZE + j + k * CHESS_BOARD_SIZE - k);
						return winner;
					}
				}
			}
		}
		return winner;
	}
	
	
	static boolean isChessLegal(ArrayList<String> list) {
		// 判断是否存在3种以上的棋子
		Set<String> set = new HashSet<String>();
		set.addAll(list);
		// 存在两种以上棋子
		set.remove("");
		if (set.size() > 2) {
			return false;
		}
		return true;
	}
	
	
	static ArrayList<String> saveChessmanData(Sheet sheet) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = minR; i <= minR + CHESS_BOARD_SIZE - 1; i++) {
			for (int j = minC; j <= minC + CHESS_BOARD_SIZE - 1; j++) {
				if (j > maxC || i > maxR) {
					list.add("");
				} else {
					list.add(sheet.getCell(j, i).getContents());
				}
			}
		}
		return list;
	}
	
	
	static boolean isChessBoardLegal(Sheet sheet) {
		// 设置最大小值
		setSheetUsedSize(sheet);
		// 空SHEET
		if (maxR == 0 && maxC == 0 && isNullSheet == true) {
			return false;
		}
		// 超过3*3
		if (maxR - minR > CHESS_BOARD_SIZE - 1 || maxC - minC > CHESS_BOARD_SIZE - 1) {
			return false;
		}
		return true;
	}
	
	
	static void setSheetUsedSize(Sheet sheet) {
		if (sheet.getRows() == 0) {
			maxR = maxC = minR = minC = 0;
			isNullSheet = true;
			return;
		}
		isNullSheet = false;
		// 取得最大值
		int usedMaxR = sheet.getRows() - 1;
		int usedMaxC = sheet.getColumns() - 1;
		// 取得最小值
		// 最小R
		outer: for (int minRTemp = 0; minRTemp <= usedMaxR; minRTemp++) {
			for (int minCTemp = 0; minCTemp <= usedMaxC; minCTemp++) {
				if (sheet.getCell(minCTemp, minRTemp).getContents() != "") {
					minR = minRTemp;
					break outer;
				}
			}
		}
		// 最小C
		outer: for (int minCTemp = 0; minCTemp <= usedMaxC; minCTemp++) {
			for (int minRTemp = 0; minRTemp <= usedMaxR; minRTemp++) {
				if (sheet.getCell(minCTemp, minRTemp).getContents() != "") {
					minC = minCTemp;
					break outer;
				}
			}
		}
		// 最大R
		outer: for (int maxRTemp = usedMaxR; maxRTemp >= 0; maxRTemp--) {
			for (int maxCTemp = usedMaxC; maxCTemp >= 0; maxCTemp--) {
				if (sheet.getCell(maxCTemp, maxRTemp).getContents() != "") {
					maxR = maxRTemp;
					break outer;
				}
			}
		}
		// 最大C
		outer: for (int maxCTemp = usedMaxC; maxCTemp >= 0; maxCTemp--) {
			for (int maxRTemp = usedMaxR; maxRTemp >= 0; maxRTemp--) {
				if (sheet.getCell(maxCTemp, maxRTemp).getContents() != "") {
					maxC = maxCTemp;
					break outer;
				}
			}
		}
	}
	
	
	static void setWorkbook(String fn) {
		try {
			InputStream stream = new FileInputStream(fn);
			wb = Workbook.getWorkbook(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}