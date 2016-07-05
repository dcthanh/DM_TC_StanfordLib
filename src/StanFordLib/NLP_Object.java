package StanFordLib;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import StanFordLib.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.OriginalTextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

public class NLP_Object {

	Properties props = null;
	StanfordCoreNLP pipeline;
	List<CoreMap> sentences;
	Iterator<CoreMap> sentIt;
	CoreMap curSentence;

	public NLP_Object() {
		// creates a StanfordCoreNLP object, with POS tagging
		props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos");
		pipeline = new StanfordCoreNLP(props);
	}

	public void getParsed(String text) {

		// create an empty Annotation just with the given text
		Annotation document = new Annotation(text);

		// run all Annotators on this text
		pipeline.annotate(document);
		sentences = document.get(SentencesAnnotation.class);
		sentIt = sentences.iterator();
	}

	public boolean hasNextSentence() {
		return sentIt.hasNext();
	}

	public void processNextSentence() {
		curSentence = sentIt.next();
	}

	public String[] getPOS() {
		List<CoreLabel> tokenList = curSentence.get(TokensAnnotation.class);
		String[] ans = new String[tokenList.size()];
		int i = 0;
		Iterator<CoreLabel> tokit = tokenList.iterator();
		while (tokit.hasNext()) {
			CoreLabel token = tokit.next();
			String pos = token.get(PartOfSpeechAnnotation.class);
			String word = token.get(OriginalTextAnnotation.class);
			ans[i] = word + "/" + pos;
			i++;
		}
		return ans;
	}

	public String getLine() {
		List<CoreLabel> tokenList = curSentence.get(TokensAnnotation.class);
		StringBuffer ans = new StringBuffer();
		int i = 0;
		Iterator<CoreLabel> tokit = tokenList.iterator();
		while (tokit.hasNext()) {
			CoreLabel token = tokit.next();
			String word = token.get(OriginalTextAnnotation.class);
			ans.append(word + " ");
			i++;
		}
		return ans.toString();
	}

	public ArrayList<String> StanfordCoreNlp_String(String str, String[] arr) {
		ArrayList<String> mylist = new ArrayList<String>();
		getParsed(str);
		ArrayList<String> all_lines = new ArrayList<String>();

		while (hasNextSentence()) {
			processNextSentence();
			all_lines.add(getLine());

			String text = getLine();
			Annotation document = new Annotation(text);
			// run all Annotators on this text
			pipeline.annotate(document);

			List<CoreMap> sentences = document.get(SentencesAnnotation.class);

			for (CoreMap sentence : sentences) {
				// traversing the words in the current sentence
				// a CoreLabel is a CoreMap with additional token-specific
				// methods
				for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
					// this is the text of the token
					String word = token.get(TextAnnotation.class);
					// this is the POS tag of the token
					String pos = token.get(PartOfSpeechAnnotation.class);

					if (Util.contain(arr, pos)) {
						mylist.add(word);
					}

				}
				// this is the parse tree of the current sentence
				Tree tree = sentence.get(TreeAnnotation.class);

				// this is the Stanford dependency graph of the current sentence
				SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
			}

		}
		return mylist;

	}

	public ArrayList<String> StanfordCoreNlp_ArrString(ArrayList<String> arrStr, String[] arr) {
		ArrayList<String> mylist = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (String s : arrStr) {
			sb.append(s);
			sb.append(" ");
		}
		getParsed(sb.toString());
		ArrayList<String> all_lines = new ArrayList<String>();

		while (hasNextSentence()) {
			processNextSentence();
			all_lines.add(getLine());

			String text = getLine();
			Annotation document = new Annotation(text);
			// run all Annotators on this text
			pipeline.annotate(document);

			List<CoreMap> sentences = document.get(SentencesAnnotation.class);

			for (CoreMap sentence : sentences) {
				// traversing the words in the current sentence
				// a CoreLabel is a CoreMap with additional token-specific
				// methods
				for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
					// this is the text of the token
					String word = token.get(TextAnnotation.class);
					// this is the POS tag of the token
					String pos = token.get(PartOfSpeechAnnotation.class);

					if (Util.contain(arr, pos)) {
						mylist.add(word);
					}

				}
				// this is the parse tree of the current sentence
				Tree tree = sentence.get(TreeAnnotation.class);

				// this is the Stanford dependency graph of the current
				// sentence
				SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
			}

		}

		return mylist;

	}

	public static void main(String[] s) throws IOException {
		NLP_Object nlp = new NLP_Object();
		ReadFile rd = new ReadFile();
		WordCount wc = new WordCount("thanhdc", 69);
		ArrayList<String> listADJ = new ArrayList<String>();
		if (Constants.TEST_MODE) {
			listADJ = nlp.StanfordCoreNlp_String(Constants.DUMMY_STRING_DATA, Constants.ADJECTIVE_TAG);
		} else {
			rd.read(Constants.DUMMY_FILE);
			ArrayList<String> contentFile = rd.getMessages();
			if (contentFile != null) {
				listADJ = nlp.StanfordCoreNlp_ArrString(contentFile, Constants.ADJECTIVE_TAG);
			} else {
				System.err.println("Error when read file");
			}
		}
		wc.getFrequentWords(listADJ);

		try {
			Util.writeCSV_File(wc.getFrequentWords(listADJ), Constants.OUTPUT_FILE);
		} catch (Exception e) {
			System.err.print("Error");
			e.printStackTrace();
		}
	}
}
