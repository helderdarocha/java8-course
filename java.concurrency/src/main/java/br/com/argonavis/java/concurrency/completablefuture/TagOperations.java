package br.com.argonavis.java.concurrency.completablefuture;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TagOperations {
	public String tag(String tagName, String contents) {
		return "<" + tagName + ">" + contents + "</" + tagName + ">";
	}

	public String unTag(String taggedText) {
		Pattern pattern = Pattern.compile("<(\\w+)>(.*)</\\1>");
		Matcher matcher = pattern.matcher(taggedText);
		matcher.find();
		return matcher.group(2);
	}
}
