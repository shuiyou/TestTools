package train;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import jxl.*;

/*
 * 声明：以O和X做双方棋子
 * 需求：写一个程序，待三子棋游戏结束后，判断棋局结果
 * */
public class Result {
	public static void main(String args[]) {
		Result r = new Result();
		r.whoWin("data/sanziqi.xls");
	}

	public Map<String, String> whoWin(String file_name) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Workbook book = Workbook.getWorkbook(new File(file_name));
			Sheet[] rws = book.getSheets();
			for (int k = 0; k < rws.length; k++) {

				Sheet sheet = book.getSheet(k);// 获得第k个工作表对象
				String sheet_name = sheet.getName(); // 获得该工作表的表名
				int rows = sheet.getRows(); // 获取工作表的总行数
				int columns = sheet.getColumns();// 获取工作表的总列数
				int i = 0, j = 0;

				/*
				 * 遍历每一行单元格，当首次遇到单元格内容为O或者X时，则结束for循环，此时（i，j）就是首个棋子的坐标
				 */
				end: for (j = 0; j < rows; j++) {
					for (i = 0; i < columns; i++) {
						String first_cell = sheet.getCell(i, j).getContents();
						if (first_cell.equals("O")) {
							break end;
						}
						if (first_cell.equals("X")) {
							break end;
						}
					}
				}

				String key1 = "cell1";
				String key2 = "cell2";
				String key3 = "cell3";
				String key4 = "cell4";
				String key5 = "cell5";
				String key6 = "cell6";
				String key7 = "cell7";
				String key8 = "cell8";
				String key9 = "cell9";

				map.put(key1, sheet.getCell(i, j).getContents());
				map.put(key2, sheet.getCell(i + 1, j).getContents());
				map.put(key3, sheet.getCell(i + 2, j).getContents());
				map.put(key4, sheet.getCell(i, j + 1).getContents());
				map.put(key5, sheet.getCell(i + 1, j + 1).getContents());
				map.put(key6, sheet.getCell(i + 2, j + 1).getContents());
				map.put(key7, sheet.getCell(i, j + 2).getContents());
				map.put(key8, sheet.getCell(i + 1, j + 2).getContents());
				map.put(key9, sheet.getCell(i + 2, j + 2).getContents());

				/*
				 * 横向或竖向或斜向的三颗棋子相同，则该棋子获胜
				 */
				if (map.get(key1) == map.get(key2) && map.get(key2) == map.get(key3) && map.get(key1) != "") {
					System.out.println(sheet_name + "：" + map.get(key1) + " 赢得了游戏");
				} else if (map.get(key4) == map.get(key5) && map.get(key5) == map.get(key6) && map.get(key4) != "") {
					System.out.println(sheet_name + "：" + map.get(key4) + " 赢得了游戏");
				} else if (map.get(key7) == map.get(key8) && map.get(key8) == map.get(key9) && map.get(key7) != "") {
					System.out.println(sheet_name + "：" + map.get(key7) + " 赢得了游戏");
				} else if (map.get(key1) == map.get(key4) && map.get(key4) == map.get(key7) && map.get(key1) != "") {
					System.out.println(sheet_name + "：" + map.get(key1) + " 赢得了游戏");
				} else if (map.get(key2) == map.get(key5) && map.get(key5) == map.get(key8) && map.get(key2) != "") {
					System.out.println(sheet_name + "：" + map.get(key2) + " 赢得了游戏");
				} else if (map.get(key3) == map.get(key6) && map.get(key6) == map.get(key9) && map.get(key3) != "") {
					System.out.println(sheet_name + "：" + map.get(key3) + " 赢得了游戏");
				} else if (map.get(key1) == map.get(key5) && map.get(key5) == map.get(key9) && map.get(key1) != "") {
					System.out.println(sheet_name + "：" + map.get(key1) + " 赢得了游戏");
				} else if (map.get(key3) == map.get(key5) && map.get(key5) == map.get(key7) && map.get(key3) != "") {
					System.out.println(sheet_name + "：" + map.get(key3) + " 赢得了游戏");
				} else {
					System.out.println(sheet_name + "：哟，打了个平手！");
				}
			}
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return map;
	
		
	}
}
