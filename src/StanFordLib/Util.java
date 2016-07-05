package StanFordLib;

import java.io.FileWriter;
import java.io.IOException;

public class Util {

	public static boolean contain(String[] arr, String targetValue) {
		for (String s : arr) {
			if (s.equals(targetValue))
				return true;
		}
		return false;
	}

	public static void writeCSV_File(WordCount[] wc, String outputFile) throws IOException {
		FileWriter writer = null;
		try {

			writer = new FileWriter(outputFile);

			for (WordCount item : wc) {
				writer.append(item.getWord());
				writer.append(Constants.COMMA_DELIMITER);
				writer.append(String.valueOf(item.getCount()));
				writer.append(Constants.NEW_LINE_SEPARATOR);
			}
		} catch (Exception e) {

			System.out.println("Error !!!");

			e.printStackTrace();

		} finally {

			try {

				writer.flush();

				writer.close();

			} catch (IOException e) {

				System.out.println("Error while flushing/closing writer !!!");

				e.printStackTrace();

			}

		}

	}

}
