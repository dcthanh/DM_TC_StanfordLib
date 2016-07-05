package StanFordLib;

import java.util.Arrays;
import java.util.List;

public final class Constants {
	private Constants() {
	}

	/*
	 * Dummy data test
	 */
	public static final boolean TEST_MODE = false;
	// From variable
	public static final String DUMMY_STRING_DATA = "After doing a little shopping at the OTFM on Saturday, I headed over here to try it out based on the good reviews from Yelp! What a pleasant surprise! I'm not a huge pancake person, so I decided to try the Greek egg scramble with egg whites and the BF had the yogurt with fruit, since he had already downed a GF cupcake and cookie from the GF bakery. The scramble was delicious, perfectly seasoned and I ate every last bite of it! Also, the wheat toast was fantastic!! I also had a chai tea which was different, but really good! So many chai teas are overly seasoned with ginger and this one you could really taste the black tea! The BF had a latte as well and he loved it!! The only thing that wasn't awesome was the seating process. There was a definite awkward process of getting seated...not really knowing do you seat yourself, do they seat you. A more defined seating process would make this a solid 4.5 stars!!!";

	// From file

	public static final String DUMMY_FILE = "D:\\in.txt";

	/*
	 * Output file
	 */

	public static final String OUTPUT_FILE = "D:\\out.txt";

	/*
	 * Define part-of-speech tags
	 * https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.
	 * html JJ Adjective JJR Adjective, comparative JJS Adjective, superlative
	 */
	public static final String[] ADJECTIVE_TAG = new String[] { "JJ", "JJR", "JJS" };


	// Delimiter used in CSV file

	public static final String COMMA_DELIMITER = ",";

	public static final String NEW_LINE_SEPARATOR = "\n";

}