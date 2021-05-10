package train;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class jtlAnalysis {

	private ArrayList<HashMap<String, String>> collectResult(String fileName)
			throws IOException {
		FileInputStream file = new FileInputStream(
				"/Users/sunjie/Desktop/apache-jmeter-3.0/" +  fileName);
		InputStreamReader read = new InputStreamReader(file, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(read);

		String lineTxt = null;
		ArrayList<String> orgList = new ArrayList<String>();
		while ((lineTxt = bufferedReader.readLine()) != null) {
			orgList.add(lineTxt);
		}
		int resultNum = orgList.size();
		read.close();

		String[] titleList = (orgList.get(0)).split(",");
		ArrayList<HashMap<String, String>> resultSample = new ArrayList<HashMap<String, String>>();

		if (resultNum != 0) {
			for (int i = 1; i < resultNum; i++) {
				HashMap<String, String> singleResultMap = new HashMap<String, String>();
				String[] currentLine = orgList.get(i).split(",");
				for (int k = 0; k < titleList.length; k++) {
					singleResultMap.put(titleList[k], currentLine[k]);
				}
				resultSample.add(singleResultMap);
			}
		}
		return resultSample;
	}

	public String analysisElapsed(String fileName, String time) throws IOException {
		ArrayList<HashMap<String, String>> result = collectResult(fileName);
		int resultNum = result.size();
		StringBuilder elapsedBuilder = new StringBuilder();
		for (int i = 0; i < resultNum; i++) {
			elapsedBuilder.append((result.get(i)).get("elapsed"));
			elapsedBuilder.append(",");
		}
		String elapsedTemp = elapsedBuilder.toString().substring(0,
				elapsedBuilder.toString().length() - 1);
		String[] elapsedList = elapsedTemp.split(",");
		int[] elapsed = new int[elapsedList.length];
		for (int i = 0; i < elapsedList.length; i++) {
			elapsed[i] = Integer.parseInt(elapsedList[i]);
		}

		Arrays.sort(elapsed);

		int elapsedNum = elapsed.length;

		// 获取时间段数组长度
		String[] timeQuantum = time.split(",");
		int timeQuantumCount = timeQuantum.length;

		int[] analysisResult = new int[timeQuantumCount];
		for (int i = 0; i < timeQuantumCount; i++) {
			analysisResult[i] = 0;
		}
		for (int i = 0; i < elapsedNum; i++) {
			for (int k = 0; k < timeQuantumCount; k++) {
				if (k == timeQuantumCount - 1
						&& elapsed[i] > Integer.parseInt(timeQuantum[k])) {
					analysisResult[k] = analysisResult[k] + 1;
				} else if (elapsed[i] > Integer.parseInt(timeQuantum[k])
						&& elapsed[i] <= Integer.parseInt(timeQuantum[k + 1])) {
					analysisResult[k] = analysisResult[k] + 1;
				}
			}
		}

		StringBuilder resultString = new StringBuilder("[");
		for (int i = 0; i < analysisResult.length; i++) {
			if (i != analysisResult.length - 1) {
				resultString.append(timeQuantum[i] + "-" + timeQuantum[i + 1]
						+ "(ms): " + analysisResult[i] + "笔, ");
			} else {
				resultString.append(timeQuantum[i] + "++" + "(ms): " + analysisResult[i] + "笔");
			}
		}
		resultString.append("]");

		return resultString.toString();
	}

	public static void main(String[] args) {
		jtlAnalysis test = new jtlAnalysis();
		try {
			System.out.println(test.analysisElapsed(args[0], args[1]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
